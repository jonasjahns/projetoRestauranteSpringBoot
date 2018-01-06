<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Pagamento</title>


</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Informe os dados do Cart�o</h2>

	<form:form method="POST" modelAttribute="cartaoDeCredito">
		<table>
			<tr>
				<td><label for="numeroCartao">N�mero do Cart�o: </label></td>
				<td><form:input type="number" path="numeroCartao"
						id="numeroCartao" required="required" /></td>
			</tr>

			<tr>
				<td><label for="dataVencimento">Vencimento: </label></td>
				<td><form:input path="dataVencimento" id="dataVencimento"
						required="required" /></td>
			</tr>

			<tr>
				<td><label for="codigoSeguranca">C�digo de Seguran�a: </label></td>
				<td><form:input path="codigoSeguranca" id="codigoSeguranca"
						required="required" size="3" /></td>
			</tr>

			<tr>
				<td><label for="portador">Nome do Portador: </label></td>
				<td><form:input path="portador" id="portador"
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
</body>
</html>