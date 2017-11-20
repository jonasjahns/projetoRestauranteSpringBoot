<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Adicionando Grupos</title>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Grupos Adicionados</h2>
	<div class="table-responsive">
		<table class="table table-hover table-bordered">
			<thead class="thead-inverse">
				<tr>
					<th>Grupos</th>
					<th>Remover</th>
				</tr>
			</thead>
			<c:forEach items="${receita.receitaGrupos}" var="receitaGrupo">
				<tr>
					<td>${receitaGrupo.grupo.nome}</td>
					<td><a
						href="<c:url value='/receita/${receita.id}/grupos/deletar-${receitaGrupo.grupo.id}' />">deletar</a></td>
				</tr>
				<tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<a href="<c:url value='/receita/${receita.id}/grupos/novo' />">Criar
		novo Grupo</a>
	<br />
	<a href="<c:url value='/receita/${receita.id}/grupos/selecionar' />">Selecionar
		Grupos existentes</a>
	<br />
	<a href="<c:url value='/receita/${receita.id}/grupos/configurar' />">Configurar
		Receita</a>
</body>
</html>