package com.umg.helpdesk.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.umg.helpdesk.model.TicketDetalle;
import com.umg.helpdesk.model.TicketDto;

@SuppressWarnings("serial")
@ManagedBean(name = "ticketDao")
@ApplicationScoped
public class TicketDao implements Serializable {

	public List<TicketDto> getListaTickets() throws Exception {
		List<TicketDto> tickets = new ArrayList<>();
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("t.id, ");
			sql.append("t.asunto, ");
			sql.append("t.inconveniente, ");
			sql.append("t.anio, ");
			sql.append("t.mes, ");
			sql.append("t.fecha_creacion, ");
			sql.append("t.fecha_modificacion, ");
			sql.append("t.fecha_estimacion_resolucion, ");
			sql.append("ts.descripcion, ");
			sql.append("p.descripcion, ");
			sql.append("dp2.departamento, ");
			sql.append("dp.departamento, ");
			sql.append("es.descripcion, ");
			sql.append("cl.descripcion, ");
			sql.append("es.id ");
			sql.append("FROM ticket t ");
			sql.append(" INNER JOIN tipos_solicitudes ts on (t.tipo_solicitud_id = ts.id )");
			sql.append(" INNER JOIN prioridades p on (t.prioridad_id = p.id)");
			sql.append(" INNER JOIN ticket_estados es on (t.estado_id = es.id)");
			sql.append(" INNER JOIN clasificaciones cl on (t.clasificacion_id = cl.id)");
			sql.append(" INNER JOIN departamentos dp on (t.depto_asignacion_id = dp.id) ");
			sql.append(" INNER JOIN departamentos dp2 on (t.depto_solicitante_id = dp2.id) ORDER BY t.id ASC");			

			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			while (rs.next()) {
				TicketDto ticket = new TicketDto();
				ticket.setId(rs.getLong(1));
				ticket.setAsunto(rs.getString(2));
				ticket.setInconveniente(rs.getString(3));
				ticket.setAnio(rs.getShort(4));
				ticket.setMes(rs.getShort(5));
				Date fechaCreacion = rs.getDate(6);
				ticket.setFechaCreacion(df.format(fechaCreacion));
				Date fechaModificacion = rs.getDate(7);
				if (fechaModificacion != null)
					ticket.setFechaModificacion(df.format(fechaModificacion));
				else
					ticket.setFechaModificacion("--");

				Date fechaEstimacion = rs.getDate(8);
				if (fechaEstimacion != null)
					ticket.setFechaEstimacion(df.format(fechaEstimacion));
				else
					ticket.setFechaEstimacion("--");
				ticket.setTipoSolicitud(rs.getString(9));
				ticket.setPrioridad(rs.getString(10));
				ticket.setDeptoSolicitante(rs.getString(11));
				ticket.setDeptoAsignacion(rs.getString(12));
				ticket.setEstado(rs.getString(13));
				ticket.setClasificacion(rs.getString(14));
				ticket.setEstadoId(rs.getLong(15));
				tickets.add(ticket);

			}
		} catch (SQLException e) {
			throw new Exception("Error al obtener el listado de tickets");
		}
		return tickets;
	}

	public boolean insertarDetalle(TicketDetalle ticketDetalle) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO ticket_detalle (accion, anio, comentario, fecha_creacion, mes, ticket_id, usuario_accion_id) values (?,?,?,?,?,?,?) ");

			Map<Integer, Object> params = new HashMap<>();
			params.put(1, ticketDetalle.getAccion());
			params.put(2, ticketDetalle.getAnio());
			params.put(3, ticketDetalle.getComentario());
			params.put(4, ticketDetalle.getFechaCreacion());
			params.put(5, ticketDetalle.getMes());
			params.put(6, ticketDetalle.getTicketId());
			params.put(7, ticketDetalle.getUsuarioAccionId());

			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public boolean resolverTicket(Long ticketId) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ticket SET estado_id = 2 WHERE id = ? ");

			Map<Integer, Object> params = new HashMap<>();
			params.put(1, ticketId);

			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public List<TicketDetalle> getListaTicketDetalles(Long id) throws Exception {
		List<TicketDetalle> tickets = new ArrayList<>();
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");			
			sql.append("comentario, ");
			sql.append("fecha_creacion, ");
			sql.append("usuario_accion_id ");
			sql.append("FROM ticket_detalle ");
			sql.append("WHERE ticket_id = ? ORDER BY id DESC ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, id);
		
			ResultSet rs = trans.ExecuteQuery(sql.toString(), params);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			while (rs.next()) {
				TicketDetalle ticket = new TicketDetalle();
				ticket.setComentario(rs.getString(1));
				Date fechaCreacion = rs.getDate(2);
				String usuarioAccion = rs.getString(3);
				ticket.setAccion(df.format(fechaCreacion) +" - "+usuarioAccion);
				tickets.add(ticket);

			}
		} catch (SQLException e) {
			throw new Exception("Error al obtener el listado de tickets");
		}
		return tickets;
	}

}
