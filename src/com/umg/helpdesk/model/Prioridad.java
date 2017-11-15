package com.umg.helpdesk.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Prioridad implements Serializable {

	private Long id;
	private String descripcion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
