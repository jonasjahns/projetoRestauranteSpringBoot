<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Cadastrar Ingredientes</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="container-fluid">
		<div id="divRow">
			<div id="divCol">
				<div id="divForm">
					<br> <br>
					<form:form method="POST" modelAttribute="ingrediente">
						<form:input type="hidden" path="id" id="id" />
						<table>
							<tr>
								<td><h3>Criar Ingrediente</h3></td>
							</tr>
							<tr>
								<td><form:input path="nome" id="nome" required="required"
										placeholder="Nome" /></td>
							</tr>
							<tr>
								<td><form:select path="carne" id="carne"
										required="required">
										<option value="" disabled selected>� Carne?</option>
										<option value="0">N�o</option>
										<option value="1">Sim</option>
									</form:select></td>
							</tr>

							<tr>
								<td colspan="3"><c:choose>
										<c:when test="${editar}">
											<input type="submit" value="Atualizar" id="enviar" />
										</c:when>
										<c:otherwise>
											<input type="submit" value="Registrar" id="enviar" />
										</c:otherwise>
									</c:choose></td>
							</tr>

						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<a id="btnVoltar" href="<c:url value='/ingrediente/listar' />">Voltar</a>
</body>
</html>