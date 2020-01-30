package mains;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import dao.RankingController;

public class ViewRanking2 extends JFrame{
	private static final Border Border = null;
	private JLabel ranking;
	private JLabel usuario;
	private JLabel pontuacao;
	private JLabel jLabelPonto;
	private JLabel jLabelNome;
	private JLabel jLabelRan;
	private JLabel umJ;
	private JLabel doisJ;
	private JLabel tresJ;
	private ImageIcon imagem;
	private ImageIcon um;
	private ImageIcon dois;
	private ImageIcon tres;
	private JLabel fundo;
	private Font f;
	private ArrayList<Ranking> runs;
	private JButton voltar;
	
	public ViewRanking2(ArrayList<Ranking> runs) {
		this.runs = runs;
		start();
		eventos();
		setVisible(true);
	}
	
	public void start() {
		setSize(1000, 800);
		setTitle("Ranking");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		Color azul = new Color(135,206,250);
		Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
		
		voltar = new JButton("Voltar");
		voltar.setBounds(10, 10, 100, 30);
		add(voltar);
		
		f = new Font("", 0, 30);
		int px = 20, py = 20, i = 1;
		
		ranking = new JLabel("Ranking");
		ranking.setBounds(220, 10, 170, 100);
		ranking.setFont(f);
		ranking.setForeground(Color.WHITE);
		add(ranking);
		
		usuario = new JLabel("Usuário");
		usuario.setBounds(420, 10, 170, 100);
		usuario.setFont(f);
		usuario.setForeground(Color.WHITE);
		add(usuario);
		
		pontuacao = new JLabel("Pontuação");
		pontuacao.setBounds(620, 10, 170, 100);
		pontuacao.setFont(f);
		pontuacao.setForeground(Color.WHITE);
		add(pontuacao);
		
		
		//barrinha
		JPanel divPontos = new JPanel();
		divPontos.setBounds(0, 0, 700, 300);
		divPontos.setOpaque(true);
		divPontos.setBackground(azul);
		divPontos.setBorder(border);
		
		divPontos.setLayout(null);
		JScrollPane scroll = new JScrollPane(divPontos);
		scroll.setSize(700, 400);
		
		um = new ImageIcon(getClass().getResource("../um.png"));
		um.setImage(um.getImage().getScaledInstance(60, 60, Image.SCALE_FAST));
		umJ = new JLabel(um);
		umJ.setBounds(160, 180, 60, 60);
		add(umJ);
		
		dois = new ImageIcon(getClass().getResource("../dois.png"));
		dois.setImage(dois.getImage().getScaledInstance(60, 60, Image.SCALE_FAST));
		doisJ = new JLabel(dois);
		doisJ.setBounds(160, 260, 60, 60);
		add(doisJ);
		
		tres = new ImageIcon(getClass().getResource("../tres.png"));
		tres.setImage(tres.getImage().getScaledInstance(60, 60, Image.SCALE_FAST));
		tresJ = new JLabel(tres);
		tresJ.setBounds(160, 340, 60, 60);
		add(tresJ);
		for(Ranking r : runs) {
				
			//Colocação no ranking
			jLabelRan = new JLabel(String.valueOf(i+"°"));
			jLabelRan.setFont(f);
			jLabelRan.setBounds(px +130 ,py + 20,50,40);
			jLabelRan.setForeground(Color.BLACK);
			divPontos.add(jLabelRan);
			
			//nome do usuário
			jLabelNome = new JLabel(r.getUsuario());
			jLabelNome.setFont(f);
			jLabelNome.setBounds(px +250,py + 20,200,40);
			jLabelNome.setForeground(Color.BLACK);
			divPontos.add(jLabelNome);
			
			
			//pontuação do usuário
			jLabelPonto = new JLabel(r.getnPonto(), JLabel.CENTER);
			jLabelPonto.setBounds(px + 400, py + 20, 200, 40);
			jLabelPonto.setFont(f);
			jLabelPonto.setForeground(Color.BLACK);
			divPontos.add(jLabelPonto);
			
			divPontos.setPreferredSize(new Dimension(px, py));
			
			py += 80;
			i++;
		}
		
		scroll.setLocation(140, 150);
		add(scroll);
		
		//imagem de fundo
		imagem = new ImageIcon(getClass().getResource("../fundoAzul.jpg"));
		fundo = new JLabel(imagem);
		fundo.setBounds(0,0,getWidth(),getHeight());
		add(fundo);
	}
	public void eventos() {
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	}
}
