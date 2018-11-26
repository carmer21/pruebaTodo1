<%@page import="com.pruebauno.jsps.dto.ProductosDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pruebauno.jsps.dto.UsuarioDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/estilos.css">
<script type="text/javascript" src="./js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="./js/pruebaTodo1.js"></script>
<title>PRUEBA TÉCNICA TODO1</title>
</head>
<body>
		
	<%  
		UsuarioDTO usuarioDto = (UsuarioDTO)request.getAttribute("usuarioDto");
		String password = request.getAttribute("password").toString();
		String documento = request.getAttribute("documento").toString();
	%>
<!-- <h1 align="center" style="font-weight: bold;" >PRUEBA TECNICA TODO 1</h1> -->
	
<div id="banner"><h1 align="center" style="font-weight: bold;" >PRUEBA TÉCNICA TODO1</h1></div>

		<p>Bienvenido USUARIO: <%=usuarioDto.getIdUsuario()%></p>
		<label>Documento: <%=usuarioDto.getTipoDocumento()%> - <%=usuarioDto.getDocumento()%></label> <label> | </label>
		<label>Nombres: <%=usuarioDto.getNombres()%>  <%=usuarioDto.getApellidos()%></label> <label> | </label>
		<label>Cargo: <%=usuarioDto.getCargo()%></label><p></p>
		
		<div id="menu">
			<div id="menuUsuarios">
				ADMINISTRAR USUARIOS
			</div>
			<div id="menuProducto">
				ADMINISTRAR PRODUCTOS
			</div>
			<div id="menuInventario">
				 INVENTARIO PRODUCTOS
			</div>
			<div id="menuTienda">
				TIENDA TODO 1
			</div>
		</div>
		<div id="botones">
			<div class="botonContenedor">
				<form action="usuario.jr" method="POST" onsubmit="">
					<input type="hidden" name="accion" id="accion">
					<input type="hidden" name="documento" id="documento" value="<%=documento%>">
					<input type="hidden" name="password" id="password" value="<%=password%>">
					<input type="submit" id="btnAdminUsuarios" value="ADMINISTRAR USUARIOS">
				</form>
			</div>
			<div class="botonContenedor">
				<form action="productos.jr" method="POST" onsubmit="">
					<input type="hidden" name="accion2" id="accion2">
					<input type="hidden" name="documento2" id="documento2" value="<%=documento%>">
					<input type="hidden" name="password2" id="password2" value="<%=password%>">
					<input type="submit" id="btnAdminProductos" value="ADMINISTRAR PRODUCTOS">
				</form>
			</div>
			<div class="botonContenedor">
				<form action="inventarioproducto.jr" method="POST" onsubmit="">
					<input type="hidden" name="accion3" id="accion3">
					<input type="hidden" name="documento3" id="documento3" value="<%=documento%>">
					<input type="hidden" name="password3" id="password3" value="<%=password%>">
					<input type="submit" id="btnInventarioProductos" value="INVENTARIO POR PRODUCTO">
				</form>
			</div>
			<div class="botonContenedorVentas">
				<form action="ventas.jr" method="POST" onsubmit="">
					<input type="hidden" name="accion4" id="accion4">
					<input type="hidden" name="documento4" id="documento4" value="<%=documento%>">
					<input type="hidden" name="password4" id="password4" value="<%=password%>">
					<input type="submit" id="btnVentas" value="TIENDA TODO1">
				</form>
			</div>
		</div>
		
</body>
</html>