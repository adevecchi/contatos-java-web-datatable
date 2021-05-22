<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/header.jsp" />
	<div class="container">
		<div class="font-weight-bold mt-3 shadow p-3 mb-4 bg-light rounded">
			Lista de Contatos
		</div>
        
		<div class="mb-3">
			<button type="button" class="btn btn-primary btn-add" data-toggle="modal" data-target="#modal-add-update">
				Adicionar
			</button>
		</div>
        
        <table class="table table-hover">
        	<thead>
            	<tr>
                	<th>#</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Telefone</th>
                    <th width="5%">&nbsp;</th>
                    <th width="5%">&nbsp;</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="contato" items="${contatos}">
            	<tr>
                	<td><c:out value="${contato.id}" /></td>
                    <td><c:out value="${contato.nome}" /></td>
                    <td><c:out value="${contato.email}" /></td>
                    <td><c:out value="${contato.telefone}" /></td>
                    <td>
                    	<button type="button" class="btn btn-info btn-sm btn-edit" data-id="<c:out value='${contato.id}' />">
                    		Editar
                    	</button>
                    </td>
                    <td>
                    	<button type="button" class="btn btn-danger btn-sm btn-excluir" data-id="<c:out value='${contato.id}' />" data-toggle="modal" data-target="#modal-excluir">
                            Excluir
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="modal fade" id="modal-add-update" tabindex="-1" aria-labelledby="addUpdateLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 id="title-add-upd" class="modal-title"></h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <form autocomplete="nope">
		            <div class="modal-body">
	                	<div class="form-group">
		                    <label for="nome">Nome</label>
		                    <input type="text" class="form-control" id="nome" name="nome" required>
		                </div>
		
		                <div class="form-group">
		                    <label for="email">Email</label>
		                    <input type="email" class="form-control" id="email" name="email" required>
		                </div>
		
		                <div class="form-group">
		                    <label for="telefone">Telefone</label>
		                    <input type="text" class="form-control" id="telefone" name="telefone" required maxlength="15">
		                </div>
		            </div>
		            <div class="modal-footer">
		            	<button type="submit" class="btn btn-success btn-action">Salvar</button>
		                <button type="button" class="btn btn-secondary btn-action" data-dismiss="modal">Cancelar</button>
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
    
    <div class="modal fade" id="modal-excluir" tabindex="-1" aria-labelledby="excluirLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title">Excluir</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <p>Deseja excluir o contato?</p>
	            </div>
	            <div class="modal-footer">
	                <form id="frm-excluir">
	                	<input type="hidden" name="cmd" value="del">
	                	<input type="hidden" name="id" value="">
	                    <button type="submit" class="btn btn-danger btn-action">Sim</button>
	                </form>
	                <button type="button" class="btn btn-secondary btn-action" data-dismiss="modal">Não</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" crossorigin></script>
	<script src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js" crossorigin></script>
	<script>
		var path = '<%= request.getContextPath() %>';
	</script>
	<script src="assets/js/contato.js"></script>
<jsp:include page="/includes/footer.jsp" />