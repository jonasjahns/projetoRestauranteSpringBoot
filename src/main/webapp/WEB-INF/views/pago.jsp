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
	<h2>Transação Efetuada!</h2>
	<p>Status = ${resposta.status}</p>
	<p>Reason Code = ${resposta.reasonCode}</p>
	<p>Mensagem = ${resposta.reasonMessage }</p>
	<a href="/Boot/caixa/"
		class="btn btn-sq-lg btn-primary m-1 input-block-level form-control">
		<i class="fa fa-cutlery fa-5x"></i> <br /> Página Inicial<br>
	</a>
</body>
</html>