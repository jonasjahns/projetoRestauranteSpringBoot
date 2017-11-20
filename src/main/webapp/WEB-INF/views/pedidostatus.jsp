<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Receitas Cadastradas</title>

<style>
tr:first-child {
	font-weight: bold;
	background-color: #C6C9C4;
}
</style>
<%@ include file="parts/header.jsp"%>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Lista de Receitas</h2>
	<table>
		<tr>
			<td>Receita</td>
			<td>Status</td>
			<td>Ingrediente</td>
			<td>Valor</td>
		</tr>
		<tr>
			<c:forEach var="pedido" items="${pedidos}">
				<td>${pedido.receita.nome}</td>
				<td>${pedido.status}</td>
				<td><c:forEach var="listValue" items="${pedido.valores}">
						<c:out value="${listValue.nome}" />
						<br />
					</c:forEach></td>
				<td><c:forEach var="listValue" items="${pedido.valores}">
						<c:out value="${listValue.valor}" />
						<br />
					</c:forEach></td>
		</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='/' />">Voltar para o Início</a>

</body>
</html>