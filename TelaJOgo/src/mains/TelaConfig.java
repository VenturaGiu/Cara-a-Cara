package mains;
import java.awt.event.*;
import java.io.InputStream;
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

public class TelaConfig extends JFrame{
	
	private JButton btMais = new JButton("+");
	private JButton btMenos = new JButton("-");
	private JLabel tf = new JLabel("100");
	private int tam = 50;
	private JLabel vol = new JLabel("Volume Musica: ");
	private JButton btMais2 = new JButton("+");
	private JButton btMenos2 = new JButton("-");
	private JLabel tf2 = new JLabel("100");
	private JLabel vol2 = new JLabel("Volume Sonoros: ");
	
	private JButton voltar = new JButton("←");
	private float controle = 1f;
	private Font f = new Font("",0,25);
	
	private String som = "../somFundo.wav";
	private Funcao f1;
	
	public TelaConfig(Funcao f) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e){
			
		}
		
		start();
		events();
		this.f1 = f;
		setVisible(true);
	}
	
	public void start() {
		setTitle("Configurações");
		setSize(400,400);
		setLayout(null);
		setLocationRelativeTo(null);
		
		setResizable(false);
		
		vol.setBounds(50,20,200,20);
		btMais.setBounds(170,50,tam,tam);
		btMais.setFocusable(false);
		btMenos.setBounds(50, 50, tam, tam);
		btMenos.setFocusable(false);
		tf.setBounds(125,50,tam,tam);
		
		tf.setFont(f);
		tf2.setFont(f);
		btMais.setFont(f);
		btMais2.setFont(f);
		btMenos.setFont(f);
		btMenos2.setFont(f);
		
		add(btMais);
		add(btMenos);
		add(tf);
		add(vol);
		
		vol2.setBounds(50,130,200,20);
		
		btMais2.setBounds(170,160,tam,tam);
		btMais2.setFocusable(false);
		btMenos2.setBounds(50,160, tam, tam);
		btMenos2.setFocusable(false);
		tf2.setBounds(125,160,tam,tam);
		
		add(btMais2);
		add(btMenos2);
		add(tf2);
		add(vol2);
		
		voltar.setBounds(50,getHeight() - 120, tam, tam );
		voltar.setFocusable(false);
		add(voltar);
		
	}
	
	public void events() {	
		btMais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ala = Integer.parseInt(tf.getText());
				ala += 10;
				if(ala > 100) {
					ala = 100;
				}
				
				if(ala < 100 && ala > 0) {
					controle += 0.1f;
					f1.setVolume(controle);
				}
				
				tf.setText(String.valueOf(ala));
				
			}
		});
		
		btMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ala = Integer.parseInt(tf.getText());
				ala -= 10;
				if(ala < 0) {
					ala = 0;
				}
				
				if(ala < 100 && ala > 0) {
					controle -= 0.1f;
					f1.setVolume(controle);
				}
				tf.setText(String.valueOf(ala));
			}
		});
		
		
		btMais2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ala = Integer.parseInt(tf2.getText());
				ala++;
				if(ala > 100) {
					ala = 100;
				}
				tf2.setText(String.valueOf(ala));
			}
		});
		
		btMenos2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ala = Integer.parseInt(tf2.getText());
				ala--;
				if(ala < 0) {
					ala = 0;
				}
				tf2.setText(String.valueOf(ala));
			}
		});
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		
	}
	
	
}
