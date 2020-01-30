package mains;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaInicial extends JFrame{
	
	String[] Dificuldades = { "INICIANTE", "AMADOR", "BOM", "MESTRE", "DESAFIO HARDCORE" };


	private JButton btJogar = new JButton("JOGAR");
	private JButton btTutorial = new JButton("TUTORIAL");
	private JComboBox cbDificuldade = new JComboBox(Dificuldades);
	private boolean apertado = false;
	
	private JButton btRanking = new JButton("");
	private JButton btConfig = new JButton("");
	private JButton btSom = new JButton("");
	
	public TelaInicial(){
		start();
		eventos();
	}

	public void start() {
		Font font = new Font("Arial",0,30);
		
		Color azulCalro = new Color(0,191,255);
		
		setTitle("GUESS WHO?");
		setSize(1000,800);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		btJogar.setFont(new Font("Sans-serif", Font.BOLD, 25));
		btJogar.setBackground(azulCalro);
		btJogar.setForeground(Color.WHITE);
		btJogar.setBounds(385, 380, 200, 50);
		add(btJogar);
		
		btTutorial.setFont(new Font("Sans-serif", Font.BOLD, 20));
		btTutorial.setBackground(azulCalro);
		btTutorial.setForeground(Color.WHITE);
		btTutorial.setBounds(385, 440, 200, 50);
		add(btTutorial);
		
		cbDificuldade.setFont(new Font("Sans-serif", Font.BOLD, 15));
		cbDificuldade.setBackground(Color.WHITE);
		cbDificuldade.setForeground(azulCalro);
		cbDificuldade.setBounds(385, 500, 200, 50);
		add(cbDificuldade);
		
		
		
		btRanking.setBounds(20, 30, 50, 50);
		btRanking.setBackground(Color.WHITE);
		add(btRanking);
		
		btConfig.setBounds(830, 30, 50, 50);
		btConfig.setBackground(Color.WHITE);
		add(btConfig);
		
		btSom.setBounds(900, 30, 50, 50);
		btSom.setBackground(Color.WHITE);
		add(btSom);
		
		inserirIcone();
	}
	
	public void inserirIcone() {
		btConfig.setIcon(new ImageIcon(getClass().getResource("/imagem/gear-option.png")));
		
		btRanking.setIcon(new ImageIcon(getClass().getResource("/imagem/clasificacion.png")));
		
		btSom.setIcon(new ImageIcon(getClass().getResource("/imagem/volume-up-interface-symbol.png")));
	}

	public void eventos() {
		btJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//TelaJogoBot jogo = new TelaJogoBot(2);
			}
		});
		btConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TelaConfig tc = new TelaConfig(true);
				//tc.setVisible(true);
			}
		});
		
	}
	
	public boolean isAp() {
		return apertado;
	}
	
}
