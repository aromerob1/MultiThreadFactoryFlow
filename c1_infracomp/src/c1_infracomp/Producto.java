package c1_infracomp;

public class Producto {
	@SuppressWarnings("unused")
	private int id;
	private String estado;
	private String log;
	private String comment;

	public Producto(int id) {
		this.id = id;
		this.estado = "";
		this.log = "Producto " + id;
		this.comment = "";
	}

	public void cambiarEstado(String nuevoEstado) {
		this.estado = nuevoEstado;
	}

	public void stamp() {
		this.log = this.log + " -> " + this.estado;
		System.out.println(log + comment);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEstado() {
		return estado;
	}

	public String getLog() {
		return log;
	}

	public String getComment() {
		return comment;
	}

	public int getId() {
		return id;
	}

}
