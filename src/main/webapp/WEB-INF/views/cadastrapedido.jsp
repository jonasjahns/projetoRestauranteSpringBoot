<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/header2.jsp"%>
<script>
	jQuery(document).ready(function() {
		jQuery('.radio').on('click', function() {

			// Get the element index , which one we click on
			var indx = jQuery(this).index('.radio');

			// Trigger a click on the same index in the second radio set

			jQuery('.teste')[indx].click();
		})
	});
</script>
<style>
td.escondido {
	visibility: hidden;
}
</style>
<title>Faça Seu Pedido</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Pedido de ${receita.nome}</h2>

	<form:form method="POST" modelAttribute="pedido">
		<table>
			<form:input type="hidden" path="id" id="id" />
			<form:input type="hidden" path="receita" id="receita"
				value="${receita.id}" />
			<form:input type="hidden" path="status" id="status" value="1" />
			<c:forEach items="${receita.receitaGrupos}" var="grupo" varStatus="i">
				<tr>
					<td><label for="nome">Nome: ${grupo.grupo.nome} </label></td>
					<td><form:select path="valores[${i.index}].ingrediente"
							id="id" items="${grupo.grupo.ingredientes}" itemLabel="nome"
							itemValue="id" /></td>
					<td><form:radiobuttons path="valores[${i.index}].valor"
							items="${grupo.valoresMedida}" class="radio" /></td>
					<td class="escondido"><form:radiobuttons
							path="valores[${i.index}].custo" items="${grupo.valoresPreco}"
							class="teste" /></td>
					<form:input type="hidden" path="valores[${i.index}].medida"
						id="medida" value="${grupo.medida.id}" />
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
	<a href="<c:url value='/pedido/listar' />">Voltar para Receitas</a>
</body>
</html>