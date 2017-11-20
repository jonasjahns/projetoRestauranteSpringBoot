<html>
<head>
<title>Grupos Cadastradas</title>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="card card-default">
			<div class="card-block">
				<span class="lead">Lista de Grupos</span>
			</div>
			<div class="">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th>Grupo</th>
							<th>Ingredientes</th>
							<th>Medidas</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th width="80"></th>
							</sec:authorize>
						</tr>
					</thead>
					<c:forEach items="${grupos}" var="grupo">
						<tr>
							<td>${grupo.nome}</td>
							<td><select readonly="true"
								class="custom-select mb-2 mr-sm-2 mb-sm-0"><c:forEach
										items="${grupo.ingredientes}" var="ingrediente">
										<option value="${ingrediente.id}">${ingrediente.nome}</option>
									</c:forEach></select></td>
							<td><select readonly="true"
								class="custom-select mb-2 mr-sm-2 mb-sm-0"><c:forEach
										items="${grupo.medidas}" var="medida">
										<option value="${medida.id}">${medida.nome}</option>
									</c:forEach></select></td>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a href="<c:url value='/grupo/deletar-${grupo.id}' />"
									class="btn btn-danger w-80 float-right ml-1">Deletar</a> <a
									href="<c:url value='/grupo/editar-${grupo.id}' />"
									class="btn btn-success w-80 float-right ml-1">Editar</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="col-3 p-1">
			<sec:authorize access="hasRole('ADMIN')">
				<a class="btn btn-primary col-sm-4 "
					href="<c:url value='/grupo/novo' />"><strong>Novo</strong></a>
			</sec:authorize>
			<a class=" font-weight-bold btn btn-secondary col-sm-4 "
				href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
		</div>
	</div>
</body>
</html>