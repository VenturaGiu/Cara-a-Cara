package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class jogoDao {
	
	public boolean insert(String jogada) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		try {
			String sql = "INSERT INTO tbjogada (jogada) values('?')";
			conn = conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, jogada);
			pstm.execute();
			
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally {
			conexao.fechar(conn);
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
			String aux = "";
			while(rs.next()){
				aux = rs.getString("jogada");
			}
			return aux;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			conexao.fechar(conn);
		}
	}
}
