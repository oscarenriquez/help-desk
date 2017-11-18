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

import com.umg.helpdesk.dao.TicketDao;
import com.umg.helpdesk.model.TicketDetalle;
import com.umg.helpdesk.model.TicketDto;
import com.umg.helpdesk.utils.SessionUtils;

@SuppressWarnings("serial")
@ManagedBean(name="ticketView")
@ViewScoped
public class TicketController implements Serializable {
	
	// Propiedad del Bean
	private List<TicketDto> tickets;
	private List<TicketDetalle> ticketDetalles;
	private TicketDto selectedTicket;
		
	@ManagedProperty("#{ticketDao}")
    private TicketDao dao;
 
	private TicketDetalle ticketDetalle;
	
    @PostConstruct
    public void init() {
    	try {
			tickets = dao.getListaTickets();
		} catch (Exception e) {			
			e.printStackTrace();
		}
    	
    	ticketDetalle = new TicketDetalle();  
    	selectedTicket = new TicketDto();
    }

	public List<TicketDto> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketDto> tickets) {
		this.tickets = tickets;
	}

	public TicketDao getDao() {
		return dao;
	}

	public void setDao(TicketDao dao) {
		this.dao = dao;
	}

	public TicketDto getSelectedTicket() {
		return selectedTicket;
	}

	public void setSelectedTicket(TicketDto selectedTicket) {
		this.selectedTicket = selectedTicket;
	}

	public TicketDetalle getTicketDetalle() {
		return ticketDetalle;
	}

	public void setTicketDetalle(TicketDetalle ticketDetalle) {
		this.ticketDetalle = ticketDetalle;
	}
	
	public void editar() {
		FacesMessage message = null;        
        String username = SessionUtils.getUserName();
        ticketDetalle.setAccion("editar");
        ticketDetalle.setAnio(Calendar.getInstance().get(Calendar.YEAR));
        ticketDetalle.setMes(Calendar.getInstance().get(Calendar.MONTH + 1));
        ticketDetalle.setFechaCreacion(Calendar.getInstance().getTime());
        ticketDetalle.setTicketId(selectedTicket.getId());
        ticketDetalle.setUsuarioAccionId(username);
        
        try {
			if(dao.insertarDetalle(ticketDetalle))
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar", "Ticket Editado");
			else
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar", "Error");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar", e.getMessage());
			e.printStackTrace();
		}
         
        FacesContext.getCurrentInstance().addMessage(null, message);        
	}
	
	public void resolver() {
		FacesMessage message = null;        
        String username = SessionUtils.getUserName();
        ticketDetalle.setAccion("editar");
        ticketDetalle.setAnio(Calendar.getInstance().get(Calendar.YEAR));
        ticketDetalle.setMes(Calendar.getInstance().get(Calendar.MONTH + 1));
        ticketDetalle.setFechaCreacion(Calendar.getInstance().getTime());
        ticketDetalle.setTicketId(selectedTicket.getId());
        ticketDetalle.setComentario("RESUELTO");
        ticketDetalle.setUsuarioAccionId(username);
        
        try {
			if(dao.insertarDetalle(ticketDetalle))
				if(dao.resolverTicket(selectedTicket.getId()))
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar", "Ticket Editado");
				else
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar", "Error");
			else
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar", "Error");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editar", e.getMessage());
			e.printStackTrace();
		}
         
        FacesContext.getCurrentInstance().addMessage(null, message);           
	}
	
	public void obtenerDetalles() {
		try {
			ticketDetalles = dao.getListaTicketDetalles(selectedTicket.getId());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	public List<TicketDetalle> getTicketDetalles() {
		return ticketDetalles;
	}

	public void setTicketDetalles(List<TicketDetalle> ticketDetalles) {
		this.ticketDetalles = ticketDetalles;
	}	
	
}
