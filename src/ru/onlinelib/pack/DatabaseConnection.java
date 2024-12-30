package ru.onlinelib.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
		private static final String URL = "jdbc:postgresql://127.0.0.1:5432/onlineLib"; 
	    private static final String USER = "postgres"; 
	    private static final String PASSWORD = "0000"; 

	    public static Connection connect() {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Соединение с базой данных установлено!");
	        } catch (SQLException e) {
	            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
	        }
	        return conn;
	    }

}
