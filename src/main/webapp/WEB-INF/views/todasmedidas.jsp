<html>
<head>
<title>Medidas Cadastradas</title>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="card card-default">
			<div class="card-block">
				<span class="lead">Lista de Medidas</span>
			</div>
			<div class="">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th class="">Medida</th>
							<th class="">Abreviatura</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th class="w-80"></th>
							</sec:authorize>
						</tr>
					</thead>
					<c:forEach items="${medidas}" var="medida">
						<tr>
							<td>${medida.nome}</td>
							<td>${medida.abreviatura}</td>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a
									href="<c:url value='/medida/deletar-${medida.id}' />"
									class="btn btn-danger w-80 float-right ml-1">Deletar</a><a
									href="<c:url value='/medida/editar-${medida.id}' />"
									class="btn btn-success w-80 float-right ml-1">Editar</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div class="col-sm-3 p-1">
			<sec:authorize access="hasRole('ADMIN')">
				<a class="btn btn-primary col-sm-4 "
					href="<c:url value='/medida/nova' />"><strong>Nova</strong></a>
			</sec:authorize>
			<a class=" font-weight-bold btn btn-secondary col-sm-4 "
				href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
		</div>
	</div>
</body>
</html>