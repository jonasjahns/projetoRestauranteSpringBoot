<html>
<head>
<title>ReceitaGrupo Cadastrados</title>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="card card-default">
			<div class="card-block">
				<span class="lead">Lista de Grupos</span>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th>Grupo</th>
							<th>Customizável</th>
							<th>Variações</th>
							<th>Medida</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${receitaGrupos}" var="receitaGrupo">
							<tr>
								<td><a
									href="<c:url value='/receitagrupo/editar-${receitaGrupo.id}' />">${receitaGrupo.id}</a></td>
								<td>${receitaGrupo.grupo.nome}</td>
								<td><c:choose>
										<c:when test="${receitaGrupo.editavel==true}">Sim</c:when>
										<c:otherwise>Não</c:otherwise>
									</c:choose></td>
								<td><select readonly=true>
										<c:forEach items="${receitaGrupo.valores}" var="valor">
											<option value="${valor}">${valor}</option>
										</c:forEach>
								</select></td>
								<td>${receitaGrupo.medida.nome}</td>
								<td><a
									href="<c:url value='/receitagrupo/deletar-${receitaGrupo.id}' />">deletar</a></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<br /> <br /> <a href="<c:url value='/cadastros/' />">Voltar
			para o Início</a>
	</div>
</body>
</html>