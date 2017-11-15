package com.umg.helpdesk.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Ticket implements Serializable {

	private Long id;
	private String inconveniente;
	private String asunto;
	private Short anio;
	private Short mes;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Date fechaEstimacion;
	private Long tipoSolicitudId;
	private Long prioridadId;
	private Long deptoSolicitanteId;
	private Long deptoAsignacionId;
	private Long estadoId;
	private Long clasificacionId;
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Date getFechaEstimacion() {
		return fechaEstimacion;
	}
	public void setFechaEstimacion(Date fechaEstimacion) {
		this.fechaEstimacion = fechaEstimacion;
	}
	public Long getTipoSolicitudId() {
		return tipoSolicitudId;
	}
	public void setTipoSolicitudId(Long tipoSolicitudId) {
		this.tipoSolicitudId = tipoSolicitudId;
	}
	public Long getPrioridadId() {
		return prioridadId;
	}
	public void setPrioridadId(Long prioridadId) {
		this.prioridadId = prioridadId;
	}
	public Long getDeptoSolicitanteId() {
		return deptoSolicitanteId;
	}
	public void setDeptoSolicitanteId(Long deptoSolicitanteId) {
		this.deptoSolicitanteId = deptoSolicitanteId;
	}
	public Long getDeptoAsignacionId() {
		return deptoAsignacionId;
	}
	public void setDeptoAsignacionId(Long deptoAsignacionId) {
		this.deptoAsignacionId = deptoAsignacionId;
	}
	public Long getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	public Long getClasificacionId() {
		return clasificacionId;
	}
	public void setClasificacionId(Long clasificacionId) {
		this.clasificacionId = clasificacionId;
	}
	
	
}
