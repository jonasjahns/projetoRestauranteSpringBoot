<html>
<head>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Gerenciadores</span>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-inverse">
						<tr>
							<th class="col-sm-5">Selecione</th>
							<th class="col-sm-1"></th>
						</tr>
					</thead>
					<tr>
						<td>Medidas</td>
						<td><a class="btn btn-success btn-block "
							href="<c:url value='/medida/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Ingredientes</td>
						<td><a class="btn btn-success btn-block"
							href="<c:url value='/ingrediente/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Grupos</td>
						<td><a class="btn btn-success btn-block"
							href="<c:url value='/grupo/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Receitas</td>
						<td><a class="btn btn-success btn-block"
							href="<c:url value='/receita/listar' />">Acessar</a></td>
					</tr>
<%-- 					<tr>
						<td>ReceitaGrupos</td>
						<td><a class="btn btn-success btn-block"
							href="<c:url value='/receitagrupo/listar' />">Acessar</a></td>
					</tr> --%>
					<tr>
						<td>Pedidos</td>
						<td><a class="btn btn-success btn-block"
							href="<c:url value='/pedido/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Usuarios</td>
						<td><a class="btn btn-success btn-block"
							href="<c:url value='/usuario/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Conversor Medidas</td>
						<td><a class="btn btn-success btn-block"
							href="<c:url value='/conversor/listar' />">Acessar</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>