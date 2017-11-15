package com.umg.helpdesk.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Evaluacion implements Serializable {

	private Long id;
	private Long ticketId;
	private Integer calidadServicio;
	private String observaciones;
	private Date fechaCreacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public Integer getCalidadServicio() {
		return calidadServicio;
	}
	public void setCalidadServicio(Integer calidadServicio) {
		this.calidadServicio = calidadServicio;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
		
}
