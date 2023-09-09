package c1_infracomp;

import java.util.ArrayList;

public class Buzon {
	private ArrayList<Producto> productos; // Está limitada a un producto

	public Buzon() {
		this.productos = new ArrayList<Producto>();
	}

	public synchronized void ponerProducto(Producto producto) {
		productos.add(producto);
		producto.cambiarEstado("ENTREGADO");
		producto.setComment(": El despachador entregó el paquete y ahora está libre");
		producto.stamp();
		notifyAll();
		while (productos.size() == 1)
			try {
				// Si el buzon está lleno, el despachador debe esperar
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public synchronized Producto sacarProducto() {
		while (isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Producto producto = productos.remove(0);
		this.notify();
		return producto;
	}

	public synchronized void senalFinTrabajo() {
		Producto senalFinTrabajo = new Producto(-1, -1);
		senalFinTrabajo.cambiarEstado("FIN TRABAJO");
		productos.add(senalFinTrabajo);
		notifyAll();
		while (productos.size() == 1)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public boolean isEmpty() {
		return productos.isEmpty();
	}
}
