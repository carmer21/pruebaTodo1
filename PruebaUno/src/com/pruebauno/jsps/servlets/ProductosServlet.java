package com.pruebauno.jsps.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pruebauno.jsps.business.ProductosBusiness;
import com.pruebauno.jsps.business.UsuarioBusiness;
import com.pruebauno.jsps.dto.ProductosDTO;
import com.pruebauno.jsps.dto.UsuarioDTO;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/productos.jr")
public class ProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("todo1");

    /**
     * Default constructor. 
     */
    public ProductosServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion2");
		if(accion!=null && !accion.equals("") &&  accion.equals("0")){
			String documento = request.getParameter("documento2");
			String password = request.getParameter("password2");
			// consultar 
			try{
				ProductosDTO productosDTO = new ProductosDTO();
				UsuarioDTO usuarioDto = UsuarioBusiness.getInstance().consultarUsuario(documento, password);
				if(usuarioDto.getIdUsuario()>0){
					request.setAttribute("usuarioDto", usuarioDto);
				}else{
					request.setAttribute("usuarioDto", new UsuarioDTO());
				}
				productosDTO = ProductosBusiness.getInstance().consultarProductos();
				request.setAttribute("listaProductos", productosDTO.getListaProductos());
				request.setAttribute("listaTiposProductos", productosDTO.getListaTiposProductos());
				request.setAttribute("password", password);
				request.getRequestDispatcher("productos.jsp").forward(request, response);
			}catch(Exception e){
				log.error("ProductosServlet >>> doPost-0: "+e.getMessage());
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		}
		if(accion!=null && !accion.equals("") &&  accion.equals("1")){
			// GRABAR PRODUCTOS
			String respuesta = "";
			OutputStream out = null;
			int grabar = 0;
			try{
				ProductosDTO productosDTO = new ProductosDTO();
				productosDTO.setTipo(request.getParameter("tipoProducto"));
				productosDTO.setCodigo(request.getParameter("codigoProducto"));
				productosDTO.setNombre(request.getParameter("nombre"));
				grabar = ProductosBusiness.getInstance().grabarProducto(productosDTO);
				
				if(grabar==0){
					respuesta="Producto registrado con èxito";
				}else{
					respuesta="Error al registrar el producto";
				}
				response.setContentType("application/json; charset=ISO-8859-1" );
				out = response.getOutputStream();
				int offset = 0;
				int length =  respuesta.getBytes().length;
				do {
					int size = 1024;
					if( offset+size> length)
						size = length-offset;
					out.write( respuesta.getBytes(), offset, size );
					offset += size;
				} while( offset<length );
			}catch(Exception e){
				log.error("ProductosServlet >>> doPost-1: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}
			
		if(accion!=null && !accion.equals("") &&  accion.equals("2")){
			// ELIMINAR PRODUCTOS
			String respuesta = "";
			OutputStream out = null;
			int eliminar = 0;
			try{
				int idProductos = Integer.parseInt(request.getParameter("idProductos"));
				eliminar = ProductosBusiness.getInstance().eliminarProductos(idProductos);
				if(eliminar==0){
					respuesta="Producto eliminado con èxito";
				}else{
					respuesta="Error al eliminar el producto";
				}
				response.setContentType("application/json; charset=ISO-8859-1" );
				out = response.getOutputStream();
				int offset = 0;
				int length =  respuesta.getBytes().length;
				do {
					int size = 1024;
					if( offset+size> length)
						size = length-offset;
					out.write( respuesta.getBytes(), offset, size );
					offset += size;
				} while( offset<length );
			}catch(Exception e){
				log.error("ProductosServlet >>> doPost-2: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}
	}

}
