package c1_infracomp;

import java.util.ArrayList;

public class Bodega {
	private int tamano;
	private ArrayList<Producto> almacenamiento;

	public Bodega(int tamano) 
	{
		this.tamano = tamano;
		this.almacenamiento = new ArrayList<Producto>();
	}
	
	public synchronized void almacenarProducto(Producto producto)
	{
		while(almacenamiento.size() == tamano)
		{
			try 
			{
				wait();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		almacenamiento.add(producto);
		producto.cambiarEstado("EN BODEGA");
		producto.stamp();
		notify();
	}
	
	public Producto retirarProducto()
	{
		while(true)
		{
			synchronized (this) 
			{
				if(!isEmpty())
				{
					Producto producto = almacenamiento.remove(0);
					notify();
					return producto;
				}
			}
		}
	}
	
	public boolean isEmpty()
	{
		return almacenamiento.isEmpty();
	}

}
