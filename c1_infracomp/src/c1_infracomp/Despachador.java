package c1_infracomp;


public class Despachador extends Thread{
	@SuppressWarnings("unused")
	private int nProductores;
	private int nProductos;
	private int nRepartidores;
	private Bodega bodega;
	private Buzon buzon;
	private int repartidos;

	public Despachador(int nProductores, int nProductos, int nRepartidores, Bodega bodega) 
	{
		this.nProductores = nProductores;
		this.nProductos = nProductos;
		this.nRepartidores = nRepartidores;
		this.bodega = bodega;
		this.buzon = new Buzon();
		this.repartidos = 0;
	}
	
	public Buzon getBuzon()
	{
		return this.buzon;
	}
	
	
	public void run()
	{
		while(repartidos < nProductos)
		{
			Producto producto = bodega.retirarProducto();
			producto.cambiarEstado("EN DESPACHO");
			producto.stamp();
			buzon.ponerProducto(producto);
			repartidos++;
		}
		for(int r = 0; r < nRepartidores; r++)
		{
			buzon.senalFinTrabajo();
		}
	}

}
