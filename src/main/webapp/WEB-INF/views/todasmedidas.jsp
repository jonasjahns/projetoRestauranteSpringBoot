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
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th class="">Medida</th>
							<th class="">Abreviatura</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<c:forEach items="${medidas}" var="medida">
						<tr>
							<td>${medida.nome}</td>
							<td>${medida.abreviatura}</td>
							<td><a href="<c:url value='/medida/deletar-${medida.id}' />"
								class="btn btn-danger w-80 float-right ml-1">Deletar</a></td>
							<td><a href="<c:url value='/medida/editar-${medida.id}' />"
								class="btn btn-success w-80 float-right ml-1">Editar</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<a id="btnNovo" href="<c:url value='/medida/nova' />"><strong>Nova</strong></a>
		<a id="btnVoltarLista" href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
	</div>
</body>
</html>