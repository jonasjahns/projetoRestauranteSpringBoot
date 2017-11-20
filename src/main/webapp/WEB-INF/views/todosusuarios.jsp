<html>
<head>
<title>Users List</title>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="card card-default">
			<div class="card-block">
				<span class="lead">Lista de Usuários</span>
			</div>
			<div class="">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th>Nome</th>
							<th>Sobrenome</th>
							<th>Email</th>
							<th>CPF</th>
							<th>Tipo</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th class="w-80"></th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.CPF}</td>
								<c:forEach items="${user.usuarioProfile}" var="profile">
									<td>${profile.tipo}</td>
								</c:forEach>
								<sec:authorize access="hasRole('ADMIN')">
									<td><a
										href="<c:url value='/usuario/deletar/${user.CPF}' />"
										class="btn btn-danger w-80 float-right ml-1">Deletar</a> <a
										href="<c:url value='/usuario/editar/${user.CPF}' />"
										class="btn btn-success w-80 float-right ml-1">Editar</a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="col-sm-3">
			<sec:authorize access="hasRole('ADMIN')">
				<a class="btn btn-primary col-sm-4"
					href="<c:url value='/usuario/novo' />"><strong>Novo</strong></a>
			</sec:authorize>
			<a class=" font-weight-bold btn btn-secondary col-sm-4 "
				href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
		</div>


	</div>
</body>
</html>