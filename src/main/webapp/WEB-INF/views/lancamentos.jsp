<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Registros do Estoque</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Lista de Registros</h2>
	<div class="table-responsive">
		<table class="table table-hover table-bordered">
			<thead class="thead-inverse">
				<tr>
					<th>Data</th>
					<th>Tipo</th>
					<th>Quantidade</th>
				</tr>
			</thead>
			<c:forEach items="${registroEstoque.lancamentos}" var="lancamento">
				<tr>
					<td>${lancamento.dataFormatada}</td>
					<td>${lancamento.tipo.nome}</td>
					<td>${lancamento.quantidadeAbs}${registroEstoque.medida.abreviatura}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<a href="<c:url value='/estoque/listar' />">Voltar</a>
</body>
</html>