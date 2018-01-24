package com.tduc.library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataHelper {
	private static Connection conn = null;
	private static Statement stat = null;
	private static DataHelper instance = null;
	private static ConnectionPool connectionPool;

	public static ResultSet query(String sql) {
		ResultSet resultSet = null;
		try {
			resultSet = DataHelper.generateStatement().executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("[SQL]" + e.getMessage());
		}

		if (conn == null) {
			throw new NullPointerException();
		}
		// release the connection back to the pool
		connectionPool.free(conn);
		return resultSet;
	}

	public static void update(String sql) {
		try {
			DataHelper.generateStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("[SQL]" + e.getMessage());
		}
	}

	public static Statement generateStatement() {
		if (connectionPool == null) {
			try {
				connectionPool = new ConnectionPool();
			} catch (SQLException e) {
				System.out.println("Cannot make a Connection Pool, error message: " + e.getMessage());
			}
		}
		try {
			conn = connectionPool.getConnection();
			if (conn != null) {
				stat = conn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return stat;
	}

	public static ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public static void setConnectionPool(ConnectionPool connectionPool) {
		DataHelper.connectionPool = connectionPool;
	}
}
