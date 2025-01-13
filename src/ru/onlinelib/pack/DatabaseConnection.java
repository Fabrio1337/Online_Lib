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

		public int getUserId(Connection conn)
		{
			try
			{
				String userQuery = "SELECT userid FROM users";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(userQuery);

				int userId = -1;
				if(rs.next())
				{
					userId = rs.getInt("userid");
				}
				return  userId;
			}
			catch (Exception e)
			{
				System.out.println("");
				return -1;
			}
		}

		//метод для внесения данных книги в БД
		public boolean booksQuery(Connection conn, String book_name, String book_url)
		{
			int userId = getUserId(conn);
			try {
				String checkQuery = "SELECT book_name FROM books WHERE book_name = ?";
				PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
				checkStmt.setString(1, book_name);
				ResultSet rs = checkStmt.executeQuery(checkQuery);

				if(rs.next())
				{
					System.out.println("Такая книга уже существует");
					return false;
				}


				String query = "INSERT INTO books(book_name, book_url, userid) VALUES(?, ?, ?)";
				PreparedStatement insertStatement = conn.prepareStatement(query);
				insertStatement.setString(1, book_name);
				insertStatement.setString(2, book_url);
				insertStatement.setInt(3, userId);

				int rowInserted = insertStatement.executeUpdate();

				System.out.println("Книги внесены в базу данных");
				return  rowInserted > 0;

			}
			catch (Exception e)
			{
				System.out.println("");
				return false;
			}

		}
		//метод для проверки имеются ли книги в БД
		public String getBooks()
		{
			return "";
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
				//поиск пользователя в Базе данных
				String query = "SELECT first_name FROM " + table_name + " WHERE first_name = ? AND pass = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, first_name);
				pstmt.setString(2, pass);
				ResultSet rs = pstmt.executeQuery();

				getUserId(conn);

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
