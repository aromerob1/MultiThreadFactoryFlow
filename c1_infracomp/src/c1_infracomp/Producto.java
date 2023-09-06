package c1_infracomp;

public class Producto {
	private int id;
	private String estado;
	private String log;

	public Producto(int id) {
		this.id = id;
		this.estado = "PRODUCIDO";
		this.log = "Producto " + id + " ->" + " " + this.estado;
		if (this.id != -1) {
			System.out.println(log);
		}
	}

	public void cambiarEstado(String nuevoEstado) {
		this.estado = nuevoEstado;
	}

	public void stamp() {
		this.log = this.log + " -> " + this.estado;
		System.out.println(log);
	}

	public String getEstado() {
		return estado;
	}

	public String getLog() {
		return log;
	}

}
