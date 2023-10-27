package c1_infracomp;

public class Producto {
	private int id;
	private String estado;
	private String log;
	private String comment;
	private int idProductor;

	public Producto(int id, int idProductor) {
		this.id = id;
		this.estado = "";
		this.log = "Producto " + id;
		this.comment = "";
		this.idProductor = idProductor;
	}

	public void cambiarEstado(String nuevoEstado) {
		this.estado = nuevoEstado;
	}

	public void stamp() {
		this.log = this.log + " -> " + this.estado;
		// Prints log in bold, and comment in normal font
		System.out.println("\033[1m" + this.log + "\033[0m" + this.comment);
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

	public int getIdProductor() {
		return idProductor;
	}

}
