<html>
<head>
<title>Receitas Cadastradas</title>
<%@ include file="parts/header.jsp"%>
</head>
<%@include file="parts/authheader.jsp"%>
<div class="generic-container">
	<div class="card card-default">
		<div class="card-block">
			<span class="lead">Cardápio</span>
		</div>
		<div class="">
			<table class="table table-hover">
				<thead class="thead-inverse">
					<tr>
						<th>Prato</th>
						<th width="80"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${receitas}" var="receita">
						<tr>
							<td>${receita.nome}</td>
							<td><a class="btn btn-success w-80 float-right ml-1"
								href="<c:url value='/pedido/novo/${receita.id}' />"><strong>Pedir</strong></a></td>
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