/**
 * 
 */
$(document).ready(function(){
	
	$("#btnGrabar").click(function(){
		var idProducto = isNaN(parseInt($("#idProducto").val())) ? 0 : parseInt($("#idProducto").val());
		var cantidad = isNaN(parseInt($("#cantidad").val())) ? 0 : parseInt($("#cantidad").val()); 
		var valorVenta = isNaN(parseInt($("#valorVenta").val())) ? 0 : parseInt($("#valorVenta").val()); 
		var idUsuario = isNaN(parseInt($("#idUsuario").val())) ? 0 : parseInt($("#idUsuario").val());
		var cantidadDisponible = isNaN(parseInt($("#cantidadDisponible").val())) ? 0 : parseInt($("#cantidadDisponible").val());
		if(idProducto==null || idProducto==undefined || idProducto==0 ){
			alert('Seleccione un producto utilizando la ventana de busqueda de prodcutos');
			return false;
		}
		if(cantidad==null || cantidad==undefined || cantidad==0 ){
			alert('Digite la cantidad de productos a vender');
			return false;
		}
		
		if(cantidad > cantidadDisponible){
			alert('Del producto '+$("#producto").val()+', solo se pueden vender '+cantidadDisponible+' unidades');
			return false;
		}
		
		if(valorVenta==null || valorVenta==undefined || valorVenta==0 ){
			alert('Digite el valor de la venta');
			return false;
		}
		$.ajax({
			type : "post",
			url : 'ventas.jr',
			data : {
				accion4 : "1", // 1 = GRABAR INVENTARIO PRODUCTOS
				idProducto : idProducto,
				cantidad : cantidad,
				valorVenta : valorVenta,
				idUsuario : idUsuario
			},
			dataType : 'text',
			scriptCharset : "UTF-8",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				if (response!=null && response!= undefined && response != "") {
					alert(response);
					location.reload();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('ERROR: '+xhr.status + "--" + ajaxOptions + "--" + thrownError);
			}
		})	
	});
	
	$("#btnLimpiar").click(function(){
		$("#idProducto").val("");
		$("#producto").val("");
		$("#cantidadDisponible").val("");
		$("#cantidad").val("");
		$("#valorVenta").val("");
	});
	
	var posicion = $("#consultarProductos").offset();
	  var margenSuperior = 15;
	  $(window).scroll(function() {
	    if ($(window).scrollTop() > posicion.top) {
	      $("#consultarProductos").stop().animate({
	        marginTop: $(window).scrollTop() - posicion.top + margenSuperior
	      });
	    } else {
	      $("#consultarProductos").stop().animate({
	        marginTop: 0
	      });
	    };
	  });
	  
	  $("#imgBuscarProducto").click(function(){ 
		 $("#consultarProductos").attr('style','display: block');
	  });
	  
	  $("#salirConsultaProductos").click(function(){ 
		  $("#consultarProductos").attr('style','display: none');
	  });
});

function seleccionarProducto(idProducto,tipo,codigo,nombre,cantidad){
	$("#idProducto").val(idProducto);
	$("#cantidadDisponible").val(cantidad);
	$("#producto").val(tipo+' - '+codigo+' - '+nombre);
	$("#consultarProductos").attr('style','display: none');
}

function soloNumeros(e){
	var evento=e.keyCode?e.keyCode:e.which;	
	var commonKeys = (evento==8 || evento==9 ||evento==37 || evento==39 || evento==46);	
	if((48 <= evento && evento <=57) || commonKeys )
		return true;
	else
		return false;
}

function eliminarVenta(idVentaSelect,idProductoSelect,cantidadSelect,nombre){
	var idVenta = isNaN(parseInt(idVentaSelect)) ? 0 : parseInt(idVentaSelect);
	var idProducto = isNaN(parseInt(idProductoSelect)) ? 0 : parseInt(idProductoSelect);
	var cantidad = isNaN(parseInt(cantidadSelect)) ? 0 : parseInt(cantidadSelect);
	if(confirm('Esta seguro que desea eliminar la venta del producto: '+nombre+'?')){
		$.ajax({
			type : "post",
			url : 'ventas.jr',
			data : {
				accion4 : "2", // 2 = Eliminar
				idVenta : idVenta,
				idProducto : idProducto,
				cantidad : cantidad
			},
			dataType : 'text',
			scriptCharset : "UTF-8",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(response) {
				if (response!=null && response!= undefined && response != "") {
					alert(response);
					location.reload();
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('ERROR: '+xhr.status + "--" + ajaxOptions + "--" + thrownError);
			}
		})
	}
}