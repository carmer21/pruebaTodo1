package com.pruebauno.jsps.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pruebauno.jsps.business.UsuarioBusiness;
import com.pruebauno.jsps.dto.TiposDocumentosDTO;
import com.pruebauno.jsps.dto.UsuarioDTO;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/usuario.jr")
public class UsuarioGrabarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("todo1");

    /**
     * Default constructor. 
     */
    public UsuarioGrabarServlet() {
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
		String accion = request.getParameter("accion");
		if(accion!=null && !accion.equals("") &&  accion.equals("0")){
			String documento = request.getParameter("documento");
			String password = request.getParameter("password");
			UsuarioDTO usuarioDto = new UsuarioDTO();
			ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
			try{
				usuarioDto = UsuarioBusiness.getInstance().consultarUsuario(documento, password);
				if(usuarioDto.getIdUsuario()>0){
					lista.add(usuarioDto);
					if(!usuarioDto.getListaUsuarios().isEmpty() && usuarioDto.getListaUsuarios().size()>0)
						request.setAttribute("lista", usuarioDto.getListaUsuarios());
					else
						request.setAttribute("lista", lista);
					
					List<TiposDocumentosDTO> listaTiposDoc = new ArrayList<TiposDocumentosDTO>();
					TiposDocumentosDTO tiposDocumentos = new TiposDocumentosDTO();
					tiposDocumentos.setCodigo("");
					tiposDocumentos.setTipoDocumento("Seleccione");
					listaTiposDoc.add(tiposDocumentos);
					tiposDocumentos = new TiposDocumentosDTO();
					tiposDocumentos.setCodigo("CC");
					tiposDocumentos.setTipoDocumento("Cèdula de Ciudadanìa");
					listaTiposDoc.add(tiposDocumentos);
					tiposDocumentos = new TiposDocumentosDTO();
					tiposDocumentos.setCodigo("NIT");
					tiposDocumentos.setTipoDocumento("NIT");
					listaTiposDoc.add(tiposDocumentos);
					tiposDocumentos = new TiposDocumentosDTO();
					tiposDocumentos.setCodigo("CE");
					tiposDocumentos.setTipoDocumento("Cèdula de Extrangerìa");
					listaTiposDoc.add(tiposDocumentos);
					
					request.setAttribute("usuarioDto", usuarioDto);
					request.setAttribute("password", password);
					request.setAttribute("documento", documento);
					request.setAttribute("listaTiposDoc", listaTiposDoc);
					request.getRequestDispatcher("usuarios.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("login.html").forward(request, response);
				}
			}catch(Exception e){
				log.error("UsuarioGrabarServlet >>> doPost-0: "+e.getMessage());
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		}
		if(accion!=null && !accion.equals("") &&  accion.equals("1")){
			// GRABAR
			String respuesta = "";
			OutputStream out = null;
			int grabar = 0;
			try{
				String tipoDocumento = request.getParameter("tipoDocumento");
				String documento = request.getParameter("documento");
				String nombres = request.getParameter("nombres");
				String apellidos = request.getParameter("apellidos");
				String cargo = request.getParameter("cargo");
				UsuarioDTO usuarioDto = new UsuarioDTO();
				usuarioDto.setTipoDocumento(tipoDocumento);
				usuarioDto.setDocumento(documento);
				usuarioDto.setNombres(nombres);
				usuarioDto.setApellidos(apellidos);
				usuarioDto.setCargo(cargo);
				
				grabar = UsuarioBusiness.getInstance().grabarUsuario(usuarioDto);
				
				if(grabar==0){
					respuesta="Usuario registrado con èxito";
				}else{
					respuesta="Error al registrar el usuario";
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
				log.error("UsuarioGrabarServlet >>> doPost-1: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}
		
		if(accion!=null && !accion.equals("") &&  accion.equals("2")){
			// ELIMINAR USUARIOS
			String respuesta = "";
			OutputStream out = null;
			int eliminar = 0;
			try{
				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				eliminar = UsuarioBusiness.getInstance().eliminarUsuario(idUsuario);
				if(eliminar==0){
					respuesta="Usuario eliminado con èxito";
				}else{
					respuesta="Error al eliminar el usuario";
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
				log.error("UsuarioGrabarServlet >>> doPost-2: "+e.getMessage());
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} finally {
				out.flush();
				out.close();
			}
		}
	}

}
