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
tr.spaceUnder>td {
	padding-bottom: 3em;
}
</style>
<title>Cadastrar ReceitaGrupo</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Configurando a Receita de ${receita.nome}</h2>


	<form:form method="POST" modelAttribute="receita">
		<form:input type="hidden" path="id" id="id" />
		<form:input type="hidden" path="nome" id="nome" />
		<table>
			<c:forEach items="${receita.receitaGrupos}" var="receitaGrupo"
				varStatus="i">
				<form:input type="hidden" path="receitaGrupos[${i.index}].id" id="id" />
				<tr>
					<td><label for="grupo">Grupo: </label></td>
					<td><form:select path="receitaGrupos[${i.index}].grupo"
							required="required" id="grupo" readonly="true">
							<option value="${receitaGrupo.grupo.id}">${receitaGrupo.grupo.nome}</option>
						</form:select></td>
				</tr>
				<tr>
					<td><label for="medida">Medidas: </label></td>
					<td><form:select path="receitaGrupos[${i.index}].medida"
							required="required" id="medida">
							<form:options items="${receitaGrupo.grupo.medidas}"
								itemLabel="nome" itemValue="id" />
						</form:select></td>
				</tr>
				<tr>
					<td>Editável?</td>
					<td><form:select path="receitaGrupos[${i.index}].editavel"
							id="editavel" required="required" display="none"
							onclick="yesnoCheck()">
							<option value="1">Sim</option>
							<option value="0" selected>Não</option>
						</form:select></td>
				</tr>
				<tr id="trnivel">
					<td>Níveis:</td>
					<td><form:select path="receitaGrupos[${i.index}].nivel"
							id="nivel" required="required">
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</form:select></td>
				</tr>
				<tr class="spaceUnder">
					<td><label for="receitaGrupos[${i.index}].variacao"
						id="labelvalor">Variação: </label></td>
					<td><form:input path="receitaGrupos[${i.index}].variacao"
							id="varicao" required="required" /></td>
				</tr>
			</c:forEach>
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
	<a href="<c:url value='/receita/${receita.id}/grupos/' />">Lista de
		Adicionar Grupos</a>
</body>
</html>