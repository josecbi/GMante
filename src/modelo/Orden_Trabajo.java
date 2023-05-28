package modelo;

public class Orden_Trabajo {
	
	
	
	public Orden_Trabajo(String equipo, String plan_mantenimiento, String tipo_mantenimiento, String ubicacion_equipo, String fecha_inicio,
			String fecha_terminado, String tiempo_taller, String tiempo_preparacion, String tareas, String herramientas,
			String materiales, String tipo_tarea, String observaciones, String empleado, String tarea_realizada) {
		super();
		this.equipo = equipo;
		this.plan_mantenimiento=plan_mantenimiento;
		this.tipo_mantenimiento = tipo_mantenimiento;
		this.ubicacion_equipo = ubicacion_equipo;
		this.fecha_inicio = fecha_inicio;
		this.fecha_terminado = fecha_terminado;
		this.tiempo_taller = tiempo_taller;
		this.tiempo_preparacion = tiempo_preparacion;
		this.tareas = tareas;
		this.herramientas = herramientas;
		this.materiales = materiales;
		this.tipo_tarea = tipo_tarea;
		this.observaciones = observaciones;
		this.empleado = empleado;
		this.tarea_realizada=tarea_realizada;
	}

	

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getTipo_mantenimiento() {
		return tipo_mantenimiento;
	}

	public void setTipo_mantenimiento(String tipo_mantenimiento) {
		this.tipo_mantenimiento = tipo_mantenimiento;
	}

	public String getUbicacion_equipo() {
		return ubicacion_equipo;
	}

	public void setUbicacion_equipo(String ubicacion_equipo) {
		this.ubicacion_equipo = ubicacion_equipo;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_terminado() {
		return fecha_terminado;
	}

	public void setFecha_terminado(String fecha_terminado) {
		this.fecha_terminado = fecha_terminado;
	}

	public String getTiempo_taller() {
		return tiempo_taller;
	}

	public void setTiempo_taller(String tiempo_taller) {
		this.tiempo_taller = tiempo_taller;
	}

	public String getTiempo_preparacion() {
		return tiempo_preparacion;
	}

	public void setTiempo_preparacion(String tiempo_preparacion) {
		this.tiempo_preparacion = tiempo_preparacion;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}	


	public String getMantenimiento() {
		return plan_mantenimiento;
	}



	public void setMantenimiento(String mantenimiento) {
		this.plan_mantenimiento = mantenimiento;
	}
	
	public String getTarea_realizada() {
		
		
		return tarea_realizada;
	}
	
	public void setTarea_realizada(String tarea_realizada) {
		
		
		this.tarea_realizada=tarea_realizada;
	}
	
	
	public String equipo, plan_mantenimiento, tipo_mantenimiento, ubicacion_equipo, fecha_inicio, fecha_terminado, tiempo_taller, 
	
		tiempo_preparacion, tareas, herramientas, materiales, tipo_tarea, observaciones, empleado, tarea_realizada;

}
