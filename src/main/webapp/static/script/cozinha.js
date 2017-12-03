var stompClient = null;

function connect() {
	var socket = new SockJS('/Boot/update-fila');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/update-fila/novo', function(pedido) {
			atualizarPedido(JSON.parse(pedido.body).content);
		});
	});
};

function atualizarPedido(pedido) {
	$("#pedidos-table").DataTable({
		ajax : pedido,
	});
}