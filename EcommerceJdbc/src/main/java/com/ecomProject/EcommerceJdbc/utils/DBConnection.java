package com.ecomProject.EcommerceJdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private DBConnection() {
	}

	public static Connection getConnection() {

		Connection conn = null;

		try {

			Class.forName(DBConstant.DRIVER);

			conn = DriverManager.getConnection(DBConstant.URL, DBConstant.USERNAME, DBConstant.PASSWORD);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}