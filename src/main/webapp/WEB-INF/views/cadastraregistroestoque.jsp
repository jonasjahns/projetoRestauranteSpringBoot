<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Entrada no estoque</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>
	<h2>Entrada de estoque</h2>

	<form:form method="POST" modelAttribute="registroEstoque">
		<form:input type="hidden" path="id" id="id" />

		<table>
			<tr>
				<td><label for="ingrediente">Produto: </label></td>
				<td><form:select path="ingrediente" required="required">
						<form:options items="${todosIngredientes}" itemLabel="nome"
							itemValue="id" />
					</form:select></td>
			</tr>

			<tr>
				<td><label for="quantidadeComprada">Quantidade: </label></td>
				<td><form:input path="quantidadeComprada"
						id="quantidadeComprada" required="required" /></td>
			</tr>

			<tr>
				<td><label for="medida">Medida: </label></td>
				<td><form:select path="medida" required="required">
						<form:options items="${todasMedidas}" itemLabel="nome"
							itemValue="id" />
					</form:select></td>
			</tr>

			<tr>
				<td><label for="valor">Custo: </label></td>
				<td><form:input path="valor" id="valor" required="required" /></td>
			</tr>

			<tr>
				<td><label for="validade">Validade: </label></td>
				<td><form:input path="validade" id="validade"
						required="required" type="date" pattern="dd/MM/yyyy" /></td>
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
	<a href="<c:url value='/estoque/listar' />">Estoque</a>
</body>
</html>