<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="parts/header.jsp"%>
<title>Pedido código: ${comanda.id}</title>
<script>
	$(document).ready(function() {
		var monkeyList = new List('receitas', {
			valueNames : [ 'name' ]
		});
	});
</script>
<style>
p.name {
	visibility: hidden;
}
</style>
</head>
<body>
	<div class="generic-container"></div>
	<%@include file="parts/authheader.jsp"%>
	<nav
		class="navbar navbar-toggleable-md navbar-light navbar-inverse bg-inverse">
		<div class="collapse navbar-collapse ml-auto">
			<ul class="navbar-nav ml-auto navbar-inverse">
				<li class="nav-item p-3"><a><i class="fa fa-inverse">Hora
							do Peiddo: ${comanda.horaInicialFormatada}</i></a></li>


			</ul>
		</div>
	</nav>
	<div class="container-fluid" id="receitas">
		<div class="row">
			<nav class="sidebar col-sm-2">
				<ul class="nav nav-pills flex-column">
					<li class="nav-item p-3"><a
						href="/Boot/caixa/${comanda.id}/listar"
						class="btn btn-sq-lg btn-primary m-1 input-block-level form-control">
							<i class="fa fa-cutlery fa-5x"></i><br />Novo Item<br>
					</a></li>
					<li><a href="/Boot/caixa/${comanda.id}/pagar/cartao"
						class="btn btn-sq-lg btn-danger m-1 input-block-level form-control">
							<i class="fa fa-cutlery fa-5x"></i> <br /> Pagar!<br>
					</a></li>
				</ul>
			</nav>
			<div class="col-sm-8">
				Buscar: <input type="text" class="fuzzy-search" />
				<ul class="list-inline col-sm-12 list">
					<c:forEach items="${comanda.pedidos}" var="pedido">
						<li class="list-inline-item col-sm-2"><a
							href="/Boot/caixa/${comanda.id}/editar/${pedido.id}"
							class="btn btn-sq-lg btn-success m-1 input-block-level form-control">
								<i class="fa fa-cutlery fa-5x"></i><br />${pedido.receita.nome}<br>
								<br>
						</a>
							<p class="name">${pedido.receita.nome}</p></li>
					</c:forEach>
				</ul>
				<div class="h2 text-danger float-right">Total atual:
					${comanda.total }</div>
			</div>

		</div>

	</div>


</body>
</html>