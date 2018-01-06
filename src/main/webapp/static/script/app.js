function setFormulario() {
	$("#divRow").addClass("row justify-content-center align-items-center");
	$("#divCol").addClass("col col-sm-6 col-md-6 col-lg-4 col-xl-3");
	$("#divForm").addClass("info-form");
	$("#enviar").addClass("btn btn-info btn-lg btn-block");
	$("form > table > tbody > tr > td > input").addClass(
			"form-control form-control-lg");
	$("form > table > tbody > tr > td > select").addClass(
			"form-control form-control-lg");
	$("form > table > tbody > tr > td > span> input").addClass(
			"form-control form-control-lg radio-inline");
	$("form > table > tbody > tr > td > span ").addClass("radio-inline");
	$("#btnVoltar")
			.addClass(
					"btn btn-success btn-lg btn-block col-4 col-sm-4 col-md-4 col-lg-1 col-xl-1");
	$("body").fadeIn(0);
}