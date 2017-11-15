package com.umg.helpdesk.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.umg.helpdesk.model.Usuario;

@SuppressWarnings("serial")
@ManagedBean(name="usuarioDao")
@ApplicationScoped
public class UsuarioDao implements Serializable {
	
	public Usuario autenticarUsuario (String username, String password) throws Exception {
		Usuario usuario = null;
		try {
			Transaction trans = new Transaction();
			StringBuilder sql = new StringBuilder(); 				
			sql.append("SELECT ");
			sql.append("usuario, ");
			sql.append("id, ");
			sql.append("nombre, ");
			sql.append("apellido, ");
			sql.append("correo, ");
			sql.append("departamento_id, ");
			sql.append("estado_id, ");
			sql.append("rol_id, ");
			sql.append("supervisor_id, ");
			sql.append("telefono, ");
			sql.append("clave ");
			sql.append("FROM usuarios ");
			sql.append("WHERE usuario = ? ");
			sql.append("AND clave = ? ");
			
			Map<Integer, Object> params = new HashMap<>();
			params.put(1, username);
			params.put(2, password);			
			ResultSet resultSet = trans.ExecuteQuery(sql.toString(), params);
					
			if (resultSet.next()) {
				usuario = new Usuario();
				usuario.setUsuario(resultSet.getString("usuario"));
				usuario.setId(resultSet.getLong("id"));
				usuario.setNombre(resultSet.getString("nombre"));
				usuario.setApellido(resultSet.getString("apellido"));
				usuario.setCorreo(resultSet.getString("correo"));
				usuario.setDepartamentoId(resultSet.getLong("departamento_id"));
				usuario.setEstadoId(resultSet.getLong("estado_id"));
				usuario.setRolId(resultSet.getLong("rol_id"));
				usuario.setSupervisorId(resultSet.getLong("supervisor_id"));
				usuario.setTelefono(resultSet.getString("telefono"));
				usuario.setClave(resultSet.getString("clave"));
				return usuario;
			}		
		} catch (SQLException e) {
			throw new Exception("Error al autenticar al usuario "+username);
		}	
		return usuario;
	}

}
