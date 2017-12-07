<html>
<head>
<title>Relatório de Vendas</title>
<%@ include file="parts/header.jsp"%>
</head>
<script>
	$(document).ready(function() {

		$('#pedidos-table').DataTable({
			"order" : [ [ 3, "asc" ] ],
			"info" : false,
			"searching" : false,
			"lengthChange" : false,
			"paging" : false,
			dom : 'Bfrtip',
			buttons : [ 'copy', 'excel', 'pdf' ]
		});
	})
</script>
<%@include file="parts/authheader.jsp"%>
<div class="generic-container">
	<div class="card card-default">
		<div class="card-block">
			<span class="lead">Pedidos</span>
		</div>
		<div class="">
			<form:form method="POST" modelAttribute="rangeDatas">
				<label for="dataDe">Data de: </label>
				<form:input path="dataDe" id="dataDe" required="required"
					type="date" pattern="dd/MM/yyyy" />
				<label for="dataAte">Data até: </label>
				<form:input path="dataAte" id="dataAte" required="required"
					type="date" pattern="dd/MM/yyyy" />
				<input type="submit" value="Pesquisar" />
			</form:form>
			<table class="table table-hover" id="pedidos-table">
				<thead class="thead-inverse">
					<tr>
						<th>Prato</th>
						<th>Status</th>
						<th>Data Pedido</th>
						<th>Hora Pedido</th>
						<th>Última Atualização</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pedidos}" var="pedido">
						<tr>
							<td>${pedido.receita.nome}</td>
							<td>${pedido.nomeStatus}</td>
							<td>${pedido.dataFormatada}</td>
							<td>${pedido.hora}</td>
							<td>${pedido.ultimoStatus.hora}</td>
							<td><a
								href="<c:url value='/meuspedidos/cancelar/${pedido.id}' />"
								class="btn btn-danger w-80 float-right ml-1">Cancelar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br />

	<div class="col-3 p-1">
		<a class=" font-weight-bold btn btn-secondary col-sm-4 "
			href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
	</div>
</div>
</body>
</html>