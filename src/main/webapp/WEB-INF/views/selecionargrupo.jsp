<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Cadastrar Grupos</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<form:form method="POST" modelAttribute="gruposSeleciondos">
		<div class="table-responsive">
			<table class="table table-hover table-bordered">
				<thead class="thead-inverse">
					<tr>
						<th>Grupo</th>
						<th>Selecionado</th>
					</tr>
				</thead>
				<tr>
					<td><label for="grupoList">Grupos: </label></td>
					<td><form:select path="grupoList" required="required"
							id="grupo">
							<form:options items="${todosGrupos}" itemLabel="nome"
								itemValue="id" />
						</form:select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Registrar" /></td>
				</tr>
			</table>
		</div>
	</form:form>
	<br /> Voltar para
	<a href="<c:url value='/receita/${receita.id}/grupos/' />">Lista de
		Adicionar Grupos</a>
</body>
</html>