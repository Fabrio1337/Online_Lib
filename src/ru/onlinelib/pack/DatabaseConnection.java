package ru.onlinelib.pack;

import java.sql.*;

public class DatabaseConnection {
		private static final String URL = "jdbc:postgresql://127.0.0.1:5432/onlineLib"; 
	    private static final String USER = "postgres"; 
	    private static final String PASSWORD = "0000"; 

	    public Connection connect() {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Соединение с базой данных установлено!");
	        } catch (SQLException e) {
	            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
	        }
	        return conn;
	    }

	public boolean databaseQuery(boolean whatIsQuery, Connection conn, String table_name, String first_name, String pass) {
		if(!whatIsQuery) {
			try {
				// проверка существует ли пользователь с таким именем
				String checkQuery = "SELECT first_name FROM " + table_name + " WHERE first_name = ?";
				PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
				checkStmt.setString(1, first_name);
				ResultSet rs = checkStmt.executeQuery();

				if(rs.next()) {
					System.out.println("Пользователь с таким именем уже существует");
					return false;
				}

				// добавление нового пользователя
				String insertQuery = "INSERT INTO " + table_name + "(first_name, pass) VALUES(?, ?)";
				PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
				insertStmt.setString(1, first_name);
				insertStmt.setString(2, pass); // Consider adding password hashing

				int rowsInserted = insertStmt.executeUpdate();
				System.out.println("Регистрация прошла успешно");
				return rowsInserted > 0;

			} catch (SQLException e) {
				System.err.println("Ошибка при регистрации: " + e.getMessage());
				return false;
			}
		} else {
			try {
				String query = "SELECT first_name FROM " + table_name + " WHERE first_name = ? AND pass = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, first_name);
				pstmt.setString(2, pass);
				ResultSet rs = pstmt.executeQuery();

				boolean userFound = rs.next();
				if(userFound) {
					System.out.println("Авторизация успешна");
				} else {
					System.out.println("Неверные данные для входа");
				}
				return userFound;

			} catch (SQLException e) {
				System.err.println("Ошибка при авторизации: " + e.getMessage());
				return false;
			}
		}
	}

}
