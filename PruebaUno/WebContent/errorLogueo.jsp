<%@page import="java.util.ArrayList"%>
<%@page import="com.pruebauno.jsps.dto.UsuarioDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/estilos.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%-- <% String usuario = sys.getString("usuarioLogeado"); %> --%>
	<% 
		String usuario = request.getAttribute("usuario").toString();
		String password = request.getAttribute("password").toString();
	%>
	<div id="banner"><h1 align="center" style="font-weight: bold;" >PRUEBA TÉCNICA TODO1</h1></div>
	<p>ERROR - no existe un usuario con los datos ingresados</p>
	<label>usuario: <%=usuario%></label><p></p>
	<label>password: <%=password%></label><p></p>
	
</body>
</html>