package com.umg.helpdesk.dao;

import java.sql.*;
import java.util.Map;
import java.util.Set;

import com.sun.rowset.CachedRowSetImpl;

public class Transaction {

	public ResultSet ExecuteQuery(String sql, Map<Integer, Object> params) throws SQLException {
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		Connection connection = null;
		CachedRowSetImpl result = new CachedRowSetImpl(); 
		try {
			MySQLConnection conn = MySQLConnection.getInstance();
			connection = conn.connect();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			
			if (params != null && params.size() > 0) {
				Set<Integer> keys = params.keySet();
				for (Integer key : keys) {
					Object value = params.get(key);

					if (value instanceof String) {
						statement.setString(key, value.toString());
					} else if (value instanceof Integer) {
						statement.setInt(key, (Integer) value);
					} else if (value instanceof Long) {
						statement.setLong(key, (Long) value);
					} else if (value instanceof Double) {
						statement.setDouble(key, (Double) value);
					} else {
						new RuntimeException("Unsupported data type on Transaction.ExecuteQuery");
					}
				}
			}
			
			resultSet = statement.executeQuery();	
			
			result.populate(resultSet);

			statement.close();
			resultSet.close();
			connection.commit();
		} catch (SQLException ex) {
			try {
				connection.rollback();
				ex.printStackTrace();
				System.out.print(sql);
			} catch (SQLException ex2) {
				ex2.printStackTrace();
				System.out.print(sql);
				throw new RuntimeException(ex2);

			}
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.print(sql);
			throw new RuntimeException(exc);
		} finally {

			resultSet = null;
			statement = null;

		}
		return result;
	}
	
	public boolean ExecuteInsertOrUpdate(String sql, Map<Integer, Object> params) throws SQLException {
		boolean result = false;
		PreparedStatement statement = null;
		Connection connection = null;		
		try {
			MySQLConnection conn = MySQLConnection.getInstance();
			connection = conn.connect();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			
			if (params != null && params.size() > 0) {
				Set<Integer> keys = params.keySet();
				for (Integer key : keys) {
					Object value = params.get(key);

					if (value instanceof String) {
						statement.setString(key, value.toString());
					} else if (value instanceof Integer) {
						statement.setInt(key, (Integer) value);
					} else if (value instanceof Long) {
						statement.setLong(key, (Long) value);
					} else if (value instanceof Double) {
						statement.setDouble(key, (Double) value);
					} else {
						new RuntimeException("Unsupported data type on Transaction.ExecuteInsertOrUpdate");
					}
				}
			}
			
			statement.execute();	
			result = statement.getUpdateCount() > 0;
			
			statement.close();			
			connection.commit();
		} catch (SQLException ex) {
			try {
				connection.rollback();
				ex.printStackTrace();
				System.out.print(sql);
			} catch (SQLException ex2) {
				ex2.printStackTrace();
				System.out.print(sql);
				throw new RuntimeException(ex2);

			}
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.print(sql);
			throw new RuntimeException(exc);
		} finally {
			
			statement = null;

		}
		return result;
	}

}
