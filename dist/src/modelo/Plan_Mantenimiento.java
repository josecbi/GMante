package modelo;

import java.util.Date;

public class Plan_Mantenimiento {	
	
	public Plan_Mantenimiento(String fecha_inicio, String codigo_equipo, String tipo_mantenimiento, String tareas,
			String herramientas, String materiales, String tipo_tarea, String tarea_realizada, String frecuencia,
			String tiempo_preparacion, String tiempo_estimado, String tiempo_real, int frecuencia_total) {
		
		this.fecha_inicio = fecha_inicio;
		this.codigo_equipo = codigo_equipo;
		this.tipo_mantenimiento = tipo_mantenimiento;
		this.tareas = tareas;
		this.herramientas = herramientas;
		this.materiales = materiales;
		this.tipo_tarea = tipo_tarea;
		this.tarea_realizada = tarea_realizada;
		this.frecuencia = frecuencia;
		this.tiempo_preparacion = tiempo_preparacion;
		this.tiempo_estimado = tiempo_estimado;
		this.tiempo_real = tiempo_real;
		this.frecuencia_total = frecuencia_total;
	}
	
	
	

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getCodigo_equipo() {
		return codigo_equipo;
	}

	public void setCodigo_equipo(String codigo_equipo) {
		this.codigo_equipo = codigo_equipo;
	}

	public String getTipo_mantenimiento() {
		return tipo_mantenimiento;
	}

	public void setTipo_mantenimiento(String tipo_mantenimiento) {
		this.tipo_mantenimiento = tipo_mantenimiento;
	}

	public String getTareas() {
		return tareas;
	}

	public void setTareas(String tareas) {
		this.tareas = tareas;
	}

	public String getHerramientas() {
		return herramientas;
	}

	public void setHerramientas(String herramientas) {
		this.herramientas = herramientas;
	}

	public String getMateriales() {
		return materiales;
	}

	public void setMateriales(String materiales) {
		this.materiales = materiales;
	}

	public String getTipo_tarea() {
		return tipo_tarea;
	}

	public void setTipo_tarea(String tipo_tarea) {
		this.tipo_tarea = tipo_tarea;
	}

	public String getTarea_realizada() {
		return tarea_realizada;
	}

	public void setTarea_realizada(String tarea_realizada) {
		this.tarea_realizada = tarea_realizada;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getTiempo_preparacion() {
		return tiempo_preparacion;
	}

	public void setTiempo_preparacion(String tiempo_preparacion) {
		this.tiempo_preparacion = tiempo_preparacion;
	}

	public String getTiempo_estimado() {
		return tiempo_estimado;
	}

	public void setTiempo_estimado(String tiempo_estimado) {
		this.tiempo_estimado = tiempo_estimado;
	}

	public String getTiempo_real() {
		return tiempo_real;
	}

	public void setTiempo_real(String tiempo_real) {
		this.tiempo_real = tiempo_real;
	}
	
	
	
	
	
	public String fecha_inicio;
	
	public String codigo_equipo, tipo_mantenimiento, tareas, herramientas, materiales, tipo_tarea, tarea_realizada;
	
	public String frecuencia, tiempo_preparacion, tiempo_estimado, tiempo_real;
	
	public int frecuencia_total;

}
