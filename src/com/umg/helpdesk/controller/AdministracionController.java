package com.umg.helpdesk.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.umg.helpdesk.dao.AdministracionDao;
import com.umg.helpdesk.model.Clasificacion;
import com.umg.helpdesk.model.Departamento;
import com.umg.helpdesk.model.Prioridad;
import com.umg.helpdesk.model.Role;
import com.umg.helpdesk.model.TicketEstado;
import com.umg.helpdesk.model.TipoSolicitud;

@SuppressWarnings("serial")
@ManagedBean(name = "adminView")
@ViewScoped
public class AdministracionController implements Serializable {

	@ManagedProperty("#{adminDao}")
	private AdministracionDao dao;

	private Departamento departamento;
	private Clasificacion clasificacion;

	private List<Departamento> departamentos;
	private List<Clasificacion> clasificaciones;
	private List<Role> roles;
	private List<Prioridad> prioridades;
	private List<TipoSolicitud> tipoSolicitudes;
	private List<TicketEstado> ticketEstados;

	@PostConstruct
	public void init() {
		try {
			setDepartamentos(dao.obtenerListaDepartamentos());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		setDepartamento(new Departamento());
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Clasificacion> getClasificaciones() {
		return clasificaciones;
	}

	public void setClasificaciones(List<Clasificacion> clasificaciones) {
		this.clasificaciones = clasificaciones;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Prioridad> getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(List<Prioridad> prioridades) {
		this.prioridades = prioridades;
	}

	public List<TipoSolicitud> getTipoSolicitudes() {
		return tipoSolicitudes;
	}

	public void setTipoSolicitudes(List<TipoSolicitud> tipoSolicitudes) {
		this.tipoSolicitudes = tipoSolicitudes;
	}

	public List<TicketEstado> getTicketEstados() {
		return ticketEstados;
	}

	public void setTicketEstados(List<TicketEstado> ticketEstados) {
		this.ticketEstados = ticketEstados;
	}

	public void onRowEdit(RowEditEvent event) {
		
		FacesMessage msg = null;
		Departamento departamento = (Departamento) event.getObject();
		try {
			getDao().actualizarDepartamento(departamento);
			setDepartamentos(getDao().obtenerListaDepartamentos());
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento", departamento.getDepartamento());
		} catch (Exception e) {	
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento", e.getMessage());
			e.printStackTrace();
		}
	
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Departamento) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public AdministracionDao getDao() {
		return dao;
	}

	public void setDao(AdministracionDao dao) {
		this.dao = dao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void guardarDepartamento() {
		RequestContext context = RequestContext.getCurrentInstance();
		Boolean isSuccess = false;
		FacesMessage message = null;
		
		try {
			isSuccess = getDao().crearDepartamento(getDepartamento());
			setDepartamentos(getDao().obtenerListaDepartamentos());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento",	e.getMessage());
			e.printStackTrace();
		}
		
		if(isSuccess)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento",	"Nuevo Departamento Creado!!");
		else 
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento",	"Error al crear Departamento!!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("isSuccess", isSuccess);
	}

	public void obtenerDepartamento() {

	}

	public void editarDepartamento() {

	}

	public void eliminarDepartamento() {

	}		

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void guardarClasificacion() {

	}

	public void obtenerClasificacion() {

	}

	public void editarClasificacion() {

	}

	public void eliminarClasificacion() {

	}
	
	private Prioridad prioridad;

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public void guardarPrioridad() {

	}

	public void obtenerPrioridad() {

	}

	public void editarPrioridad() {

	}

	public void eliminarPrioridad() {

	}
	
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void guardarRole() {

	}

	public void obtenerRole() {

	}

	public void editarRole() {

	}

	public void eliminarRole() {

	}
	
	private TicketEstado ticketEstado;

	public TicketEstado getTicketEstado() {
		return ticketEstado;
	}

	public void setTicketEstado(TicketEstado ticketEstado) {
		this.ticketEstado = ticketEstado;
	}

	public void guardarTipoEstado() {

	}

	public void obtenerTipoEstado() {

	}

	public void editarTipoEstado() {

	}

	public void eliminarTipoEstado() {

	}
	
	private TipoSolicitud tipoSolicitud;

	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public void guardarTipoSolicitud() {

	}

	public void obtenerTipoSolicitud() {

	}

	public void editarTipoSolicitud() {

	}

	public void eliminarTipoSolicitud() {

	}

}
