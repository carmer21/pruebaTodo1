<%@page import="com.pruebauno.jsps.dto.VentasDTO"%>
<%@page import="com.pruebauno.jsps.dto.InventarioProductoDTO"%>
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
<script type="text/javascript" src="./js/ventas.js"></script>
<title>Ventas Todo1</title>
</head>
<body>
		
	<%  
		String password = request.getAttribute("password").toString();
		UsuarioDTO usuarioDto = (UsuarioDTO)request.getAttribute("usuarioDto");
		ArrayList<InventarioProductoDTO> listaInventarioProducto = (ArrayList<InventarioProductoDTO>)request.getAttribute("listaInventarioProducto");
		ArrayList<VentasDTO> listaVentas = (ArrayList<VentasDTO>)request.getAttribute("listaVentas");
	%>
<!-- <h1 align="center" style="font-weight: bold;" >PRUEBA TECNICA TODO 1</h1> -->
	
<div id="banner"><h1 align="center" style="font-weight: bold;" >PRUEBA TÉCNICA TODO1</h1></div>
		<input type="hidden" name="accion3" id="accion3" >
		<input type="hidden" name="documento3" id="documento3" value="<%=usuarioDto.getDocumento()%>">
		<input type="hidden" name="password3" id="password3" value="<%=password%>">
		<input type="hidden" name="idUsuario" id="idUsuario" value="<%=usuarioDto.getIdUsuario()%>">
		<input type="hidden" name="idProducto" id="idProducto" >
		<input type="hidden" name="cantidadDisponible" id="cantidadDisponible" >
		
		<p>Bienvenido USUARIO: <%=usuarioDto.getIdUsuario()%></p>
		<label>Documento: <%=usuarioDto.getTipoDocumento()%> - <%=usuarioDto.getDocumento()%></label> <label> | </label>
		<label>Nombres: <%=usuarioDto.getNombres()%>  <%=usuarioDto.getApellidos()%></label> <label> | </label>
		<label>Cargo: <%=usuarioDto.getCargo()%></label><p></p>
		
		<div id="contenedorProductos" >
			<form action="" method="POST" id="form_inventarioproducto">
				<table align="center" style="font-weight: bold;" width="98%">
					<tr align="center">
						<td colspan="4" >Ventas todo1</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td width="20%">Producto: </td>
						<td colspan="3" width="80%"> 
							<input type="text" name="producto" id="producto" style="width: 84%;" disabled="disabled">
							<img id="imgBuscarProducto" src="./images/imgSearch.jpg" border="0" title="Buscar Productos" style="cursor: pointer;">
						</td>
					</tr>
					<tr>
						<td>Cantidad: </td>
						<td> <input type="text" name="cantidad" id="cantidad" onkeypress='return soloNumeros(event)' maxlength="15"> </td>
						<td>Valor Venta: </td>
						<td> <input type="text" name="valorVenta" id="valorVenta" onkeypress='return soloNumeros(event)' maxlength="15"> </td>
					</tr>
					<tr >
						<td colspan="2" align="right"> <input type="button" value="Limpiar" id="btnLimpiar"> </td>
						<td colspan="2" align="left"> <input type="button" value="Registrar" id="btnGrabar"> </td>
					</tr>
				</table>	
				<p align="center" style="font-weight: bold;">Lista  de Ventas por producto</p>
				<table width="98%" cellspacing="0" class="tablaListas">
					<tr>
						<th >Id</th>
						<th >Producto</th>
						<th >Cantidad</th>
						<th >Valor Venta</th>
						<th >Eliminar</th>
					</tr>
					<% VentasDTO objVentasDTO;
						for(int i=0; i<listaVentas.size(); i++){
							objVentasDTO = listaVentas.get(i); %>
							<tr>
				    			<td ><%=objVentasDTO.getIdVentas()%></td>
				    			<td ><%=objVentasDTO.getProductosDTO().getNombre()%></td>
				    			<td ><%=objVentasDTO.getCantidad()%></td>
				    			<td ><%=objVentasDTO.getValorVenta()%></td>
				    			<td >
				    				<div align="center">
					  	 				<a onclick="eliminarVenta('<%=objVentasDTO.getIdVentas()%>','<%=objVentasDTO.getProductosDTO().getIdProducto()%>','<%=objVentasDTO.getCantidad()%>','<%=objVentasDTO.getProductosDTO().getNombre()%>');" style="cursor: pointer;">
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
		
		<div id="consultarProductos" style="display: none;">
			<p align="center" style="font-weight: bold;">Lista  de Productos Disponibles</p>
				<table width="94%" cellspacing="0" class="tablaListas" style="margin-left: 3%;">
					<tr>
						<th ></th>
						<th >Id</th>
						<th >Tipo</th>
						<th >Código</th>
						<th >Nombre</th>
						<th >Cantidad Disponible</th>
						<th >Valor Unidad</th>
					</tr>
					<% InventarioProductoDTO objInventarioProductoDTO;
						for(int i=0; i<listaInventarioProducto.size(); i++){
							objInventarioProductoDTO = listaInventarioProducto.get(i); %>
							<tr>
								<td >
									<div align="center">
					  	 				<a onclick="seleccionarProducto('<%=objInventarioProductoDTO.getProductoDTO().getIdProducto()%>','<%=objInventarioProductoDTO.getProductoDTO().getTipo()%>','<%=objInventarioProductoDTO.getProductoDTO().getCodigo()%>','<%=objInventarioProductoDTO.getProductoDTO().getNombre()%>','<%=objInventarioProductoDTO.getCantidad()%>');">
					  	 					<img id="idImgEditar" src="./images/imgSelect.jpg" border="0" title="Seleccionar producto">
					  	 				</a>
					  	 			</div>
					  	 		</td>
				    			<td ><%=objInventarioProductoDTO.getProductoDTO().getIdProducto()%></td>
				    			<td ><%=objInventarioProductoDTO.getProductoDTO().getTipo()%></td>
				    			<td ><%=objInventarioProductoDTO.getProductoDTO().getCodigo()%></td>
				    			<td ><%=objInventarioProductoDTO.getProductoDTO().getNombre()%></td>
				    			<td ><%=objInventarioProductoDTO.getCantidad()%></td>
				    			<td ><%=objInventarioProductoDTO.getValorUnidad()%></td>
			    			</tr>
					<%  } %>
				</table>
				<div id="idSalir" style="float: left; width: 100%; text-align: center; padding-top: 20px;">
					<a id="salirConsultaProductos" style="cursor: pointer;">SALIR</a>
				</div>
		</div>
</body>
</html>