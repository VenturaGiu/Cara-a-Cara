package mains;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.RankingController;

public class tela_Inicial extends JFrame{
	
	String[] Dificuldades = { "INICIANTE", "AMADOR", "BOM", "MESTRE", "DESAFIO HARDCORE" };

	private JButton btSingle = new JButton("Singleplayer");
	private JButton btJogar = new JButton("JOGAR");
	private JButton btTutorial = new JButton("TUTORIAL");
	private JComboBox cbDificuldade = new JComboBox(Dificuldades);

	private ImageIcon fundo = new ImageIcon(getClass().getResource("/imagem/fundgif.gif"));
	
	private ImageIcon IcConfig = new ImageIcon(getClass().getResource("/imagem/gear-option.png"));
	
	private ImageIcon IcRank = new ImageIcon(getClass().getResource("/imagem/clasificacion.png"));
	
	private ImageIcon IcVol = new ImageIcon(getClass().getResource("/imagem/volume.png"));
	
	private ImageIcon IcNoVol = new ImageIcon(getClass().getResource("/imagem/Semvolume.png"));
	
	private ImageIcon fundoHard = new ImageIcon(getClass().getResource("/imagem/fundoHARDCORE.jpg"));
	
	private JLabel fundoL = new JLabel(fundo);
	
	private boolean mute = false;
	
	private boolean hard = false;
	
	private JButton btRanking = new JButton(IcRank);
	private JButton btConfig = new JButton(IcConfig);
	private JButton btSom = new JButton(IcVol);
	private Funcao f;
	private String nome;
	
	public tela_Inicial(Funcao f,String nome) {
		this.nome = nome;
		start();
		eventos();
		this.f = f;
	}


	public void start() {
		Font font = new Font("Arial",0,30);
		
		Color azulCalro = new Color(0,191,255);
		Color cinzaCalro = new Color(45,123,204);
		
		setTitle("GUESS WHO?");
		setSize(1000,800);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		fundoL.setBounds(0, 0, 1000, 800);
		
//		btJogar.setFont(new Font("Sans-serif", Font.BOLD, 25));
//		btJogar.setBackground(Color.WHITE);
//		btJogar.setBorderPainted(false);
//		btJogar.setForeground(azulCalro);
//		btJogar.setBounds(385, 460, 200, 50);
//		add(btJogar);
		
		btTutorial.setFont(new Font("Sans-serif", Font.BOLD, 20));
		btTutorial.setBackground(Color.WHITE);
		btTutorial.setBorderPainted(false);
		btTutorial.setForeground(azulCalro);
		btTutorial.setBounds(385, 520, 200, 50);
		add(btTutorial);
		
		btSingle.setFont(new Font("Sans-serif", Font.BOLD, 25));
		btSingle.setBackground(Color.WHITE);
		btSingle.setBorderPainted(false);
		btSingle.setForeground(azulCalro);
		btSingle.setBounds(385, 460, 200, 50);
		add(btSingle);
		
		cbDificuldade.setFont(new Font("Sans-serif", Font.BOLD, 15));
		cbDificuldade.setBackground(Color.WHITE);
		((JLabel)cbDificuldade.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		cbDificuldade.setBorder(BorderFactory.createLineBorder(Color.BLACK, -1));
		cbDificuldade.setForeground(Color.RED);
		cbDificuldade.setBounds(385, 580, 200, 50);
		add(cbDificuldade);
		
		
		
		btRanking.setBounds(20, 30, 50, 50);
		btRanking.setBackground(cinzaCalro);
		btRanking.setBorderPainted(false);
		add(btRanking);
		
		btConfig.setBounds(830, 30, 50, 50);
		btConfig.setBackground(cinzaCalro);
		btConfig.setBorderPainted(false);
		add(btConfig);
		
		btSom.setBounds(900, 30, 50, 50);
		btSom.setBackground(cinzaCalro);
		btSom.setBorderPainted(false);
		add(btSom);
		
		add(fundoL);
		mute();
	}
	
	public void mute() {
		
		btSom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if (mute == false) {
					btSom.setIcon(IcNoVol);
					mute = true;
					f.mutar();
				}
				else {
					btSom.setIcon(IcVol);
					mute = false;
					f.tocarSom(f.clip);
				}
				
			}});
		
		}
	
	public void hard() {
			
			cbDificuldade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
									
					if (Dificuldades.equals("DESAFIO HARDCORE")) {
						fundoL.setIcon(fundoHard);
						hard = true;
					}
					else {
						fundoL.setIcon(fundo);
						hard = false;
					}
					
				}});
			
			}
	
	
	
	private void eventos() {
		btConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConfig tc = new TelaConfig(f);
			}
		});
		btSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaJogoBot tj = new TelaJogoBot(1,cbDificuldade.getSelectedIndex(),nome);
			}
		});
		btJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaJogoBot tj = new TelaJogoBot(0,0,nome);
			}
		});
		btRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Ranking> runs = new ArrayList<Ranking>();
				RankingController pc = new RankingController();
				runs = pc.listarPodrutos();
				String podsS = "";
				if(runs != null) {
					for(Ranking p: runs) {
						podsS += "Pontos: " + p.getnPonto() + "\nUsuario: " + p.getUsuario() + "\n";
					}
				}
				ViewRanking2 vR2 = new ViewRanking2(runs);
			}
		});
	}
	
}
