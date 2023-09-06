package c1_infracomp;

public class Repartidor extends Thread {
	private int id;
	private Buzon buzon;

	public Repartidor(int id, Despachador despachador) {
		this.id = id;
		this.buzon = despachador.getBuzon();
	}

	public void entregarProducto(Producto producto) {
		try {
			// sleep random time between 3 and 10 sec
			Thread.sleep((long) (Math.random() * 7000 + 3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean verificarTrabajo(Producto producto) {
		if (producto.getEstado().equals("FIN TRABAJO")) {
			return false;
		}
		return true;
	}

	public void run() {
		boolean trabajar = true;
		while (trabajar) {
			Producto producto = buzon.sacarProducto();
			trabajar = verificarTrabajo(producto);
			if (trabajar) {
				producto.cambiarEstado("EN REPARTO");
				producto.setComment(": Repartidor " + id + " está entregando el producto");
				producto.stamp();
				entregarProducto(producto);
				producto.cambiarEstado("ENTREGADO");
				producto.setComment(": Repartidor " + id + " entregó el producto");
				producto.stamp();
				synchronized (producto) {
					producto.notify();
				}
			}
		}
		System.out.println("	Repartidor " + id + " terminó de trabajar");
	}
}
