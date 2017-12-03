<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/header.jsp"%>
<style>
#trnivel {
	visibility: hidden;
}
</style>
<script type="text/javascript">
	function yesnoCheck() {
		if (document.getElementById('editavel').value == "1") {
			document.getElementById('trnivel').style.visibility = 'visible';
			document.getElementById('labelvalor').innerHTML = 'Variação: '
		} else {
			document.getElementById('trnivel').style.visibility = 'hidden';
			document.getElementById('labelvalor').innerHTML = 'Valor: '
		}
	}
	jQuery(document)
			.ready(
					function($) {
						$("#grupo")
								.change(
										function() {
											$('#medida').find('option')
													.remove().end()
											var grupoStringId = this.value;
											$
													.ajax({
														type : "GET",
														url : "http://localhost:8081/Boot/receitagrupo/listar/medidas",
														data : {
															"grupoStringId" : grupoStringId
														},
														dataType : 'json',
														timeout : 100000,
														success : function(data) {
															$
																	.each(
																			data,
																			function(
																					key,
																					value) {
																				$(
																						'#medida')
																						.append(
																								$(
																										'<option></option>')
																										.text(
																												value.nome)
																										.attr(
																												'value',
																												value.id));

																			});
														},
														error : function(data) {
															alert(data);
														}
													})
										})

					});
</script>
<title>Cadastrar ReceitaGrupo</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Cadastro de ReceitaGrupo</h2>


	<form:form method="POST" modelAttribute="receitaGrupo">
		<form:input type="hidden" path="id" id="id" />
		<form:input type="hidden" path="valores" id="valores" />
		<table>
			<tr>
				<td><label for="grupo">Grupo: </label></td>
				<td><form:select path="grupo" required="required" id="grupo">
						<c:choose>
							<c:when test="${editar}">
							</c:when>
							<c:otherwise>
								<form:option value="">&nbsp;</form:option>
							</c:otherwise>
						</c:choose>

						<form:options items="${todosGrupos}" itemLabel="nome"
							itemValue="id" />
					</form:select></td>
			</tr>
			<tr>
				<td><label for="medida">Medidas: </label></td>
				<td><form:select path="medida" required="required" id="medida">
						<c:choose>
							<c:when test="${editar}">
								<form:options items="${receitaGrupo.grupo.medidas}"
									itemLabel="nome" itemValue="id" />
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>
					</form:select></td>
			</tr>
			<tr>
				<td>Editável?</td>
				<td><form:select path="editavel" id="editavel"
						required="required" display="none" onclick="yesnoCheck()">
						<option value="1">Sim</option>
						<option value="0" selected>Não</option>
					</form:select></td>
			</tr>
			<tr id="trnivel">
				<td>Níveis:</td>
				<td><form:select path="nivel" id="nivel" required="required">
						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</form:select></td>
			</tr>
			<tr>
				<td><label for="variacao" id="labelvalor">Valor: </label></td>
				<td><form:input path="variacao" id="varicao"
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
	<a href="<c:url value='/receitagrupo/listar' />">Lista de
		ReceitaGrupos</a>
</body>
</html>