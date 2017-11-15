package com.umg.helpdesk.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="navigatorView")
public class NavigatorController implements Serializable {

    private static final String NUEVO_CASO="nuevo-caso";
    private static final String MIS_CASOS="mis-casos";
    private static final String ADMINISTRAR="administrar";
    
	private static final long serialVersionUID = 1L;
	
	public String nuevoCaso() {
		return NUEVO_CASO;
	}
	
	public String misCasos() {
		return MIS_CASOS;
	}
	
	public String administrar() {
		return ADMINISTRAR;
	}

 }