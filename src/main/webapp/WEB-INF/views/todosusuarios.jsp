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
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th>Nome</th>
							<th>Sobrenome</th>
							<th>Email</th>
							<th>CPF</th>
							<th>Tipo</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.cpf}</td>
								<c:forEach items="${user.usuarioProfile}" var="profile">
									<td>${profile.tipo}</td>
								</c:forEach>
								<td><a href="<c:url value='/usuario/editar/${user.cpf}' />"
									class="btn btn-success w-80 float-right ml-1">Editar</a></td>
								<td><a
									href="<c:url value='/usuario/deletar/${user.cpf}' />"
									class="btn btn-danger w-80 float-right ml-1">Deletar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<a id="btnNovo" href="<c:url value='/usuario/novo' />"><strong>Novo</strong></a>
		<a id="btnVoltarLista" href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
	</div>
</body>
</html>