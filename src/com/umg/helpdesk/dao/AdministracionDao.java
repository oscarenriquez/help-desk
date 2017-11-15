package com.umg.helpdesk.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.umg.helpdesk.model.Clasificacion;
import com.umg.helpdesk.model.Departamento;
import com.umg.helpdesk.model.Prioridad;
import com.umg.helpdesk.model.Role;
import com.umg.helpdesk.model.TicketEstado;
import com.umg.helpdesk.model.TipoSolicitud;

@SuppressWarnings("serial")
@ManagedBean(name = "adminDao")
@ApplicationScoped
public class AdministracionDao implements Serializable {

	public boolean crearDepartamento(Departamento departamento) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("INSERT INTO departamentos (departamento) values (?) ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, departamento.getDepartamento());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public boolean crearClasificacion(Clasificacion clasificacion) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("INSERT INTO clasificaciones (descripcion) values (?) ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, clasificacion.getDescripcion());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params); 
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public boolean crearPrioridad(Prioridad prioridad) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("INSERT INTO prioridades (descripcion) values (?) ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, prioridad.getDescripcion());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params); 
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public boolean crearRole(Role role) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("INSERT INTO roles (descripcion) values (?) ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, role.getDescripcion());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params); 
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public boolean crearTicketEstado(TicketEstado estado) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("INSERT INTO ticket_estados (descripcion) values (?) ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, estado.getDescripcion());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public boolean crearTipoSolicitud(TipoSolicitud tipoSolicitud) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("INSERT INTO tipos_solicitudes (descripcion) values (?) ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, tipoSolicitud.getDescripcion());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}
	}

	public List<Departamento> obtenerListaDepartamentos()  throws Exception {
		try {
			List<Departamento> departamentos = new ArrayList<>();
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT id, departamento FROM departamentos ORDER BY id ");
						
				
			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
			if(rs != null) {
				while(rs.next()) {
					Departamento departamento = new Departamento();
					departamento.setId(rs.getLong("id"));
					departamento.setDepartamento(rs.getString("departamento"));
					
					departamentos.add(departamento);
				}
			}
			
			return departamentos;
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}		
	}
	
	public boolean actualizarDepartamento(Departamento departamento) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("UPDATE departamentos SET departamento = ? WHERE id = ? ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, departamento.getDepartamento());
			params.put(2, departamento.getId());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al actualizar entidad ");
		}
	}
}
