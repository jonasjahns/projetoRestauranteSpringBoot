<html>
<head>
<title>Escolha um Item</title>
<%@ include file="parts/header.jsp"%>
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
	<%@include file="parts/authheader.jsp"%>
	<div class="generic-container">
		<div class="card card-default">
			<div class="card-block">
				<span class="lead">Pedidos</span>
			</div>
			<div class="container-fluid row" id="receitas">
				Buscar: <input type="text" class="fuzzy-search" />
				<ul class="list-inline col-sm-12 list">
					<c:forEach items="${receitas}" var="receita">
						<li class="list-inline-item col-sm-2"><a
							href="/Boot/caixa/${comanda.id}/adicionar/${receita.id}"
							class="btn btn-sq-lg btn-success m-1 input-block-level form-control">
								<i class="fa fa-cutlery fa-5x"></i><br />${receita.nome}<br>
						</a>
							<p class="name">${receita.nome}</p></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<br />

		<div class="col-3 p-1">
			<a class=" font-weight-bold btn btn-secondary col-sm-4 "
				href="<c:url value='/cadastros/' />"><strong>Voltar</strong></a>
		</div>
	</div>
</body>
</html>