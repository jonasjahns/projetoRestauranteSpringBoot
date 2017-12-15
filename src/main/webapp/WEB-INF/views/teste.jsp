<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Faça Seu Pedido</title>
</head>
<body>
	<%@include file="parts/authheader.jsp"%>

	<form method="POST" name="pedido" action="/Boot/medidas" enctype="application/x-www-form-urlencoded">
		<input name="nome" /> <input name="abreviatura" /> <input
			type="submit" />
	</form>
	<br />
	<br /> Voltar para
	<a href="<c:url value='/pedido/listar' />">Voltar para Receitas</a>
</body>
</html>