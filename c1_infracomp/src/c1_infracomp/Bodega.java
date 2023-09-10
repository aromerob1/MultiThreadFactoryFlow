package c1_infracomp;

import java.util.ArrayList;

public class Bodega {
	private int tamano;
	private ArrayList<Producto> almacenamiento;

	public Bodega(int tamano) {
		this.tamano = tamano;
		this.almacenamiento = new ArrayList<Producto>();
	}

	public synchronized void almacenarProducto(Producto producto) {
		while (almacenamiento.size() == tamano) {
			try {
				// El repartidor se duerme sobre la bodega
				// Si esta llena, hasta que esta tenga espacio.
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		almacenamiento.add(producto);
		producto.cambiarEstado("EN BODEGA");
		producto.setComment(": Almacenado por productor " + producto.getIdProductor() + ", hay "
				+ almacenamiento.size() + " productos en bodega");
		producto.stamp();
		System.out.println(
				"	\u2022 Productor " + producto.getIdProductor() + " se duerme hasta la entrega del producto "
						+ producto.getId());

		this.notify();

	}

	public Producto retirarProducto() {
		while (true) {
			synchronized (this) {
				if (!isEmpty()) {
					Producto producto = almacenamiento.remove(0);
					producto.cambiarEstado("EN DESPACHO");
					producto.setComment(": Sacado de bodega por despachador, quedan " + almacenamiento.size()
							+ " productos en bodega");
					producto.stamp();
					notify();
					return producto;
				}
			}
		}

	}

	public boolean isEmpty() {
		return almacenamiento.isEmpty();
	}

}
