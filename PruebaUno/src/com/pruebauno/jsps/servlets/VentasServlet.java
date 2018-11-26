package com.pruebauno.jsps.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pruebauno.jsps.business.UsuarioBusiness;
import com.pruebauno.jsps.business.VentasBusiness;
import com.pruebauno.jsps.dto.ProductosDTO;
import com.pruebauno.jsps.dto.UsuarioDTO;
import com.pruebauno.jsps.dto.VentasDTO;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/inventarioproducto.jr")
public class VentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("todo1");

    /**
     * Default constructor. 
     */
    public VentasServlet() {
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
		String accion = request.getParameter("accion4");
		if(accion!=null && !accion.equals("") &&  accion.equals("0")){
			String documento = request.getParameter("documento4");
			String password = request.getParameter("password4");
			try{
				UsuarioDTO usuarioDto = UsuarioBusiness.getInstance().consultarUsuario(documento, password);
				if(usuarioDto.getIdUsuario()>0){
					request.setAttribute("usuarioDto", usuarioDto);
				}else{
					request.setAttribute("usuarioDto", new UsuarioDTO());
				}
				VentasDTO ventasDTO = VentasBusiness.getInstance().consultarInventarioProducto();
				request.setAttribute("listaInventarioProducto", ventasDTO.getListaInventario());
				request.setAttribute("listaVentas", ventasDTO.getListaVentas());
				request.setAttribute("password", password);
				request.getRequestDispatcher("ventas.jsp").forward(request, response);
			}catch(Exception e){
				request.getRequestDispatcher("login.html").forward(request, response);
				log.error("VentasServlet >>> doPost-0: "+e.getMessage());
			}
		}
		if(accion!=null && !accion.equals("") &&  accion.equals("1")){
			// GRABAR INVENTARIO PRODUCTOS
			String respuesta = "";
			OutputStream out = null;
			int grabar = 0;
			try{
				VentasDTO ventasDTO = new VentasDTO();
				ProductosDTO productosDTO = new ProductosDTO();
				productosDTO.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
				ventasDTO.setProductosDTO(productosDTO);
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
				ventasDTO.setUsuarioDTO(usuarioDTO);
				ventasDTO.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
				ventasDTO.setValorVenta(Integer.parseInt(request.getParameter("valorVenta")));
				grabar = VentasBusiness.getInstance().grabarVenta(ventasDTO);
				
				if(grabar==0){
					respuesta="Se ha registrado la venta con èxito";
				}else{
					respuesta="Error al registrar la venta del producto";
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
				log.error("VentasServlet >>> doPost-1: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}
		if(accion!=null && !accion.equals("") &&  accion.equals("2")){
			// ELIMINAR VENTAS
			String respuesta = "";
			OutputStream out = null;
			int eliminar = 0;
			try{
				int idVenta = Integer.parseInt(request.getParameter("idVenta"));
				int idProducto = Integer.parseInt(request.getParameter("idProducto"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				eliminar = VentasBusiness.getInstance().eliminarVenta(idVenta, idProducto, cantidad);
				if(eliminar==0){
					respuesta="Venta eliminada con èxito";
				}else{
					respuesta="Error al eliminar la venta";
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
				log.error("VentasServlet >>> doPost-2: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}		
	}

}
