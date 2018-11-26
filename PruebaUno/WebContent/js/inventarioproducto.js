/**
 * 
 */
$(document).ready(function(){
	
	$("#btnGrabar").click(function(){
		var idProducto = isNaN(parseInt($("#idProducto").val())) ? 0 : parseInt($("#idProducto").val());
		var cantidad = isNaN(parseInt($("#cantidad").val())) ? 0 : parseInt($("#cantidad").val()); 
		var valorUnidad = isNaN(parseInt($("#valorUnidad").val())) ? 0 : parseInt($("#valorUnidad").val()); 
		if(idProducto==null || idProducto==undefined || idProducto==0 ){
			alert('Seleccione el producto utilizando la ventana de busqueda de productos');
			return false;
		}
		if(cantidad==null || cantidad==undefined || cantidad==0 ){
			alert('Digite la cantidad producto a registrar en el inventario');
			return false;
		}
		if(valorUnidad==null || valorUnidad==undefined || valorUnidad==0 ){
			alert('Digite el valro por unidad del producto');
			return false;
		}
		$.ajax({
			type : "post",
			url : 'inventarioproducto.jr',
			data : {
				accion3 : "1", // 1 = GRABAR INVENTARIO PRODUCTOS
				idProducto : idProducto,
				cantidad : cantidad,
				valorUnidad : valorUnidad
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
		$("#cantidad").val("");
		$("#valorUnidad").val("");
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

function seleccionarProducto(idProducto,tipo,codigo,nombre){
	$("#idProducto").val(idProducto);
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

function eliminarInventario(idInventarioProductoSelect,nombre){
	var idInventariProducto = isNaN(parseInt(idInventarioProductoSelect)) ? 0 : parseInt(idInventarioProductoSelect);
	if(confirm('Esta seguro que desea eliminar el inventario del producto: '+nombre+'?')){
		$.ajax({
			type : "post",
			url : 'inventarioproducto.jr',
			data : {
				accion3 : "2", // 2 = Eliminar
				idInventariProducto : idInventariProducto
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