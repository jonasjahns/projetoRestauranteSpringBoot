<nav
	class="navbar navbar-toggleable-md navbar-light navbar-inverse bg-inverse">
	<a class="navbar-brand" href="<c:url value="/" />">Módulos</a>
	<div class="collapse navbar-collapse ml-auto">
		<ul class="navbar-nav ml-auto navbar-inverse">
			<li class="nav-item p-3"><a><i
					class="fa fa-user fa-inverse"> ${loggedinuser}</i></a></li>
			<li class="nav-item p-3"><a href="<c:url value="/logout" />"><i
					aria-hidden="true" class="fa fa-sign-out fa-inverse"></i>  Sair</a></li>
		</ul>
	</div>
</nav>