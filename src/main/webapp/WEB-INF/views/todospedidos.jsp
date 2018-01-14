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
		<div class="table-responsive">
			<table class="table table-hover">
				<thead class="thead-inverse">
					<tr>
						<th>Prato</th>
						<th></th>
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
	<br /> <a id="btnNovo" href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
</div>
</body>
</html>