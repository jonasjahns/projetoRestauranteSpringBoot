<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Medidas Cadastradas</title>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Lista de Conversores</h2>
	<div class="table-responsive">
		<table class="table table-hover table-bordered">
			<thead class="thead-inverse">
				<tr>
					<th>Medida de</th>
					<th>Medida para</th>
					<th>Taxa de conversão</th>
					<th>Ingrediente</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${todosConversormedidas}" var="conversorMedidas">
				<tr>
					<td>${conversorMedidas.de.nome}</td>
					<td>${conversorMedidas.para.nome}</td>
					<td>${conversorMedidas.taxa}</td>
					<td><c:choose>
							<c:when test="${conversorMedidas.padrao}">Todos Ingredientes</c:when>
							<c:otherwise>
							${conversorMedidas.ingrediente.nome}
						</c:otherwise>
						</c:choose></td>
					<td><a
						href="<c:url value='/conversor/editar-${conversorMedidas.id}' />">Editar</a></td>
					<td><a
						href="<c:url value='/conversor/deletar-${conversorMedidas.id}' />">Deletar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />
	<a href="<c:url value='/conversor/novo' />">Adicionar Novo
		Conversor</a>
	<br />
	<a href="<c:url value='/cadastros/' />">Voltar para o Início</a>
</body>
</html>