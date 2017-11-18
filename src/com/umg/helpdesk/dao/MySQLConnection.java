package com.umg.helpdesk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.umg.helpdesk.helper.ConstantHelper;

public class MySQLConnection {

	private MySQLConnection() {
	}

	private static class MySQLConnectionSub {
		public static final MySQLConnection conn = new MySQLConnection();
	}

	public static final MySQLConnection getInstance() {
		return MySQLConnectionSub.conn;
	}

	private Connection connection = null;
	
	public Connection connect() {
		try {
			if (connection == null) {
				Class.forName(ConstantHelper.DRIVER);
				// String de conexion jdbc:mysql://localhost:3306/helpdesk
				String stringConexion = "jdbc:mysql://" + ConstantHelper.SERVER + ":" + ConstantHelper.PORT + "/"
						+ ConstantHelper.SID;
				connection = DriverManager.getConnection(stringConexion, ConstantHelper.USER, ConstantHelper.PASSWORD);
			}

		} catch (ClassNotFoundException e) {
			connection = null;
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		} catch (SQLException exx) {
			connection = null;
			exx.printStackTrace();
			throw new RuntimeException(exx.toString());
		}
		return connection;
	}

	public void closeConnection(Connection conn) {
		try {
			if (connection != null) {
				connection.close();
				conn.close();
				connection = null;
				conn = null;
			}
		} catch (SQLException ex) {
			connection = null;
			conn = null;
			throw new RuntimeException(ex.toString());
		}
	}

	/*public Connection connectDS() {
		ServiceLocator serviceLocator;
		DataSource ds;
		try {
			if (connection == null) {
				Class.forName(ConstantHelper.DRIVER).newInstance();
				serviceLocator = ServiceLocator.getInstance();
				ds = serviceLocator.getDataSource(ConstantHelper.DS);
				connection = ds.getConnection();
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			ex.printStackTrace();
			connection = null;
		}
		return connection;
	}*/
}
