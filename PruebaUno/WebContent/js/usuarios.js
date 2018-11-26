/**
 * 
 */
$(document).ready(function(){

	$("#btnGrabar").click(function(){
		var tipoDoc = $("#tipoDocumento").val();
		var document = $("#documento").val();
		var nombre = $("#nombres").val();
		var apellido = $("#apellidos").val();
		var carg = $("#cargo").val();
		if(tipoDoc==null || tipoDoc==undefined || tipoDoc=='' ){
			alert('Seleccione el tipo de documento');
			return false;
		}
		if(document==null || document==undefined || document=='' ){
			alert('Digite el numero de documento');
			return false;
		}
		if(nombre==null || nombre==undefined || nombre=='' ){
			alert('Digite los nombres');
			return false;
		}
		if(apellido==null || apellido==undefined || apellido=='' ){
			alert('Digite los apellidos');
			return false;
		}
		if(carg==null || carg==undefined || carg=='' ){
			alert('Digite el cargo');
			return false;
		}
		$.ajax({
			type : "post",
			url : 'usuario.jr',
			data : {
				accion : "1", // 1 = GRABAR USUARIOS
				tipoDocumento : tipoDoc,
				documento : document,
				nombres : nombre,
				apellidos : apellido,
				cargo : carg
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
		$("#tipoDocumento").val("");
		$("#documento").val("");
		$("#nombres").val("");
		$("#apellidos").val("");
		$("#cargo").val("");
	});
});

function soloNumeros(e){
	var evento=e.keyCode?e.keyCode:e.which;	
	var commonKeys = (evento==8 || evento==9 ||evento==37 || evento==39 || evento==46);	
	if((48 <= evento && evento <=57) || commonKeys )
		return true;
	else
		return false;
}

function eliminarUsuario(idUsuarioSelect,nombres,apellidos){
	var idUsuario = isNaN(parseInt(idUsuarioSelect)) ? 0 : parseInt(idUsuarioSelect);
	if(confirm('Esta seguro que desea eliminar el usuario: '+nombres+' '+apellidos+'?')){
		$.ajax({
			type : "post",
			url : 'usuario.jr',
			data : {
				accion : "2", // 2 = Eliminar
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
	}
}