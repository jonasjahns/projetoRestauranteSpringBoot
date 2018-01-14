<html>
<head>
<title>Ingredientes Cadastradas</title>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="card card-default">
			<div class="card-block">
				<span class="lead">Lista de Ingredientes</span>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th>Ingredientes</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<c:forEach items="${ingredientes}" var="ingrediente">
						<tr>
							<td>${ingrediente.nome}</td>
							<td><a
								href="<c:url value='/ingrediente/editar-${ingrediente.id}' />"
								class="btn btn-success w-80 float-right ml-1">Editar</a></td>
							<td><a
								href="<c:url value='/ingrediente/deletar-${ingrediente.id}' />"
								class="btn btn-danger w-80 float-right ml-1">Deletar</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<a id="btnNovo" href="<c:url value='/ingrediente/novo' />"><strong>Nova</strong></a>
		<a id="btnVoltarLista" href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
	</div>
</body>
</html>