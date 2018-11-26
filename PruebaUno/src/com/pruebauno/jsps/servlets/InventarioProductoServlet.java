package com.pruebauno.jsps.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pruebauno.jsps.business.InventarioProductoBusiness;
import com.pruebauno.jsps.business.UsuarioBusiness;
import com.pruebauno.jsps.dto.InventarioProductoDTO;
import com.pruebauno.jsps.dto.ProductosDTO;
import com.pruebauno.jsps.dto.UsuarioDTO;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/inventarioproducto.jr")
public class InventarioProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("todo1");

    /**
     * Default constructor. 
     */
    public InventarioProductoServlet() {
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
		String accion = request.getParameter("accion3");
		if(accion!=null && !accion.equals("") &&  accion.equals("0")){
			String documento = request.getParameter("documento3");
			String password = request.getParameter("password3");
			InventarioProductoDTO inventarioProductoDTO = new InventarioProductoDTO();
			// CONSULTAR DATOS USUARIOS
			try{
				UsuarioDTO usuarioDto = UsuarioBusiness.getInstance().consultarUsuario(documento, password);
				if(usuarioDto.getIdUsuario()>0){
					request.setAttribute("usuarioDto", usuarioDto);
				}else{
					request.setAttribute("usuarioDto", new UsuarioDTO());
				}
				inventarioProductoDTO = InventarioProductoBusiness.getInstance().consultarInventarioProducto();
				System.err.println("InventarioProductoServlet >>> consultar >>> getListaInventarioProducto: "+inventarioProductoDTO.getListaInventarioProducto().size());
				System.err.println("InventarioProductoServlet >>> consultar >>> getListaProductos: "+inventarioProductoDTO.getListaProductos().size());
				request.setAttribute("listaInventarioProducto", inventarioProductoDTO.getListaInventarioProducto());
				request.setAttribute("listaProductos", inventarioProductoDTO.getListaProductos());
				request.setAttribute("password", password);
				request.getRequestDispatcher("inventarioproducto.jsp").forward(request, response);
			}catch(Exception e){
				log.error("InventarioProductoServlet >>> doPost-0: "+e.getMessage());
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		}
		if(accion!=null && !accion.equals("") &&  accion.equals("1")){
			// GRABAR INVENTARIO PRODUCTOS
			String respuesta = "";
			OutputStream out = null;
			int grabar = 0;
			try{
				InventarioProductoDTO inventarioProductoDTO = new InventarioProductoDTO();
				ProductosDTO productosDTO = new ProductosDTO();
				productosDTO.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
				inventarioProductoDTO.setProductoDTO(productosDTO);
				inventarioProductoDTO.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
				inventarioProductoDTO.setValorUnidad(Integer.parseInt(request.getParameter("valorUnidad")));
				grabar = InventarioProductoBusiness.getInstance().grabarInventarioProducto(inventarioProductoDTO);
				
				if(grabar==0){
					respuesta="Inventario por Producto registrado con èxito";
				}else{
					respuesta="Error al registrar el Inventario por producto";
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
				log.error("InventarioProductoServlet >>> doPost-1: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}
			
		if(accion!=null && !accion.equals("") &&  accion.equals("2")){
			// ELIMINAR INVENTARIO PRODUCTOS
			String respuesta = "";
			OutputStream out = null;
			int eliminar = 0;
			try{
				int idInventariProducto = Integer.parseInt(request.getParameter("idInventariProducto"));
				eliminar = InventarioProductoBusiness.getInstance().eliminarInventario(idInventariProducto);
				if(eliminar==0){
					respuesta="Inventario de producto eliminado con èxito";
				}else{
					respuesta="Error al eliminar el inventario de producto";
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
				log.error("InventarioProductoServlet >>> doPost-2: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}
	}

}
