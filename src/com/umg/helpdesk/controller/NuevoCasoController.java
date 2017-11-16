package com.umg.helpdesk.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.umg.helpdesk.dao.AdministracionDao;
import com.umg.helpdesk.dao.NuevoCasoDao;
import com.umg.helpdesk.model.Clasificacion;
import com.umg.helpdesk.model.Departamento;
import com.umg.helpdesk.model.Prioridad;
import com.umg.helpdesk.model.Ticket;
import com.umg.helpdesk.model.TipoSolicitud;
import com.umg.helpdesk.model.Usuario;
import com.umg.helpdesk.utils.SessionUtils;

@SuppressWarnings("serial")
@ManagedBean(name="nuevoCasoView")
@ViewScoped
public class NuevoCasoController implements Serializable {

	
	@ManagedProperty("#{adminDao}")
	private AdministracionDao dao;
	@ManagedProperty("#{nuevoCasoDao}")
	private NuevoCasoDao nuevoCasoDao;
	
	private Ticket ticket;
	
	private List<Clasificacion> clasificaciones;
	private List<Departamento> departamentos;
	private List<Prioridad> prioridades;
	private List<TipoSolicitud> solicitudes;
	
	@PostConstruct
	public void init() {
		ticket = new Ticket();	
		try {
			clasificaciones = dao.obtenerListaClasificaciones();
			departamentos = dao.obtenerListaDepartamentos();
			prioridades = dao.obtenerListaPrioridades();		
			solicitudes = dao.obtenerListaTipoSolicitudes();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public AdministracionDao getDao() {
		return dao;
	}

	public void setDao(AdministracionDao dao) {
		this.dao = dao;
	}	

	public NuevoCasoDao getNuevoCasoDao() {
		return nuevoCasoDao;
	}

	public void setNuevoCasoDao(NuevoCasoDao nuevoCasoDao) {
		this.nuevoCasoDao = nuevoCasoDao;
	}

	public List<Clasificacion> getClasificaciones() {
		return clasificaciones;
	}

	public void setClasificaciones(List<Clasificacion> clasificaciones) {
		this.clasificaciones = clasificaciones;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Prioridad> getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(List<Prioridad> prioridades) {
		this.prioridades = prioridades;
	}

	public List<TipoSolicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<TipoSolicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}	
	
	public String nuevoCaso() {
		FacesMessage msg = null;
		Boolean isSuccess = false;
		Usuario usuario = (Usuario)SessionUtils.getSession().getAttribute("usuario");
		ticket.setAnio( Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR)).shortValue());
		ticket.setMes( Integer.valueOf(Calendar.getInstance().get(Calendar.MONTH + 1)).shortValue());
		ticket.setFechaCreacion(Calendar.getInstance().getTime());
		ticket.setFechaEstimacion(Calendar.getInstance().getTime());
		ticket.setFechaModificacion(Calendar.getInstance().getTime());
		
		if(usuario != null) {
			ticket.setDeptoSolicitanteId(usuario.getDepartamentoId());
			try {
				Long estadoId = dao.obtenerTicketEstadoId("pendiente");
				ticket.setEstadoId(estadoId);
				isSuccess = nuevoCasoDao.crearTicket(ticket);
				if(isSuccess) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo Ticket", "Creado!!");
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Al crear ticket!!");
				}
			} catch (Exception e) {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
				e.printStackTrace();
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			SessionUtils.getSession().invalidate();
			return "login.xhtml?faces-redirect=true";
		}
		
		
		if(isSuccess)
			return "home.xhtml?faces-redirect=true";
	
		return "nuevo-caso.xhtml?faces-redirect=true";
	}
	
	public String cerrarVentana() {		
		return "home.xhtml?faces-redirect=true";
	}
	
}
