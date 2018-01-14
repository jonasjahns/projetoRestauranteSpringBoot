<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/header.jsp"%>
<script>
	jQuery(document).ready(function() {
		$('td > span:first-child > input').attr('checked', true);
		$('select').change(function() {
			var id = $(this).prop('id').match(/\d+/);
			var index = $(this).prop('selectedIndex');
			var id = "#teste" + id + ">select";

			$(id).prop('selectedIndex', index);
		})
	});
</script>
<style>
.escondido {
	display: block;
	line-height: 0;
	height: 0;
	overflow: hidden;
}
</style>
<title>Faça Seu Pedido</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<div class="container-fluid">
		<div id="divRow">
			<div id="divCol">
				<div id="divForm">
					<br> <br>
					<form:form method="POST" modelAttribute="pedido">
						<table>
							<form:input type="hidden" path="id" id="id" />
							<form:input type="hidden" path="receita" id="receita"
								value="${receita.id}" />
							<form:input type="hidden" path="status" id="status" value="1" />
							<c:forEach items="${receita.receitaGrupos}" var="grupo"
								varStatus="i">
								<tr>
									<td><form:select path="valores[${i.index}].ingrediente"
											id="${i.index}" items="${grupo.grupo.ingredientes}"
											itemLabel="nome" itemValue="id" class="select" /></td>
									<td><form:select path="valores[${i.index}].valor"
											items="${grupo.valoresMedida}" class="radio" /></td>
								</tr>
								<div class="escondido" id="teste${i.index}"> <form:select
										path="valores[${i.index}].custo" items="${grupo.valoresPreco}"
										class="teste" type="hidden" />
								</div>
								<form:input type="hidden" path="valores[${i.index}].medida"
									id="medida" value="${grupo.medida.id}" />
							</c:forEach>
							<tr>
								<td colspan="3"><c:choose>
										<c:when test="${editar}">
											<input type="submit" value="Atualizar" id="enviar"/>
										</c:when>
										<c:otherwise>
											<input type="submit" value="Pedir" id="enviar"/>
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
	<a id="btnVoltar" href="<c:url value='/pedido/listar' />">Voltar</a>
</body>
</html>