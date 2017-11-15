package com.umg.helpdesk.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.umg.helpdesk.dao.TicketDao;
import com.umg.helpdesk.model.Ticket;

@SuppressWarnings("serial")
@ManagedBean(name="ticketView")
@ViewScoped
public class TicketController implements Serializable {
	
	private List<Ticket> tickets;
	
	@ManagedProperty("#{ticketDao}")
    private TicketDao dao;
 
    @PostConstruct
    public void init() {
    	try {
			tickets = dao.getListaTickets();
		} catch (Exception e) {			
			e.printStackTrace();
		}
    }

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public TicketDao getDao() {
		return dao;
	}

	public void setDao(TicketDao dao) {
		this.dao = dao;
	}
	
	

}
