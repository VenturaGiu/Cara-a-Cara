package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	public Connection getConexao() {
		try {
			String server = "10.67.4.185"; //server 192.168.0.100
			String user = "server";
			String senha = "123";
			String banco = "bdJogo";
			
			Class.forName("com.mysql.jdbc.Driver");
			String path = "jdbc:mysql://"+server +"/"+banco;
			
			Connection conn = DriverManager.getConnection(path, user, senha);
			return conn;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public void fechar(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
