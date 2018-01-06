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
	<div class="container-fluid">
		<div id="divRow">
			<div id="divCol">
				<div id="divForm">
					<br> <br>
					<form:form method="POST" modelAttribute="medida" id="formulario">
						<form:input type="hidden" path="id" id="id" />
						<table>
							<tr>
								<td><h3>Criar Medida</h3></td>
							</tr>
							<tr>
								<td><form:input placeholder="Nome" path="nome" id="nome"
										required="required" /></td>
							</tr>

							<tr>
								<td><form:input path="abreviatura" id="abreviatura"
										placeholder="Abreviatura" required="required" /></td>
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
	<a id="btnVoltar" href="<c:url value='/medida/listar' />">Voltar</a>
</body>
</html>