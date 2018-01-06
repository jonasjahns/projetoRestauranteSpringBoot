<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<%@ include file="parts/header.jsp"%>
</head>

<body>
	<%@include file="parts/authheader.jsp"%>

	<div class="container-fluid">
		<div id="divRow">
			<div id="divCol">
				<div id="divForm">
					<br> <br>
					<form:form method="POST" modelAttribute="user"
						class="form-horizontal">
						<form:input type="hidden" path="id" id="id" />

						<table>
							<tr>
								<td><h3>Cadastrar Usuário</h3></td>
							</tr>
							<tr>
								<td><form:input type="text" path="firstName" id="firstName"
										placeholder="Nome" /></td>
							</tr>
							<tr>
								<td><form:input type="text" path="lastName" id="lastName"
										placeholder="Sobrenome" /></td>
							</tr>
							<tr>
								<td><form:input type="text" path="Cpf" id="Cpf"
										placeholder="CPF" /></td>
							</tr>
							<tr>
								<td><form:input type="password" path="password"
										id="password" placeholder="Senha" /></td>
							</tr>
							<tr>
								<td><form:input type="text" path="email" id="email"
										placeholder="Email" /></td>
							</tr>
							<tr>
								<td><form:select path="usuarioProfile" items="${roles}"
										multiple="true" itemValue="id" itemLabel="tipo"
										class="form-control input-sm" /></td>
							</tr>
							<tr>
								<td><c:choose>
										<c:when test="${edit}">
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
	<a id="btnVoltar" href="<c:url value='/usuario/listar' />">Voltar</a>
</body>
</html>