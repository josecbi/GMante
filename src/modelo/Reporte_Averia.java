package modelo;

public class Reporte_Averia {
	
	public Reporte_Averia(String fecha_reporte, String descripcion, String causas, String equipos,
			String estado_tarea) {
		super();
		this.fecha_reporte = fecha_reporte;
		this.descripcion = descripcion;
		this.causas = causas;
		this.equipos = equipos;
		this.estado_tarea = estado_tarea;
	}



	public String getFecha_reporte() {
		return fecha_reporte;
	}

	public void setFecha_reporte(String fecha_reporte) {
		this.fecha_reporte = fecha_reporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCausas() {
		return causas;
	}

	public void setCausas(String causas) {
		this.causas = causas;
	}

	public String getEquipos() {
		return equipos;
	}

	public void setEquipos(String equipos) {
		this.equipos = equipos;
	}

	public String getEstado_tarea() {
		return estado_tarea;
	}

	public void setEstado_tarea(String estado_tarea) {
		this.estado_tarea = estado_tarea;
	}
	
	
	String fecha_reporte, descripcion, causas, equipos, estado_tarea;

}
