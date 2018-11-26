<%@page import="com.pruebauno.jsps.dto.TiposProductosDTO"%>
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
<script type="text/javascript" src="./js/productos.js"></script>
<title>Administración de Productos</title>
</head>
<body>
		
	<%  
		String password = request.getAttribute("password").toString();
		UsuarioDTO usuarioDto = (UsuarioDTO)request.getAttribute("usuarioDto");
		ArrayList<ProductosDTO> listaProductos = (ArrayList<ProductosDTO>)request.getAttribute("listaProductos");
		ArrayList<TiposProductosDTO> listaTiposProductos = (ArrayList<TiposProductosDTO>)request.getAttribute("listaTiposProductos");
	%>
<!-- <h1 align="center" style="font-weight: bold;" >PRUEBA TECNICA TODO 1</h1> -->
	
<div id="banner"><h1 align="center" style="font-weight: bold;" >PRUEBA TÉCNICA TODO1</h1></div>
		<input type="hidden" name="accion2" id="accion2" >
		<input type="hidden" name="documento2" id="documento2" value="<%=usuarioDto.getDocumento()%>">
		<input type="hidden" name="password2" id="password2" value="<%=password%>">
		
		<p>Bienvenido USUARIO: <%=usuarioDto.getIdUsuario()%></p>
		<label>Documento: <%=usuarioDto.getTipoDocumento()%> - <%=usuarioDto.getDocumento()%></label> <label> | </label>
		<label>Nombres: <%=usuarioDto.getNombres()%>  <%=usuarioDto.getApellidos()%></label> <label> | </label>
		<label>Cargo: <%=usuarioDto.getCargo()%></label><p></p>
		
		<div id="contenedorProductos" >
			<form action="" method="POST" id="form_productos">
				<table align="center" style="font-weight: bold;" width="98%">
					<tr align="center">
						<td colspan="4" >Administración de Productos</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td>Tipo: </td>
<!-- 						<td> <input type="text" name="tipoProducto" id="tipoProducto" maxlength="60"> </td> -->
						<td>
							<select name="tipoProducto" id="tipoProducto">
								<option value="">Seleccione</option>
								<% TiposProductosDTO objTiposProductosDTO;
								for(int i=0; i<listaTiposProductos.size(); i++){
									objTiposProductosDTO = listaTiposProductos.get(i); %>
									<option value="<%=objTiposProductosDTO.getTipo()%>"><%=objTiposProductosDTO.getTipo()%></option>
								<%  } %>
							</select>
						</td>
						<td>código: </td>
						<td> <input type="text" name="codigoProducto" id="codigoProducto" maxlength="10"> </td>
					</tr>
					<tr>
						<td width="20%">Nombre: </td>
						<td colspan="3" width="80%"> <input type="text" name="nombre" id="nombre" style="width: 80%;" maxlength="120"> </td>
					</tr>
					
					<tr >
						<td colspan="2" align="right"> <input type="button" value="Limpiar" id="btnLimpiar"> </td>
						<td colspan="2" align="left"> <input type="button" value="Registrar" id="btnGrabar"> </td>
					</tr>
				</table>	
				<p align="center" style="font-weight: bold;">Lista  de productos</p>
				<table width="98%" cellspacing="0" class="tablaListas">
					<tr>
						<th >Id</th>
						<th >Tipo</th>
						<th >Código</th>
						<th >Nombre</th>
						<th >Eliminar</th>
					</tr>
					<% ProductosDTO objProductosDTO;
						for(int i=0; i<listaProductos.size(); i++){
							objProductosDTO = listaProductos.get(i); %>
							<tr>
				    			<td ><%=objProductosDTO.getIdProducto()%></td>
				    			<td ><%=objProductosDTO.getTipo()%></td>
				    			<td ><%=objProductosDTO.getCodigo()%></td>
				    			<td ><%=objProductosDTO.getNombre()%></td>
				    			<td >
				    				<div align="center">
					  	 				<a onclick="eliminarProductos('<%=objProductosDTO.getIdProducto()%>','<%=objProductosDTO.getNombre()%>');" style="cursor: pointer;">
					  	 					<img id="idImgEliminar" src="./images/imgDelete.png" border="0" title="Eliminar">
					  	 				</a>
						  	 		</div>
						  	 	</td>
			    			</tr>
					<%  } %>
				</table>
			</form>
		</div>
		<div id="salir" style="float: left; width: 100%; text-align: center; padding-top: 20px;">
			<a href="/PruebaUno/login.html">SALIR</a>
		</div>
</body>
</html>