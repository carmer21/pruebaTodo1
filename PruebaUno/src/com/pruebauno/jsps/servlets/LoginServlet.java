package com.pruebauno.jsps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pruebauno.jsps.business.UsuarioBusiness;
import com.pruebauno.jsps.dto.UsuarioDTO;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/loginweb.jr")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
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
		String documento = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		UsuarioDTO usuarioDto = new UsuarioDTO();
		try{
			usuarioDto = UsuarioBusiness.getInstance().consultarUsuario(documento, password);
			if(usuarioDto.getIdUsuario()>0){
				
				request.setAttribute("usuarioDto", usuarioDto);
				request.setAttribute("password", password);
				request.setAttribute("documento", documento);
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			}else{
				request.setAttribute("usuario", documento);
				request.setAttribute("password", password);
				request.getRequestDispatcher("errorLogueo.jsp").forward(request, response);
			}
		}catch(Exception e){
			request.getRequestDispatcher("login.html").forward(request, response);
			e.printStackTrace();
		}
		
	}

}
