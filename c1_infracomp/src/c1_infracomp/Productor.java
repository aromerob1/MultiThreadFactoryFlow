package c1_infracomp;

public class Productor extends Thread {
	private int id;
	private int nProductosAProducir;
	private int nProductosProducidos;
	private Bodega bodega;

	public Productor(int id, int nProductosAProducir, Bodega bodega) {
		this.id = id;
		this.nProductosAProducir = nProductosAProducir;
		this.nProductosProducidos = 0;
		this.bodega = bodega;
	}

	public Producto producirProducto() {
		Producto producto = new Producto(PlantaProduccion.getNewId(), id);
		producto.cambiarEstado("PRODUCIDO");
		producto.setComment(": Producido por productor " + id);
		producto.stamp();

		return producto;
	}

	public void run() {
		while (nProductosProducidos < nProductosAProducir) {
			Producto producto = producirProducto();
			nProductosProducidos++;
			bodega.almacenarProducto(producto);
			while (!producto.getEstado().equals("ENTREGADO")) {
				try {
					synchronized (producto) {
						// El repartidor se duerme sobre el producto
						// hasta que este sea entregado.
						producto.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Si quedan productos por producir, el productor se despierta
			if (nProductosProducidos < nProductosAProducir) {
				System.out.println("	Productor " + id + " se despierta");
			}

		}
	}

}
