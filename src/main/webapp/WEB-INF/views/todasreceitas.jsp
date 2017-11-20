<html>
<head>
<title>Receitas Cadastradas</title>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="card card-default">
			<div class="card-block">
				<span class="lead">Lista de Receitas</span>
			</div>
			<div class="">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th>Receita</th>
							<th>Grupos</th>
							<sec:authorize access="hasRole('ADMIN')">
								<th width="80"></th>
							</sec:authorize>
						</tr>
					</thead>
					<c:forEach items="${receitas}" var="receita">
						<tr>
							<td>${receita.nome}</td>
							<td><select readonly="true"
								class="custom-select mb-2 mr-sm-2 mb-sm-0"><c:forEach
										items="${receita.receitaGrupos}" var="receitaGrupo">
										<option value="${receitaGrupo.id}">${receitaGrupo.grupo.nome}</option>
									</c:forEach></select></td>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a
									href="<c:url value='/receita/deletar-${receita.id}' />"
									class="btn btn-danger w-80 float-right ml-1">Deletar</a> <a
									href="<c:url value='/receita/editar-${receita.id}' />"
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
					href="<c:url value='/receita/nova' />"><strong>Nova</strong></a>
			</sec:authorize>
			<a class=" font-weight-bold btn btn-secondary col-sm-4 "
				href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
		</div>
	</div>
</body>
</html>