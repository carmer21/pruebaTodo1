<%@page import="com.pruebauno.jsps.dto.TiposDocumentosDTO"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript" src="./js/usuarios.js"></script>
<title>Administración de Usuarios</title>
</head>
<body>
		
	<%  
		UsuarioDTO usuarioDto = (UsuarioDTO)request.getAttribute("usuarioDto");
		ArrayList<UsuarioDTO> listaUsuarios = (ArrayList<UsuarioDTO>)request.getAttribute("lista");
		String password = request.getAttribute("password").toString();
		String documento = request.getAttribute("documento").toString();
		List<TiposDocumentosDTO> listaTiposDoc = (List<TiposDocumentosDTO>)request.getAttribute("listaTiposDoc");
	%>
<!-- <h1 align="center" style="font-weight: bold;" >PRUEBA TECNICA TODO 1</h1> -->
	
<div id="banner"><h1 align="center" style="font-weight: bold;" >PRUEBA TÉCNICA TODO1</h1></div>

		<input type="hidden" name="documentLogin" id="documentLogin" value="<%=documento%>">
		<input type="hidden" name="password" id="password" value="<%=password%>">
	
		<p>Bienvenido USUARIO: <%=usuarioDto.getIdUsuario()%></p>
		<label>Documento: <%=usuarioDto.getTipoDocumento()%> - <%=usuarioDto.getDocumento()%></label> <label> | </label>
		<label>Nombres: <%=usuarioDto.getNombres()%>  <%=usuarioDto.getApellidos()%></label> <label> | </label>
		<label>Cargo: <%=usuarioDto.getCargo()%></label><p></p>
		
		<div id="contenedorUsuarios" >
			<form action="" method="POST" id="form_usuarios">
				
				<table align="center" style="font-weight: bold;" width="98%">
					<tr align="center">
						<td colspan="4" >Administración de Usuarios - Ingresa tus datos</td>
					</tr>
					<tr>
						<td>Tipo de documento: </td>
						<td> 
							<select name="tipoDocumento" id="tipoDocumento">
								<% TiposDocumentosDTO objTiposDocumentos;
								for(int i=0; i<listaTiposDoc.size(); i++){
									objTiposDocumentos = listaTiposDoc.get(i); %>
									<option value="<%=objTiposDocumentos.getCodigo()%>"><%=objTiposDocumentos.getTipoDocumento()%></option>
								<%  } %>
							</select>
						</td>
						<td>Nùmero de documento: </td>
						<td> <input type="text" name="documento" id="documento" onkeypress='return soloNumeros(event)' maxlength="15"> </td>
					</tr>
					<tr>
						<td>Nombres: </td>
						<td> <input type="text" name="nombres" id="nombres" maxlength="120"> </td>
						<td>Apellidos: </td>
						<td> <input type="text" name="apellidos" id="apellidos" maxlength="120"> </td>
					</tr>
					<tr>
						<td width="20%">Cargo: </td>
						<td colspan="3" width="80%"> <input type="text" name="cargo" id="cargo" style="width: 89%;" maxlength="120"> </td>
					</tr>
					<tr >
						<td colspan="2" align="right"> <input type="button" value="Limpiar" id="btnLimpiar"> </td>
						<td colspan="2" align="left"> <input type="button" value="Registrar" id="btnGrabar"> </td>
					</tr>
				</table>	
				<p align="center" style="font-weight: bold;">Lista  de usuarios</p>
				<table width="98%" cellspacing="0" class="tablaListas">
					<tr>
						<th >Id</th>
						<th >Tipo Documento</th>
						<th >Documento</th>
						<th >Nombres</th>
						<th >Apellidos</th>
						<th >Cargo</th>
						<th >Eliminar</th>
					</tr>
					<% UsuarioDTO objusuario;
						for(int i=0; i<listaUsuarios.size(); i++){
							objusuario = listaUsuarios.get(i); %>
							<tr>
				    			<td ><%=objusuario.getIdUsuario()%></td>
				    			<td ><%=objusuario.getTipoDocumento()%></td>
				    			<td ><%=objusuario.getDocumento()%></td>
				    			<td ><%=objusuario.getNombres()%></td>
				    			<td ><%=objusuario.getApellidos()%></td>
				    			<td ><%=objusuario.getCargo()%></td>
				    			<td >
				    				<div align="center">
					  	 				<a onclick="eliminarUsuario('<%=objusuario.getIdUsuario()%>','<%=objusuario.getNombres()%>','<%=objusuario.getApellidos()%>');" style="cursor: pointer;">
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