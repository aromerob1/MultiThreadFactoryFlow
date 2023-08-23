package c1_infracomp;

public class Productor extends Thread{
	private int nProductosAProducir;
	private int nProductosProducidos;
	private Bodega bodega;
	
	public Productor(int nProductosAProducir, Bodega bodega)
	{
		this.nProductosAProducir = nProductosAProducir;
		this.nProductosProducidos = 0;
		this.bodega = bodega;
	}
	
	public Producto producirProducto()
	{
		return new Producto(PlantaProduccion.getNewId());
	}
    
	
	public void run()
	{
		while(nProductosProducidos < nProductosAProducir)
		{
			Producto producto = producirProducto();
			nProductosProducidos++;
			bodega.almacenarProducto(producto);
			while(!producto.getEstado().equals("ENTREGADO"))
			{
				try 
				{
					synchronized (producto) 
					{
						producto.wait();
					}
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

}
