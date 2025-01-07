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

		public boolean databaseQuery(boolean whatIsQuery, Connection conn, String table_name, String first_name, String pass)
		{
			if(!whatIsQuery)
			{
				try {
					String Query = String.format("INSERT INTO %s(first_name,pass) VALUES('%s','%s')", table_name, first_name, pass);
					PreparedStatement Pstmt = conn.prepareStatement(Query);
					Pstmt.setString(1, first_name);
					Pstmt.setString(2, pass);
					int rowInserted =  Pstmt.executeUpdate();

					if(rowInserted > 0)
					{
						System.out.println("Регистрация прошла успешно");
						return false;
					}
					else
					{
						System.out.println("Пользователь с таким именем уже существует");
						return true;
					}

				}
				catch (Exception e)
				{
					System.out.println(e);
				}

			}
			else
			{
				try{
					String query = String.format("SELECT first_name, pass FROM %s WHERE first_name = ? AND pass = ?", table_name);
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, first_name);
					pstmt.setString(2, pass);
					ResultSet rs = pstmt.executeQuery();

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
