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
	private Role role;
	private Prioridad prioridad;
	private TicketEstado ticketEstado;
	private TipoSolicitud tipoSolicitud;

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
			setClasificaciones(dao.obtenerListaClasificaciones());
			setRoles(dao.obtenerListaRoles());
			setPrioridades(dao.obtenerListaPrioridades());
			setTipoSolicitudes(dao.obtenerListaTipoSolicitudes());
			setTicketEstados(dao.obtenerListaTicketEstados());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDepartamento(new Departamento());
		setClasificacion(new Clasificacion());
		setRole(new Role());
		setPrioridad(new Prioridad());
		setTipoSolicitud(new TipoSolicitud());
		setTicketEstado(new TicketEstado());
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
	
	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public TicketEstado getTicketEstado() {
		return ticketEstado;
	}

	public void setTicketEstado(TicketEstado ticketEstado) {
		this.ticketEstado = ticketEstado;
	}

	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}	
	
	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public void onRowEdit(RowEditEvent event) {
			
		Object object = event.getObject();			
		try {
			if(object instanceof Departamento) {
				Departamento departamento = (Departamento) event.getObject();
				getDao().actualizarDepartamento(departamento);
				setDepartamentos(getDao().obtenerListaDepartamentos());		
			} else if (object instanceof Clasificacion) {
				Clasificacion clasificacion = (Clasificacion) object;
				getDao().actualizarClasificacion(clasificacion);
				setClasificaciones(getDao().obtenerListaClasificaciones());
			} else if (object instanceof Prioridad) {
				Prioridad prioridad = (Prioridad) object;
				getDao().actualizarPrioridad(prioridad);
				setPrioridades(getDao().obtenerListaPrioridades());
			} else if (object instanceof Role) {
				Role role = (Role) object;
				getDao().actualizarRole(role);
				setRoles(getDao().obtenerListaRoles());
			} else if (object instanceof TicketEstado) {
				TicketEstado estado = (TicketEstado) object;
				getDao().actualizarTicketEstado(estado);
				setTicketEstados(getDao().obtenerListaTicketEstados());
			} else if (object instanceof TipoSolicitud) {
				TipoSolicitud tipoSolicitud = (TipoSolicitud) object;
				getDao().actualizarTipoSolicitud(tipoSolicitud);
				setTipoSolicitudes(getDao().obtenerListaTipoSolicitudes());
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Departamento) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void guardarDepartamento() {
		RequestContext context = RequestContext.getCurrentInstance();
		Boolean isSuccess = false;
		FacesMessage message = null;

		try {
			isSuccess = getDao().crearDepartamento(getDepartamento());
			setDepartamentos(getDao().obtenerListaDepartamentos());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento", e.getMessage());
			e.printStackTrace();
		}

		if (isSuccess)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento", "Nuevo Departamento Creado!!");
		else
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamento", "Error al crear Departamento!!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("isSuccess", isSuccess);
	}
	
	public void guardarClasificacion() {
		RequestContext context = RequestContext.getCurrentInstance();
		Boolean isSuccess = false;
		FacesMessage message = null;

		try {
			isSuccess = getDao().crearClasificacion(getClasificacion());
			setClasificaciones(getDao().obtenerListaClasificaciones());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Clasificacion", e.getMessage());
			e.printStackTrace();
		}

		if (isSuccess)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Clasificacion", "Nueva Clasificacion Creada!!");
		else
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Clasificacion", "Error al crear Clasificacion!!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("isSuccess", isSuccess);
	}

	public void guardarPrioridad() {
		RequestContext context = RequestContext.getCurrentInstance();
		Boolean isSuccess = false;
		FacesMessage message = null;

		try {
			isSuccess = getDao().crearPrioridad(getPrioridad());
			setPrioridades(getDao().obtenerListaPrioridades());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prioridad", e.getMessage());
			e.printStackTrace();
		}

		if (isSuccess)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Prioridad", "Nueva Prioridad Creada!!");
		else
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prioridad", "Error al crear Prioridad!!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("isSuccess", isSuccess);
	}

	public void guardarRole() {
		RequestContext context = RequestContext.getCurrentInstance();
		Boolean isSuccess = false;
		FacesMessage message = null;

		try {
			isSuccess = getDao().crearRole(getRole());
			setRoles(getDao().obtenerListaRoles());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Role", e.getMessage());
			e.printStackTrace();
		}

		if (isSuccess)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Role", "Nuevo Role Creado!!");
		else
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Role", "Error al crear Role!!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("isSuccess", isSuccess);
	}

	public void guardarTicketEstado() {
		RequestContext context = RequestContext.getCurrentInstance();
		Boolean isSuccess = false;
		FacesMessage message = null;

		try {
			isSuccess = getDao().crearTicketEstado(getTicketEstado());
			setTicketEstados(getDao().obtenerListaTicketEstados());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "TicketEstado", e.getMessage());
			e.printStackTrace();
		}

		if (isSuccess)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "TicketEstado", "Nuevo TipoEstado Creado!!");
		else
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "TicketEstado", "Error al crear TipoEstado!!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("isSuccess", isSuccess);
	}

	public void guardarTipoSolicitud() {
		RequestContext context = RequestContext.getCurrentInstance();
		Boolean isSuccess = false;
		FacesMessage message = null;

		try {
			isSuccess = getDao().crearTipoSolicitud(getTipoSolicitud());
			setTipoSolicitudes(getDao().obtenerListaTipoSolicitudes());
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "TipoSolicitud", e.getMessage());
			e.printStackTrace();
		}

		if (isSuccess)
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "TipoSolicitud", "Nuevo TipoSolicitud Creado!!");
		else
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "TipoSolicitud", "Error al crear TipoSolicitud!!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("isSuccess", isSuccess);
	}

}
