package restaurante.events;

import restaurante.model.Pedido;

public class PedidoEvent {

	public PedidoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	private final Pedido pedido;

	public Pedido getPedido() {
		return pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoEvent other = (PedidoEvent) obj;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		return true;
	}

}
