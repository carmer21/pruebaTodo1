/**
 * 
 */
$(document).ready(function(){
	
	$("#btnGrabar").click(function(){
		var tipoProducto = $("#tipoProducto").val();
		var codigoProducto = $("#codigoProducto").val();
		var nombre = $("#nombre").val();
		if(tipoProducto==null || tipoProducto==undefined || tipoProducto=='' ){
			alert('Seleccione el tipo de producto');
			return false;
		}
		if(codigoProducto==null || codigoProducto==undefined || codigoProducto=='' ){
			alert('Digite el codigo del producto');
			return false;
		}
		if(nombre==null || nombre==undefined || nombre=='' ){
			alert('Digite el nombre del producto');
			return false;
		}
		$.ajax({
			type : "post",
			url : 'productos.jr',
			data : {
				accion2 : "1", // 1 = GRABAR PRODUCTOS
				tipoProducto : tipoProducto,
				codigoProducto : codigoProducto,
				nombre : nombre
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
		$("#tipoProducto").val("");
		$("#codigoProducto").val("");
		$("#nombre").val("");
	});
});

function eliminarProductos(idProductoSelect,nombres){
	var idProductos = isNaN(parseInt(idProductoSelect)) ? 0 : parseInt(idProductoSelect);
	if(confirm('Esta seguro que desea eliminar el producto: '+nombres+'?')){
		$.ajax({
			type : "post",
			url : 'productos.jr',
			data : {
				accion2 : "2", // 2 = Eliminar
				idProductos : idProductos
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