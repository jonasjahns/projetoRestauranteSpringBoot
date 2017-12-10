<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Caixa</title>
</head>
<body>
	<div class="generic-container"></div>
	<%@include file="parts/authheader.jsp"%>
	<div class="container row">
		<form:form method="POST" action="novo">
			<button type="submit" class="btn btn-sq-lg btn-success">
				<i class="fa fa-pencil-square-o fa-5x"></i> <br /> Novo Pedido<br>
			</button>
		</form:form>
	</div>


</body>
</html>