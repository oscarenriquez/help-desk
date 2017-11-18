package com.umg.helpdesk.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TicketDto implements Serializable {

	private Long id;
	private String inconveniente;
	private String asunto;
	private Short anio;
	private Short mes;
	private String fechaCreacion;
	private String fechaModificacion;
	private String fechaEstimacion;
	private String tipoSolicitud;
	private String prioridad;
	private String deptoSolicitante;
	private String deptoAsignacion;
	private String estado;
	private String clasificacion;
	private Long estadoId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInconveniente() {
		return inconveniente;
	}
	public void setInconveniente(String inconveniente) {
		this.inconveniente = inconveniente;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Short getAnio() {
		return anio;
	}
	public void setAnio(Short anio) {
		this.anio = anio;
	}
	public Short getMes() {
		return mes;
	}
	public void setMes(Short mes) {
		this.mes = mes;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getFechaEstimacion() {
		return fechaEstimacion;
	}
	public void setFechaEstimacion(String fechaEstimacion) {
		this.fechaEstimacion = fechaEstimacion;
	}
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getDeptoSolicitante() {
		return deptoSolicitante;
	}
	public void setDeptoSolicitante(String deptoSolicitante) {
		this.deptoSolicitante = deptoSolicitante;
	}
	public String getDeptoAsignacion() {
		return deptoAsignacion;
	}
	public void setDeptoAsignacion(String deptoAsignacion) {
		this.deptoAsignacion = deptoAsignacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Long getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
		
}

