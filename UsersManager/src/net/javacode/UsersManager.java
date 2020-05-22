package net.javacode;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class UsersManager {

	public static void insert(Connection connection) throws SQLException {
		String sql = "INSERT INTO users (username, email, fullname, password)" + " VALUES (?,?,?,?)";
		
		String fullname = "Bill Gates";
		String username = "gates.bill";
		String email = "gates@ms.com";
		String password = "!@#$%^&*";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, email);
		statement.setString(3, fullname);
		statement.setString(4, password);
		
		int rows = statement.executeUpdate();
		
		if (rows > 0) {
			System.out.println("A new user has been inserted succesfully!");
		}
	}
	
	public static void select(Connection connection) throws SQLException {
		String sql = "SELECT * FROM users";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while (result.next()) {
			int userId = result.getInt("user_id");
			String username = result.getString("username");
			String fullname = result.getString("fullname");
			String email = result.getString("email");
			String password = result.getString("password");
			
			System.out.println(userId + ":  " + username + ",  " + fullname + ",  " + email + ",  " + password);
		}
	}

	public static void update(Connection connection) throws SQLException {
		
		String sql = "UPDATE users SET password=?, fullname=?, email=? WHERE username=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		String newPassword = "studentOfBachelors";
		String newFullname = "GAtrey";
		String newEmail = "atrey@college.com";
		String forUsername = "geetansh.atrey";
		
		
		statement.setString(1, newPassword);
		statement.setString(2, newFullname);
		statement.setString(3, newEmail);
		statement.setString(4, forUsername);
		
		int rows = statement.executeUpdate();
		
		if (rows > 0) { 
			System.out.println("The user's information has been updated !");
		}
	}
	
	public static void delete(Connection connection) throws SQLException {
		String sql = "DELETE FROM users WHERE username=?";
		String deleteUsername = "geetansh.atrey";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,deleteUsername);
		
		int rows = statement.executeUpdate();
		
		if (rows > 0) {
			System.out.println("The user's information has been deleted.");
		}
		
	}
	
	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost:3307/sampledb";
		String dbUsername = "root";
		String dbPassword = "geetansh";
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, dbUsername,dbPassword);
			
			delete(connection);
			connection.close();
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
