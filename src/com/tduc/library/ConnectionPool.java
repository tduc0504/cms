package com.tduc.library;

import java.sql.*;
import java.util.*;

public class ConnectionPool implements Runnable {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/library_db";
	private String userName = "root";
	private String password = "";

	private int maxConnections = 50;
	private boolean waitIfBusy;
	private Vector<Connection> availableConnections, busyConnections;
	private boolean connectionPending = false;

	public ConnectionPool(int initialConnections) throws SQLException {

		if (initialConnections > maxConnections) {
			initialConnections = maxConnections;
		}

		availableConnections = new Vector<Connection>(initialConnections);
		busyConnections = new Vector<Connection>();

		for (int i = 0; i < initialConnections; i++) {
			availableConnections.addElement(makeNewConnection());
		}
	}

	public ConnectionPool() throws SQLException {
		availableConnections = new Vector<Connection>(maxConnections);
		busyConnections = new Vector<Connection>();

		for (int i = 0; i < maxConnections; i++) {
			availableConnections.addElement(makeNewConnection());
		}
	}

	private Connection makeNewConnection() throws SQLException {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, userName, password);
			return (connection);
		} catch (ClassNotFoundException cnfe) {
			throw new SQLException("Can’t find class for driver: " + driver);
		}
	}

	public synchronized Connection getConnection() throws SQLException {
		if (!availableConnections.isEmpty()) {
			Connection existingConnection = (Connection) availableConnections.lastElement();
			int lastIndex = availableConnections.size() - 1;
			availableConnections.removeElementAt(lastIndex);

			if (existingConnection.isClosed()) {
				notifyAll();
				return (getConnection());
			} else {
				busyConnections.addElement(existingConnection);
				return (existingConnection);
			}
		} else {

			if ((totalConnections() < maxConnections) && !connectionPending) {
				makeBackgroundConnection();
			} else if (!waitIfBusy) {
				throw new SQLException("Connection limit reached");
			}

			try {
				wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			return (getConnection());
		}
	}

	private void makeBackgroundConnection() {
		connectionPending = true;
		try {
			Thread connectThread = new Thread(this);
			connectThread.start();
		} catch (OutOfMemoryError oome) {
			// Give up on new connection
		}
	}

	public synchronized int totalConnections() {
		return (availableConnections.size() + busyConnections.size());
	}

	public synchronized void closeAllConnections() {
		closeConnections(availableConnections);
		availableConnections = new Vector<Connection>();
		closeConnections(busyConnections);
		busyConnections = new Vector<Connection>();
	}

	private void closeConnections(Vector<Connection> connections) {
		try {
			for (int i = 0; i < connections.size(); i++) {
				Connection connection = (Connection) connections.elementAt(i);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		} catch (SQLException sqle) {
		}
	}

	public void run() {
		try {
			Connection connection = makeNewConnection();
			synchronized (this) {
				availableConnections.addElement(connection);
				connectionPending = false;
				notifyAll();
			}
		} catch (Exception e) {
		}
	}

	public synchronized void free(Connection connection) {
		busyConnections.removeElement(connection);
		availableConnections.addElement(connection);
		notifyAll();
	}

}
