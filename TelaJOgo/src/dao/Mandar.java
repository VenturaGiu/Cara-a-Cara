package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Mandar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean aux = insert(in.next());
		if(aux) {
			System.out.println("Enviado");
		}else {
			System.out.println("Erro");
		}
	}
	
	public void recerber() {
		while(true) {
			System.out.println(select());
		}		
	}
	
	public String select() {
		Conexao conexao = new Conexao();
		Connection conn = null;
		try {
			String sql = "SELECT MAX(codJogada), jogada from tbJogada";
			conn = conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			String aux = rs.getString("jogada");
			return aux;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			conexao.fechar(conn);
		}
	}
	
	public static boolean insert(String jogada) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		try {
			String sql = "INSERT INTO tbJogada(jogada) values('"+jogada+"')";
			conn = conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setString(1, jogada);
			pstm.execute();
			
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally {
			conexao.fechar(conn);
		}
	}
}
