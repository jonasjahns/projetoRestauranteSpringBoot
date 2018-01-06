<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/header.jsp"%>
<style>
#tringrediente {
	visibility: hidden;
}
</style>
<script type="text/javascript">
	function yesnoCheck() {
		if (document.getElementById('padrao').value == "0") {
			document.getElementById('tringrediente').style.visibility = 'visible';
		} else {
			document.getElementById('tringrediente').style.visibility = 'hidden';
		}
	}
</script>
<title>Cadastrar Conversores de Medidas</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="container-fluid">
		<div id="divRow">
			<div id="divCol">
				<div id="divForm">
					<br> <br>
					<form:form method="POST" modelAttribute="conversorMedidas">
						<form:input type="hidden" path="id" id="id" />

						<table>
							<tr>
								<td><h3>Criar Conversor</h3></td>
							</tr>
							<tr>
								<td><form:select path="de" required="required">
										<option value="" disabled selected>Medida de:</option>
										<form:options items="${todasMedidas}" itemLabel="nome"
											itemValue="id" />
									</form:select></td>
							</tr>

							<tr>
								<td><form:select path="para" required="required">
										<option value="" disabled selected>Medida para:</option>
										<form:options items="${todasMedidas}" itemLabel="nome"
											itemValue="id" />
									</form:select></td>
							</tr>

							<tr>
								<td><form:select path="padrao" required="required"
										id="padrao" onclick="yesnoCheck()">
										<option value="" disabled selected>É Padrão?</option>
										<option value="1">Sim</option>
										<option value="0">Não</option>
									</form:select></td>
							</tr>

							<tr>
								<td><form:input path="taxa" id="taxa" required="required"
										placeholder="Taxa de Conversão" /></td>
							</tr>

							<tr id="tringrediente">
								<td><form:select path="ingrediente" id="ingrediente">
										<option value="" disabled>Ingrediente</option>
										<form:option value="" selected="true">&nbsp;</form:option>
										<form:options items="${todosIngredientes}" itemLabel="nome"
											itemValue="id" />
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
	<a id="btnVoltar" href="<c:url value='/conversor/listar' />">Voltar</a>
</body>
</html>