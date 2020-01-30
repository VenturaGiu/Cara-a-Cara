package dao;

import java.sql.Connection;

public class ConTEste {
	public static void main(String[] args) {
		Conexao con = new Conexao();
		Connection conn = con.getConexao();
		if(conn == null) {
			System.out.println("Nao conectou");
		}else {
			System.out.println("FOi");
		}
	}
}
