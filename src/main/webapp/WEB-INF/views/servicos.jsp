<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<div class="generic-container">
		<%@include file="parts/authheader.jsp"%>
		<br />
		<div class="row">
			<a class="btn btn-danger w-80 float-right ml-1 col"
				href="<c:url value='/home/meuspedidos' />"><strong>Meus
					Pedidos</strong></a>
		</div>

	</div>
</body>
</html>