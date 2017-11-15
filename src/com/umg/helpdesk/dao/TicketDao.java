package com.umg.helpdesk.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.umg.helpdesk.model.Ticket;

@SuppressWarnings("serial")
@ManagedBean(name = "ticketDao")
@ApplicationScoped
public class TicketDao implements Serializable {
	
	public List<Ticket> getListaTickets() throws Exception {
		List<Ticket> tickets = new ArrayList<>();
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 				
			sql.append("SELECT ");
			sql.append("id, ");
			sql.append("asunto, ");
			sql.append("inconveniente, ");
			sql.append("anio, ");
			sql.append("mes, ");
			sql.append("fecha_creacion, ");
			sql.append("fecha_modificacion, ");
			sql.append("fecha_estimacion_resolucion, ");
			sql.append("tipo_solicitud_id, ");
			sql.append("prioridad_id, ");
			sql.append("depto_solicitante_id, ");
			sql.append("depto_asignacion_id, ");
			sql.append("estado_id, ");
			sql.append("clasificacion_id ");			
			sql.append("FROM ticket ");			
			
			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
					
			if (rs.next()) {
				Ticket ticket = new Ticket();
				ticket.setId(rs.getLong("id"));
				ticket.setAsunto(rs.getString("asunto"));
				ticket.setInconveniente(rs.getString("inconveniente"));
				ticket.setAnio(rs.getShort("anio"));
				ticket.setMes(rs.getShort("mes"));
				ticket.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
				ticket.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
				ticket.setFechaEstimacion(rs.getTimestamp("fecha_estimacion_resolucion"));
				ticket.setTipoSolicitudId(rs.getLong("tipo_solicitud_id"));
				ticket.setPrioridadId(rs.getLong("prioridad_id"));
				ticket.setDeptoSolicitanteId(rs.getLong("depto_solicitante_id"));
				ticket.setDeptoAsignacionId(rs.getLong("depto_asignacion_id"));
				ticket.setEstadoId(rs.getLong("estado_id"));
				ticket.setClasificacionId(rs.getLong("clasificacion_id"));
				tickets.add(ticket);
				
			}		
		} catch (SQLException e) {
			throw new Exception("Error al obtener el listado de tickets");
		}	
		return tickets;
	}

}
