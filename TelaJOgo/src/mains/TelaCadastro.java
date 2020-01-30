package mains;
import javax.swing.*;
import dao.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCadastro extends JFrame {
	private JLabel lbApelido;
	private JLabel lbSenha;
	private JLabel lbSenhaConf;
	private JTextField tfApelido;
	private JPasswordField pfSenha;
	private JPasswordField pfSenhaConf;
	private JButton btCadastrar;
	private JButton btVoltar;
	
	private ImageIcon img = new ImageIcon(getClass().getResource("../foto.png"));
	private JLabel fundo = new JLabel(img);
	
	public TelaCadastro() { 
		inicializar(); 
		definiEventos(); 
	}
	
	private void inicializar(){
		setTitle("Calculadora"); 
		setSize(1000,800);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
																															
		fundo.setBounds(0,0,1000,800);
		
		btVoltar = new JButton("←");
		btVoltar.setBounds(10, 10, 70, 40);
		
		lbApelido = new JLabel("Digite seu apelido: ");
		lbApelido.setBounds(400, 250, 200, 20);
		tfApelido = new JTextField();
		tfApelido.setBounds(400, 280, 200, 20);
		
		lbSenha = new JLabel("Crie uma senha: ");
		lbSenha.setBounds(400, 310, 200, 20);
		pfSenha = new JPasswordField();
		pfSenha.setBounds(400, 340, 200, 20);
		
		lbSenhaConf = new JLabel("Confirme sua senha: ");
		lbSenhaConf.setBounds(400, 370, 200, 20);
		pfSenhaConf = new JPasswordField();
		pfSenhaConf.setBounds(400, 400, 200, 20);
		
		btCadastrar = new JButton("Enviar");
		btCadastrar.setBounds(400, 460, 70, 40);
		
		
		
		add(btVoltar);
		add(tfApelido);
		add(pfSenha);
		add(pfSenhaConf);
		add(btCadastrar);
		add(lbApelido);
		add(lbSenha);
		add(lbSenhaConf);
		add(fundo);
	}
	
	private void definiEventos() {
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				UsuarioDao uDao = new UsuarioDao();
				
				if(pfSenha.getText().equals(pfSenhaConf.getText())) {
					user.setNome(tfApelido.getText());
					user.setLogin(tfApelido.getText());
					user.setSenha(pfSenha.getText());
					ArrayList<Usuario> retorno = uDao.getLogar();
					boolean igual = user.apelidoDif(retorno, user);
					if(igual) {
						boolean cad = uDao.cadastrar(user);
						if(cad) {
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
							setVisible(false);
							TelaLogin lg = new TelaLogin(null);
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Apelido ja existe");
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null, "As senhas nao sao iguais");
					pfSenha.setText("");
					pfSenhaConf.setText("");
				}
			}
		});
	}
}
