package com.umg.helpdesk.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.umg.helpdesk.model.Ticket;

@ManagedBean(name = "nuevoCasoDao")
@ApplicationScoped
public class NuevoCasoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean crearTicket(Ticket ticket) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("INSERT INTO ticket (asunto, inconveniente, anio, mes, fecha_creacion, tipo_solicitud_id, prioridad_id, depto_solicitante_id, depto_asignacion_id, estado_id, clasificacion_id, fecha_estimacion_resolucion, fecha_modificacion)  ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");			
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, ticket.getAsunto());
			params.put(2, ticket.getInconveniente());
			params.put(3, ticket.getAnio());
			params.put(4, ticket.getMes());
			params.put(5, ticket.getFechaCreacion());			
			params.put(6, ticket.getTipoSolicitudId());
			params.put(7, ticket.getPrioridadId());
			params.put(8, ticket.getDeptoSolicitanteId());
			params.put(9, ticket.getDeptoAsignacionId());
			params.put(10, ticket.getEstadoId());
			params.put(11, ticket.getClasificacionId());
			params.put(12, ticket.getFechaEstimacion());
			params.put(13, ticket.getFechaModificacion());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}	

}
