package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mains.Ranking;

public class RankingDao {
	public boolean cadastrar(Ranking ran) {
		Conexao con = new Conexao();
		Connection conn = null;
		try {
			String sql = "INSERT INTO tbPonto(nPonto, usuario) values(?, ?)";
			conn = con.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, ran.getnPonto());
			pstm.setString(2, ran.getUsuario());
			pstm.execute();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally {
			con.fechar(conn);
		}
	}
	public ArrayList<Ranking> listar(){
		Conexao con = new Conexao();
		Connection conn = null;
		try {
			String sql = "SELECT * FROM tbPonto ORDER BY nPonto DESC";
			conn = con.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			ArrayList<Ranking> runs = new ArrayList<Ranking>(); 
			while(rs.next()) {
				Ranking aux = new Ranking();
				aux.setnPonto(rs.getString("nPonto"));
				aux.setUsuario(rs.getString("usuario"));
				runs.add(aux);
			}
			return runs;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			con.fechar(conn);
		}
		
	}
	
	public boolean att(String nome, int pontos) {
		Conexao con = new Conexao();
		Connection conn = null;
		try {
			String sql = "UPDATE tbPonto SET nPonto = '"+ String.valueOf(pontos)+"' where usuario like '"+nome+"'";
			conn = con.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.execute();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally {
			con.fechar(conn);
		}
	}
}
