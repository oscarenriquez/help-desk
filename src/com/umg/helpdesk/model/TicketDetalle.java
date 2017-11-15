package com.umg.helpdesk.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TicketDetalle implements Serializable {

	private Long id;
	private Long ticketId;
	private Integer anio;
	private Integer mes;
	private Date fechaCreacion;
	private String comentario;
	private String accion;
	private String usuarioAccionId;
	
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
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getUsuarioAccionId() {
		return usuarioAccionId;
	}
	public void setUsuarioAccionId(String usuarioAccionId) {
		this.usuarioAccionId = usuarioAccionId;
	}
		
}
