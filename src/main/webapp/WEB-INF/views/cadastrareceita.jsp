<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Cadastrar Receita</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Cadastro de Receitas</h2>

	<form:form method="POST" modelAttribute="receita">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="nome">Nome: </label></td>
				<td><form:input path="nome" id="nome" required="required" /></td>
			</tr>
			<tr>
				<td colspan="3"><c:choose>
						<c:when test="${editar}">
							<input type="submit" value="Atualizar" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Registrar" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br /> Voltar para
	<a href="<c:url value='/receita/listar' />">Lista de Receitas</a>
</body>
</html>