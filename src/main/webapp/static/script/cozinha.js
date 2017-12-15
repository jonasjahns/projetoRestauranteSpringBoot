var stompClient = null;

function connect() {
	var socket = new SockJS('/Boot/update-fila');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/cozinha/socket', function(pedido) {
			atualizarPedido(pedido.body);
		});
	});
};

function atualizarPedido(json) {
	var teste = JSON.parse(json);
	alert(json);
	alert(teste[1].nome);
	var table = $("#pedidos-table").DataTable({
		retrieve : true,
		data : teste,	
		"order" : [ [ 3, "asc" ] ],
		"info" : false,
		"searching" : false,
		"lengthChange" : false,
		"pageLength" : 15,
		columns : [ {
			data : 'nome'
		}, {
			data : 'usuario'
		}, {
			data : 'status'
		}, {
			data : 'data'
		}, {
			data : 'dataStatus'
		}, {
			data : 'botao'
		} ]
	});
	table.clear().draw();
}