package com.umg.helpdesk.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Departamento implements Serializable {

	private Long id;
	private String departamento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
		
}
