package com.umg.helpdesk.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.umg.helpdesk.dao.UsuarioDao;
import com.umg.helpdesk.model.Usuario;
import com.umg.helpdesk.utils.SessionUtils;

@ManagedBean(name="userLoginView")
public class UserLoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao dao;
	private String username;    
    private String password;    
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) {        	
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;        
        Usuario usuario = null; 
        if(username != null && password != null) {
        	try {
				usuario = dao.autenticarUsuario(username.trim(), password.trim());
			} catch (Exception e) {
				loggedIn = false;
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Ingresar", e.getMessage());
			}
        }
        
        if(usuario != null){
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", username);
            HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			session.setAttribute("usuario", usuario);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Ingresar", "Credenciales Invalidas");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);        
    }  
    
    public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public UsuarioDao getDao() {
		return dao;
	}

	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}
    
    
	
}
