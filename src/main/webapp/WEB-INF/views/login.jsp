<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>P�gina de Entrada</title>
<%@ include file="parts/header.jsp"%>
</head>

<body>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="well">
					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" class="form-horizontal">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Usu�rio ou senha Inv�lido.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>Conectado</p>
							</div>
						</c:if>
						<div class="input-group">
							<label class="input-group-addon" for="username"><i
								class="fa fa-user"></i></label> <input type="text" class="form-control"
								id="username" name="ssoId" placeholder="Usu�rio" required>
						</div>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="password"><i
								class="fa fa-lock"></i></label> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Senha" required>
						</div>
						<div class="input-group input-sm">
							<div class="checkbox">
								<label><input type="checkbox" id="rememberme"
									name="remember-me"> Remember Me</label>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="form-actions">
							<input type="submit"
								class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>