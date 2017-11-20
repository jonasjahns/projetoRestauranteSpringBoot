<html>
<head>
<title>Receitas Cadastradas</title>
<%@ include file="parts/header.jsp"%>
</head>
<%@include file="parts/authheader.jsp"%>
<div class="generic-container">
	<div class="card card-default">
		<div class="card-block">
			<span class="lead">Pedidos</span>
		</div>
		<div class="">
			<table class="table table-hover">
				<thead class="thead-inverse">
					<tr>
						<th>Prato</th>
						<th>Cliente</th>
						<th>Status</th>
						<th>Hora Pedido</th>
						<th>Última Atualização</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pedidos}" var="pedido">
						<tr>
							<td>${pedido.receita.nome}</td>
							<td>${pedido.usuario.nomeCompleto}</td>
							<td>${pedido.nomeStatus}</td>
							<td>${pedido.hora}</td>
							<td>${pedido.ultimoStatus.hora }</td>
							<td><a class="btn btn-danger w-80 float-right ml-1"
								href="<c:url value='/cozinha/pedido-${pedido.id}/atualizar/4' />"><strong>Finalizado</strong></a>
								<a class="btn btn-success w-80 float-right ml-1"
								href="<c:url value='/cozinha/pedido-${pedido.id}/atualizar/2' />"><strong>Preparando</strong></a></td>
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