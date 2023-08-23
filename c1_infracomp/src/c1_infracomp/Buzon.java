package c1_infracomp;

import java.util.ArrayList;

public class Buzon {
	private ArrayList<Producto> productos; //Está limitada a un producto
	public Buzon()
	{
		this.productos = new ArrayList<Producto>();
	}
	
	public synchronized void ponerProducto(Producto producto)
	{
		productos.add(producto);
		producto.cambiarEstado("DESPACHADO");
		producto.stamp();
		notifyAll();
		while(productos.size() == 1)
		try 
		{
			wait();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public synchronized Producto sacarProducto()
	{
		while(isEmpty())
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
		Producto producto = productos.remove(0);
		notify();
		return producto;
	}
	
	public synchronized void senalFinTrabajo()
	{
		Producto senalFinTrabajo = new Producto(-1);
		senalFinTrabajo.cambiarEstado("FIN TRABAJO");
		productos.add(senalFinTrabajo);
		notify();
		while(productos.size() == 1)
		try 
		{
			wait();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty()
	{
		return productos.isEmpty();
	}
}
