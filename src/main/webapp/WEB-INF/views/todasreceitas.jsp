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
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th>Receita</th>
							<th>Grupos</th>
							<th></th>
							<th></th>
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
							<td><a
								href="<c:url value='/receita/editar-${receita.id}' />"
								class="btn btn-success w-80 float-right ml-1">Editar</a></td>
							<td><a
								href="<c:url value='/receita/deletar-${receita.id}' />"
								class="btn btn-danger w-80 float-right ml-1">Deletar</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<a id="btnNovo" href="<c:url value='/receita/nova' />"><strong>Nova</strong></a>
		<a id="btnVoltarLista" href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
	</div>
</body>
</html>