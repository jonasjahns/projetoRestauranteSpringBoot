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
	var dynatable = $('#pedidos-table').data('dynatable');
	dynatable.records.updateFromJson({
		records : teste
	});
	dynatable.dom.update();
}