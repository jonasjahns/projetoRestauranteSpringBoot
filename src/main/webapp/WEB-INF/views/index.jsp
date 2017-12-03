<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Inicial</title>
</head>
<body>
	<div class="generic-container">
		<%@include file="parts/authheader.jsp"%>
		<br />
		<div class="table-responsive">
			<table class="table table-hover table-bordered">
				<thead class="thead-inverse">
					<tr>
						<th>Módulos</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Cadastros</td>
						<td><a href="<c:url value='/cadastros/' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Estoque</td>
						<td><a href="<c:url value='/estoque/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Fente de Caixa</td>
						<td><a href="<c:url value='/caixa/inicial' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Relatórios</td>
						<td><a href="<c:url value='/relatorios/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Fila Cozinha</td>
						<td><a href="<c:url value='/cozinha/listar' />">Acessar</a></td>
					</tr>
					<tr>
						<td>Meus Serviços</td>
						<td><a href="<c:url value='/home/' />">Acessar</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>