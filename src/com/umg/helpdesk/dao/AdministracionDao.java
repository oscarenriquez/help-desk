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
			
			rs.close();
			rs = null;
			
			return departamentos;
		} catch (SQLException e) {
			throw new Exception("Error al insertar entidad ");
		}		
	}	
	
	public List<Clasificacion> obtenerListaClasificaciones() throws Exception {
		try {
			List<Clasificacion> result = new ArrayList<>();
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, descripcion FROM clasificaciones ORDER BY id ");

			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
			if (rs != null) {
				while (rs.next()) {
					Clasificacion clasificacion = new Clasificacion();
					clasificacion.setId(rs.getLong("id"));
					clasificacion.setDescripcion(rs.getString("descripcion"));

					result.add(clasificacion);
				}
			}

			rs.close();
			rs = null;
			
			return result;
		} catch (SQLException e) {
			throw new Exception("Error al obtener lista clasificaciones ");
		}

	}
	
	public List<Prioridad> obtenerListaPrioridades() throws Exception {
		try {
			List<Prioridad> result = new ArrayList<>();
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, descripcion FROM prioridades ORDER BY id ");

			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
			if (rs != null) {
				while (rs.next()) {
					Prioridad prioridad = new Prioridad();
					prioridad.setId(rs.getLong("id"));
					prioridad.setDescripcion(rs.getString("descripcion"));

					result.add(prioridad);
				}
			}
			
			rs.close();
			rs = null;

			return result;
		} catch (SQLException e) {
			throw new Exception("Error al obtener lista prioridades ");
		}

	}
	
	public List<TipoSolicitud> obtenerListaTipoSolicitudes() throws Exception {
		try {
			List<TipoSolicitud> result = new ArrayList<>();
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, descripcion FROM tipos_solicitudes ORDER BY id ");

			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
			if (rs != null) {
				while (rs.next()) {
					TipoSolicitud tipoSolicitud = new TipoSolicitud();
					tipoSolicitud.setId(rs.getLong("id"));
					tipoSolicitud.setDescripcion(rs.getString("descripcion"));

					result.add(tipoSolicitud);
				}
			}
			
			rs.close();
			rs = null;

			return result;
		} catch (SQLException e) {
			throw new Exception("Error al obtener lista tipos_solicitudes ");
		}

	}
	
	public List<Role> obtenerListaRoles() throws Exception {
		try {
			List<Role> result = new ArrayList<>();
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, descripcion FROM roles ORDER BY id ");

			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
			if (rs != null) {
				while (rs.next()) {
					Role role = new Role();
					role.setId(rs.getLong("id"));
					role.setDescripcion(rs.getString("descripcion"));

					result.add(role);
				}
			}
			
			rs.close();
			rs = null;

			return result;
		} catch (SQLException e) {
			throw new Exception("Error al obtener lista roles ");
		}

	}
	
	public List<TicketEstado> obtenerListaTicketEstados() throws Exception {
		try {
			List<TicketEstado> result = new ArrayList<>();
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, descripcion FROM ticket_estados ORDER BY id ");

			ResultSet rs = trans.ExecuteQuery(sql.toString(), null);
			if (rs != null) {
				while (rs.next()) {
					TicketEstado estado = new TicketEstado();
					estado.setId(rs.getLong("id"));
					estado.setDescripcion(rs.getString("descripcion"));

					result.add(estado);
				}
			}

			rs.close();
			rs = null;
			
			return result;
		} catch (SQLException e) {
			throw new Exception("Error al obtener lista ticket_estados ");
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
	
	public boolean actualizarClasificacion(Clasificacion clasificacion) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("UPDATE clasificaciones SET descripcion = ? WHERE id = ? ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, clasificacion.getDescripcion());
			params.put(2, clasificacion.getId());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al actualizar entidad ");
		}
	}
	
	public boolean actualizarPrioridad(Prioridad prioridad) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("UPDATE prioridades SET descripcion = ? WHERE id = ? ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, prioridad.getDescripcion());
			params.put(2, prioridad.getId());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al actualizar entidad ");
		}
	}
	
	public boolean actualizarRole(Role role) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("UPDATE roles SET descripcion = ? WHERE id = ? ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, role.getDescripcion());
			params.put(2, role.getId());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al actualizar entidad ");
		}
	}
	
	public boolean actualizarTicketEstado(TicketEstado estado) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("UPDATE ticket_estados SET descripcion = ? WHERE id = ? ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, estado.getDescripcion());
			params.put(2, estado.getId());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al actualizar entidad ");
		}
	}
	
	public boolean actualizarTipoSolicitud(TipoSolicitud tipoSolicitud) throws Exception {
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("UPDATE tipos_solicitudes SET descripcion = ? WHERE id = ? ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, tipoSolicitud.getDescripcion());
			params.put(2, tipoSolicitud.getId());
				
			return trans.ExecuteInsertOrUpdate(sql.toString(), params);
		} catch (SQLException e) {
			throw new Exception("Error al actualizar entidad ");
		}
	}
	
	public Long obtenerTicketEstadoId(String descripcion) throws Exception {
		try {
			Long estadoId = 0l;
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT id FROM ticket_estados WHERE ucase(descripcion) = ucase(?) ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, descripcion);
				
			ResultSet rs = trans.ExecuteQuery(sql.toString(), params);
			if(rs != null) {
				if(rs.next()) {
					estadoId = rs.getLong(1);
				}
			}
			
			rs.close();
			rs = null;
			
			return estadoId;						
		} catch (SQLException e) {
			throw new Exception("Error al Obtener estado id por descripcion ");
		}
	}
}
