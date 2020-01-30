package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class UsuarioDao {
	
	
	public boolean cadastrar(Usuario user) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		
		try {
			String sql = "INSERT INTO tbusuario (nomeUsuario, senhaUsuario) values (?,?)";
			conn = conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getNome());
			pstm.setString(2, user.getSenha());
			
			pstm.execute();
			
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally {
			conexao.fechar(conn);
		}
		
	}
	
	public ArrayList<Usuario> getLogar(){
		Conexao conexao = new Conexao();
		Connection conn = null;
		try {
			String sql = "SELECT * from tbusuario";
			conn = conexao.getConexao();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			ArrayList<Usuario> Users = new ArrayList<Usuario>();
			while(rs.next()){
				Usuario p = new Usuario();
				p.setNome(rs.getString("nomeUsuario"));
				p.setSenha(rs.getString("senhaUsuario"));
				Users.add(p);
			}
			return Users;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			conexao.fechar(conn);
		}
	}
	
}
