package ru.onlinelib.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

		public boolean databaseQuery(boolean whatIsQuery, Connection conn, String table_name, String first_name, String pass)
		{
			if(!whatIsQuery)
			{
				Statement statement;
				try{
					String query =String.format("INSERT INTO %s(first_name,pass) VALUES('%s','%s')", table_name, first_name, pass);
					statement = conn.createStatement();
					statement.executeUpdate(query);
					System.out.println("Запрос успешно отправлен");
					return false;
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
			else
			{
				Statement statement;
				try{
					String query =String.format("SELECT  first_name, pass FROM %s WHERE first_name = '%s' AND pass = '%s'", table_name, first_name, pass);
					statement = conn.createStatement();
					statement.executeQuery(query);
					System.out.println("Запрос успешно принят");
					return true;
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
			return false;
		}

}
