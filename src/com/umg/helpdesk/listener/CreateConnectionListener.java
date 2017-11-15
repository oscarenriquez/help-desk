package com.umg.helpdesk.listener;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.umg.helpdesk.dao.MySQLConnection;

/**
 * Application Lifecycle Listener implementation class CreateConnectionListener
 *
 */
@WebListener
public class CreateConnectionListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public CreateConnectionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         MySQLConnection conn = MySQLConnection.getInstance();
         conn.connect();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	MySQLConnection conn = MySQLConnection.getInstance();
        Connection connection = conn.connect();
        
        conn.closeConnection(connection);
    }
	
}
