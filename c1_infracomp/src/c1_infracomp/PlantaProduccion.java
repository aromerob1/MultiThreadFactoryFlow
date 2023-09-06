package c1_infracomp;

import java.util.ArrayList;
import java.util.Scanner;

public class PlantaProduccion {
	private static int nProductores;
	private static int nRepartidores;
	private static int tamanoBodega;
	private static int nTotalProductos;

	private static ArrayList<Productor> productores = new ArrayList<Productor>();
	private static ArrayList<Repartidor> repartidores = new ArrayList<Repartidor>();

	private static int idProducto = 0;

	public static void main(String[] args) {

		// Pedir input por consola
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el numero de productores (N): ");
		nProductores = Integer.parseInt(scanner.nextLine());
		System.out.println("Ingrese el numero de repartidores (M): ");
		nRepartidores = Integer.parseInt(scanner.nextLine());
		System.out.println("Ingrese la capacidad de la bodega (TAM): ");
		tamanoBodega = Integer.parseInt(scanner.nextLine());
		System.out.println("Ingrese el numero total de productos a producir: ");
		nTotalProductos = Integer.parseInt(scanner.nextLine());
		scanner.close();

		// Revisar input
		if (nProductores < 1 || nRepartidores < 1 || tamanoBodega < 1 || nTotalProductos < 1) {
			System.out.println("Error: Todos los valores deben ser mayores a 0");
			return;
		}

		// Crear bodega
		Bodega bodega = new Bodega(tamanoBodega);

		// Crear productores
		int division_entera = nTotalProductos / nProductores;
		int residuo = nTotalProductos % nProductores;

		// Crear productores restantes
		for (int n = 0; n < nProductores; n++) {
			int nProductosCU = (n < residuo) ? division_entera + 1 : division_entera;
			Productor productor = new Productor(nProductosCU, bodega);
			productores.add(productor);
		}

		// Crear despachador
		Despachador despachador = new Despachador(nProductores, nTotalProductos, nRepartidores, bodega);

		// Crear repartidores
		for (int m = 0; m < nRepartidores; m++) {
			Repartidor repartidor = new Repartidor(m, despachador);
			repartidores.add(repartidor);

		}

		// Lanzar productores
		for (Productor productor : productores) {
			productor.start();
		}

		// Lanzar despachador
		despachador.start();

		// Lanzar repartidores
		for (Repartidor repartidor : repartidores) {
			repartidor.start();
		}
	}

	public synchronized static int getNewId() {
		return idProducto++;
	}

}
