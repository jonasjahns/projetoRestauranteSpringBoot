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
		<table class="table table-hover">
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
						<td>${registroEstoque.ingrediente.nome}</td>
						<td>${registroEstoque.medida.nome}</td>
						<td>${registroEstoque.quantidadeComprada}</td>
						<td>${registroEstoque.disponiveis}</td>
						<td>${registroEstoque.valor}</td>
						<td>${registroEstoque.dataValidade}</td>
						<td><a class="btn btn-success w-80  ml-1"
							href="<c:url value='/estoque/lancamentos-${registroEstoque.id}' />">Listar</a></td>
						<td><a class="btn btn-danger w-80 ml-1"
							href="<c:url value='/estoque/deletar-${registroEstoque.id}' />">deletar</a></td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<a id="btnNovo" href="<c:url value='/estoque/novo' />">Novo</a>
	<a id="btnVoltarLista" href="<c:url value='/' />">Voltar</a>
</body>
</html>