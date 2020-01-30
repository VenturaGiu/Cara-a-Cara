package mains;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JFrame;

import dao.Usuario;
import dao.UsuarioDao;

import java.awt.SystemColor;
import javax.swing.GroupLayout.Alignment;


public class TelaLogin extends JFrame{
		
	private JButton btLogar = new JButton("Logar");
	private JButton btCancelar = new JButton("Cancelar");
	private JButton btCadastrar = new JButton("Cadastar");
	private JTextField tfApelido = new JTextField(5);
	private JPasswordField tpSenha = new JPasswordField(5);
	private JLabel lbApelido = new JLabel ("Apelido:");
	private JLabel lbSenha = new JLabel ("Senha:");
	private Funcao f;
	
	public TelaLogin (Funcao f) {
		componentes();
		events();
		setVisible(true);
		this.f = f;
}
	
	public void componentes() {
		/* Determinando o tamnho da tela */ 
		//width,height
		
		setTitle("Tela de Login");
		setSize(353,363);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Caixa de texto do Apelido */ 
		tfApelido.setBounds(51, 74, 250, 35);
		add(tfApelido);
		
		/*Labael Apelido*/
		lbApelido.setBounds(51, 31, 400, 50);
		add(lbApelido);
		
		/*Label Senha */
		lbSenha.setBounds(51, 102, 400, 50);
		add(lbSenha);
		
		// Caixa de texto da Senha
		tpSenha.setBounds(51, 140, 250, 35);
		add(tpSenha);
		
		
		
		/* Adicionando os Botões*/
		btLogar.setBounds(76, 196, 80, 30);
		btLogar.setBackground(new Color(0,191,255));
		btLogar.setForeground(Color.white);
		add(btLogar);
		
		btCancelar.setBounds(177, 196, 87, 30);
		btCancelar.setBackground(new Color(0,191,255));
		btCancelar.setForeground(Color.white);
		add(btCancelar);
		
		btCadastrar.setBounds(130, 248, 87, 30);
		btCadastrar.setBackground(new Color(0,191,255));
		btCadastrar.setForeground(Color.white);
		add(btCadastrar);
	}
	
	public void events() {
		btCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int opcao;
		         opcao = JOptionPane.showConfirmDialog(null,"Deseja Realmente Sair?","Atencão",JOptionPane.YES_NO_OPTION);  
		           if(opcao == JOptionPane.YES_OPTION){ 
		                 System.exit(0);
		           }
		           else if (opcao == JOptionPane.NO_OPTION){
		        	   setDefaultCloseOperation(HIDE_ON_CLOSE);
		           }
			}
		});
		
		btLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				UsuarioDao udao = new UsuarioDao();
				user.setLogin(tfApelido.getText());
				user.setNome(tfApelido.getText());
				user.setSenha(tpSenha.getText());
				
				ArrayList<Usuario> retorno = udao.getLogar();
				boolean logado = user.logar(retorno);
				if(logado == true) {
					JOptionPane.showMessageDialog(null, "Bem vindo " + tfApelido.getText() + "!");
					setVisible(false);
					String nome = tfApelido.getText();
					tela_Inicial ti = new tela_Inicial(f,nome);
				}else {
					JOptionPane.showMessageDialog(null, "Login ou senha incorreto(s)");
					tfApelido.setText("");
					tpSenha.setText("");
				}
			}
		});
		
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TelaCadastro cad = new TelaCadastro();
			}
		});
		
	}
	
}
