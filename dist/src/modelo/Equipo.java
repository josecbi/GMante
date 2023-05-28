package modelo;

public class Equipo {	

	public Equipo(String nombre, String marca, String modelo, String codigo, String numero, String estado,
			String ubicacion, String manual, String foto) {
		
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.modelo = modelo;
		this.codigo = codigo;
		this.numero = numero;
		this.estado = estado;
		this.ubicacion = ubicacion;
		this.manual = manual;
		this.foto = foto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String nombre, marca, modelo, codigo, numero, estado, ubicacion, manual, foto;

}
