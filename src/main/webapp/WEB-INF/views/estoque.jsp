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
					<th>Produto</th>
					<th>Medida</th>
					<th>Lançamento Inicial</th>
					<th>Restantes</th>
					<th>Custo</th>
					<th>Validade</th>
					<th>Lançamentos</th>
					<th></th>
				</tr>
				<c:forEach items="${registrosEstoque}" var="registroEstoque">
					<tr>
						<td><a
							href="<c:url value='/estoque/editar-${registroEstoque.id}' />">${registroEstoque.ingrediente.nome}</a></td>
						<td>${registroEstoque.medida.nome}</td>
						<td>${registroEstoque.quantidadeComprada}</td>
						<td>${registroEstoque.disponiveis}</td>
						<td>${registroEstoque.valor}</td>
						<td>${registroEstoque.dataValidade}</td>
						<td><a
							href="<c:url value='/estoque/lancamentos-${registroEstoque.id}' />">Listar</a></td>
						<td><a
							href="<c:url value='/estoque/deletar-${registroEstoque.id}' />">deletar</a></td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<br />
	<a href="<c:url value='/estoque/novo' />">Adicionar Nova Registro</a>
	<br />
	<a href="<c:url value='/' />">Voltar para o Início</a>
</body>
</html>