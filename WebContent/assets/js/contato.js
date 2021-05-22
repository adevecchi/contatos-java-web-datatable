/**
 * 
 */

(function(){
	$('.table').DataTable({
		'language': {
            'emptyTable': 'Nenhum registro encontrado',
            'lengthMenu': 'Exibir _MENU_ resultados por página',
            'search': 'Pesquisar',
            'paginate': { 'previous': 'Anterior', 'next': 'Próximo' },
            'zeroRecords': 'Nenhum registro encontrado',
            'info': 'Mostrando de _START_ até _END_ de _TOTAL_ registros',
            'infoEmpty': 'Mostrando 0 até 0 de 0 registros',
            'infoFiltered': '(Filtrados de _MAX_ registros)',
            'infoThousands': '.',
            'loadingRecords': 'Carregando...',
            'processing': 'Processando...',
            'thousands': '.'
        },
        'order': [[0, 'asc']],
        'lengthMenu': [5, 10, 25, 50, 100],
        'pageLength': 5,
        'ordering': false,
        'stateSave': true
	});
	
	$('.nav-link').removeClass('active');
	$('#m-contatos').addClass('active');
	
	$('.btn-add').on('click', function (evt) {
		var $modal = $('#modal-add-update');
		
		$modal
			.find('#title-add-upd')
			.html('Novo Contato');
		$modal.find('form').attr('action', path + '/contatos?cmd=post');
	});
	
	$('.table').on('click', '.btn-edit', function (evt) {
		var $modal = $('#modal-add-update');
		
		$modal
			.find('#title-add-upd')
			.html('Atualizar Contato');
		$modal.find('form').attr('action', path + '/contatos?cmd=put');
		
		$.ajax({
            type: 'POST',
            url: path + '/contatos?cmd=edit',
            data: 'id=' + $(this).data('id'),
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json'
        })
        .done(function (data) {
        	if (data.status) {
        		$('#nome').val(data.contato.nome);
        		$('#email').val(data.contato.email);
        		$('#telefone').val(data.contato.telefone);
        		
        		$modal.find('form').append('<input type="hidden" name="id" value="' + data.contato.id + '">');
        		
        		$modal.modal('show');
        	}
        })
        .fail(function (jqXHR, textStatus) {
        	console.log(textStatus);
        });
	});
	
	$('#modal-add-update').on('submit', 'form', function (evt) {
		evt.preventDefault();
		evt.stopPropagation();
		
		$('.btn-action').prop('disabled', true);
		
		var formData = $($('#modal-add-update').find('form')[0]).serialize();
		
		$.ajax({
			type: 'POST',
		    url: $('#modal-add-update').find('form').attr('action'),
		    data: formData,
		    contentType: 'application/x-www-form-urlencoded',
		    dataType: 'json'
		})
		.done(function (data) {
			if (data.status) {
				$('#modal-add-update').modal('hide');
				location.reload();
			}
		})
		.fail(function (jqXHR, textStatus) {
			console.log(textStatus);
		});
	});
	
	$('#modal-add-update').on('hidden.bs.modal', function (e) {
		var modalForm = $('#modal-add-update').find('form')[0];
		
		modalForm.reset();
	});
	
	$('.table').on('click', '.btn-excluir', function (evt) {
    	$('#frm-excluir').find('input[name=id]').val($(this).data('id'));
    });
	
    $('#modal-excluir').on('submit', 'form', function (evt) {
    	evt.preventDefault();
		evt.stopPropagation();
		
		$('.btn-action').prop('disabled', true);
		
		var formData = $($('#modal-excluir').find('form')[0]).serialize();
		
		$.ajax({
			type: 'POST',
		    url: path + '/contatos',
		    data: formData,
		    contentType: 'application/x-www-form-urlencoded',
		    dataType: 'json'
		})
		.done(function (data) {
			if (data.status) {
				$('#modal-excluir').modal('hide');
				location.reload();
			}
		})
		.fail(function (jqXHR, textStatus) {
			console.log(textStatus);
		});
    });
})(path);