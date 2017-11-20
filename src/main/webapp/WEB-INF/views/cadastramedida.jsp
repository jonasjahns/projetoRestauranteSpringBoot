<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Cadastrar Medidas</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Cadastro de Medidas</h2>

	<form:form method="POST" modelAttribute="medida">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="nome">Nome: </label></td>
				<td><form:input path="nome" id="nome" required="required" /></td>
			</tr>

			<tr>
				<td><label for="abreviatura">Abreviatura: </label></td>
				<td><form:input path="abreviatura" id="abreviatura"
						required="required" /></td>
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
	<a href="<c:url value='/medida/listar' />">Listar de Medidas</a>
</body>
</html>