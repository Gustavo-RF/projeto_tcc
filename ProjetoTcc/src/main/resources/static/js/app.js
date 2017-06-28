$(init);

function init(){
	$('.draggable').draggable({
		connectToSortable: '.drop-container',
      	cursor: 'move',
      	revert: true,
      	helper: 'clone',
      	hoverClass: 'hovered',
      	stop: function(event,ui){
      		$(this).data('id','novotitulo');
      	}
	});
	$('.drop-container').sortable({
        placeholder: 'placeholder',
        activate: function(event, ui){
            $('.drop-container p').remove();
        }
    });
}

$('.salvar').click(function(){
    var valores = new Array();
    
    $('.drop-container .draggable').each(function(){
        valores.push( [$(this).data('type'),$(this).data('opcao')] );
    });
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/resultado",
        data: JSON.stringify(valores),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $("#feedback").html(data);

        },
        error: function (e) {
            var json = "<h4>Ajax Response</h4><pre>" + e.responseText + "</pre>";
            console.log("ERROR : ", e);
        }
    });
    //alert(valores);
});

$(".left-side div").click(function(event){
	var type = $(this).data('type');
	$("#myModal").on('shown.bs.modal',function(e){
		$('.modal-body').html('');
		if(type=='title'){
			$('.modal-body').append(
				'<div class="radio">'+
				  '<label><input type="radio" value="">Maiusculas</label>'+
				'</div>'+
				'<div class="radio">'+
				  '<label><input type="radio" value="">Minusculas</label>'+
				'</div>'
			);
			$("#descricaoElemento").html('');
			$("#descricaoElemento").html(
				'Título do documento. Selecione entre as opções:<br>'+
				'- Título completamente em maiúsculas. Ex: TITULO DO DOCUMENTO<br>'+
				'- Título capitalizado. Ex: Titulo do Documento'
			);
		}
		else if(type=='author'){
			$('.modal-body').append(
				'<div class="radio">'+
				  '<label><input type="radio" value="">Sobrenome Maiusculas,Nome Abreviado</label>'+
				'</div>'+
				'<div class="radio">'+
				  '<label><input type="radio" value="">Sobrenome Maiusculas,Nome completo</label>'+
				'</div>'
			);
			$("#descricaoElemento").html('');
			$("#descricaoElemento").html(
				'Autor do documento. Selecione entre as opções:<br>'+
				'- Sobrenome completamente em maiúsculos e nome abreviado. Ex: AUTOR, N. D.<br>'+
				'- Sobrenome completamente em maiúsculos e nome completo. Ex: AUTOR, Nome do'
			);
		}
	}).modal('show');
})