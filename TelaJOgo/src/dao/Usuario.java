package dao;

import java.util.ArrayList;

public class Usuario {
	String nome;
	String login;
	String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean logar(ArrayList<Usuario> retorno) {
		for (int i = 0; i < retorno.size(); i++) {
			Usuario aux = retorno.get(i);
			if(this.getNome().equals(aux.getNome()) && this.getSenha().equals(aux.getSenha())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean apelidoDif(ArrayList<Usuario> retorno, Usuario user) {
		for (int i = 0; i < retorno.size(); i++) {
			Usuario aux = retorno.get(i);
			System.out.println(user.getNome() + " " + aux.getNome());
			if(user.getNome().equals(aux.getNome())) {
				return false;
			}
		}
		return true;
	}
	
}
