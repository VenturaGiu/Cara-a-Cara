package mains;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.BotaoTab;
import dao.RankingController;
import dao.RankingDao;
import dao.jogoDao;

public class TelaJogoBot extends JFrame implements ActionListener{
	
	RandomMatriz rm = new RandomMatriz();
	String[] nomesT = {"Crhistian","Pedro","Bruno","Vinicius","Thiago","Gustavo","Lucas","Romullo","Wesley","Magdalena","Kathia","Cesar","Murilo","Ricardo","Escanor","Diego","Gabriel","Lucia","Beatriz","Denzel","Mauro","Luiz","Daniela","João das Neves","Kleiton","Rusbe","URL","Erico","Junior","Giulia","Andre","Igor","Alexandre"};
	//imamgem
	//private ImageIcon im = new ImageIcon(getClass().getResource("../ala.jpg"));
	private ImageIcon im2;
	private ImageIcon background = new ImageIcon();
	private JLabel fundo = new JLabel(background);
	
	//tamanhos
	private int tamA = 800, tamL = 1200;
	private int dif = 7;
	private int px = 100, py = 100, rpx = px;
	private int tam = 100;

	//botoes
	private BotaoTab[][] buts = new BotaoTab[3][dif];
	private JLabel[][] nomes = new JLabel[3][dif];
	private JButton foto;
	private JLabel nome;
	private JButton sair = new JButton("Sair"); 
	private int mapN[][] = new int[3][dif];
	private JLabel[] dicasLb = new JLabel[16];
	HashMap<Integer, Boolean[]> mapCaracteristicas  = new HashMap<Integer, Boolean[]>();
	
	//variaveis de jogo
	public int modo = 0;
	public int euX = 0;
	private String[] dicas = {"Dicas","Usa oculos","Tem bigode","Tem barba","Tem Cabelo longo","Tem cabelo curto","É loiro","É moreno","Esta mostrando os dentes","É careca","Usa chapeu","É homem","É mulher","Tem olho claro","É negro","É branco","Tem franja"};
	public int euY = 0;
	private JComboBox box = new JComboBox(dicas);
	private JButton btnAcao;
	private JButton btnPronto;
	private ImageIcon carta;
	int rodada = 0;
	int preRodada = 0;
	BotaoTab chute = null;
	BotaoTab btnClicado;
	BotaoTab BotaoChute;
	boolean acerto = false;
	int dificuldade;
	int yChute, xChute;
	private String nomePessoa;
	int pontuacao = 0 ,ganho = 0;
	boolean euVenci = false;
	boolean continua = false;
	
	
	public TelaJogoBot(int modo, int dificulade, String nome) {
		this.modo = modo;
		this.nomePessoa = nome;
		this.dificuldade = dificulade;
		startHash();
		start();
		events();
	}
	
	public void start(){
		setSize(tamL, tamA);
		setLocationRelativeTo(null);
		setTitle("Cara-a-Cara");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		fundo.setBounds(0,0,tamL, tamA);
		carta = new ImageIcon(getClass().getResource("../fundocarta.jpg"));
		carta.setImage(carta.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
		sair.setBounds(getWidth() - ((4 * tam) + (tam / 2)), getHeight() - (tam + 20), tam, 30);
		add(sair);
		add(fundo);
		tab();
		
		int	x = (int) Math.floor(Math.random() * 3);
		int	y = (int) Math.floor(Math.random() * 7);			
		euX = x;
		euY = y;
		String url = "../personagens/Personagem" + mapN[x][y] +".png";	
		im2 = new ImageIcon(getClass().getResource(url));
		im2.setImage(im2.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
		foto = new JButton(im2);
		nome = new JLabel(nomesT[mapN[x][y] - 1] + " - Você", JLabel.CENTER);
		foto.setBounds(100, getHeight() - 250, tam ,tam);
		nome.setBounds(100, getHeight() - 180, tam, tam);
		add(foto);
		add(nome);
		
		box.setBounds(300, getHeight() - 230,400, 50);
		((JLabel)box.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		add(box);
		
		btnAcao = new JButton("Pedir dica");
		btnAcao.setBounds(710, getHeight() - 230, tam, 50);
		add(btnAcao);
		
//		btnPronto = new JButton("Pronto");
//		btnPronto.setBounds(770, getHeight() - 230, tam, 50);
//		add(btnPronto);
		
		for (int i = 0; i < dicasLb.length; i++) {
			dicasLb[i] = new JLabel();
			switch (i) {
			case 1:
				dicasLb[i].setText("Oculos - ");
				break;
			case 2:
				dicasLb[i].setText("Bigode - ");
				break;
			case 3:
				dicasLb[i].setText("Barba - ");
				break;
			case 4:
				dicasLb[i].setText("Cabelo longo - ");
				break;
			case 5:
				dicasLb[i].setText("Cabelo curto - ");
				break;
			case 6:
				dicasLb[i].setText("Loiro - ");
				break;
			case 7:
				dicasLb[i].setText("Moreno - ");
				break;
			case 8:
				dicasLb[i].setText("Dentes - ");
				break;
			case 9:
				dicasLb[i].setText("Careca - ");
				break;
			case 10:
				dicasLb[i].setText("Chapeu - ");
				break;
			case 11:
				dicasLb[i].setText("Homem - ");
				break;
			case 12:
				dicasLb[i].setText("Mulher - ");
				break;
			case 13:
				dicasLb[i].setText("Olho claro - ");
				break;
			case 14:
				dicasLb[i].setText("Negro - ");
				break;
			case 15:
				dicasLb[i].setText("Branco - ");
				break;
			case 16:
				dicasLb[i].setText("Franja - ");
				break;
			
			}
		}
		int pxLbDicas = getWidth() - 250, pyLbDicas = 50;
		for (int j = 0; j < dicasLb.length; j++) {
			dicasLb[j].setBounds(pxLbDicas, pyLbDicas, 300, 20);
			add(dicasLb[j]);
			pyLbDicas+= 30;
		}
	}
	
	public void startHash() {
		mapCaracteristicas.put(1, new Boolean[] {true, false, false, false, true, false, true, false , false, false, true, false, false ,false, true, false});
		mapCaracteristicas.put(2, new Boolean[] {false, false, true, false, true, false, true, true, false, false, true, false, false ,true, false, false});
		mapCaracteristicas.put(3, new Boolean[] {false, true, false, false, false, false, false, false, true, false, true, false, false ,false, true, false});
		mapCaracteristicas.put(4, new Boolean[] {false, false, true, false, true, false, true, false, false, false, true, false, false ,false, true, false});
		mapCaracteristicas.put(5, new Boolean[] {true, false, false, false, true, false, true, false, false, false, true, false, false ,false, true, true });
		mapCaracteristicas.put(6, new Boolean[] {false, false, true, false, true, false, true, true, false, false, true, false, true, false, true, true});
		mapCaracteristicas.put(7, new Boolean[] {true, false, false, false, true, false, true, false, false, false, true, false, false , true, false, false});
		mapCaracteristicas.put(8, new Boolean[] {false, true, false, false, true, false, true, false, false, false, true, false, false , true, false, false});
		mapCaracteristicas.put(9, new Boolean[] {false, false, false, false, true, false, true, false, false, false, true, false, false , true, false, true});
		mapCaracteristicas.put(10, new Boolean[] {false, false, false, true, false, false, true, false, false, false, false, true, false , true, false, false});
		mapCaracteristicas.put(11, new Boolean[] {true, false, false, true, false , true, false, true, false, false, false, true, false, false, true, false });
		mapCaracteristicas.put(12, new Boolean[] {true, false, true, true, false, true, false, true, false, false, true, false, false, false, true, false });
		mapCaracteristicas.put(13, new Boolean[] {false, false, true, false, true, false, true, true, false, false, true, false, false, false, true, false });
		mapCaracteristicas.put(14, new Boolean[] {false, false, true, false, true, false, true, true, false, false, true, false, false, true, false, false });
		mapCaracteristicas.put(15, new Boolean[] {false, true, false, false, true, false, false, false, false, false, true, false, false, false, true, true });
		mapCaracteristicas.put(16, new Boolean[] {true, false, false, false, true, false, false, false, false, false, true, false, false, false, true, true });
		mapCaracteristicas.put(17, new Boolean[] {true, false, false, false, true, false, true, false, false, false, true, false, false, false, true, false });
		mapCaracteristicas.put(18, new Boolean[] {true, false, false, false, true, false,true, false, false, false, false, true, false, true, false, true });
		mapCaracteristicas.put(19, new Boolean[] {true, false, false, false, true, false, true, false, false, false, false, true, false, false, true, true });
		mapCaracteristicas.put(20, new Boolean[] {true, false, false, false, true, false, true, true, false, false, true, false, false, false, true, false });
		mapCaracteristicas.put(21, new Boolean[] {true,true,false,false,true,false,true,true,false,true,true,false,true,false,true,false});
		mapCaracteristicas.put(22, new Boolean[] {true,true,false,false,true,false,true,true,false,true,true,false,true,false,true,false});
		mapCaracteristicas.put(23, new Boolean[] {false,false,false,true,false,true,false,false,false,false,false,true,true,false,true,false});
		mapCaracteristicas.put(24, new Boolean[] {false,false,false,true,false,false,false,false,false,false,true,false,true,false,true,true}	);
		mapCaracteristicas.put(25, new Boolean[] {false,true,true,false,false,false,false,false,true,false,true,false,true,false,true,false});
		mapCaracteristicas.put(26, new Boolean[] {false, false, false, true, false, false, true, true, false, false, true, false, false, false, true, true});
		mapCaracteristicas.put(27, new Boolean[] {false, false, false, false, true, true, false, true, false, true, true, false, false, false, true, true});
		mapCaracteristicas.put(28, new Boolean[] {true, false, false, false, true, false, true, false, false, false, true, false, false, false, true, false});
		mapCaracteristicas.put(29, new Boolean[] {false, false, false, false, false, false, false, true, true, false, true, false, false, true, false, false});
		mapCaracteristicas.put(30, new Boolean[] {true, false, false, true, false, false, false, true, false, false, false, true, false, false, true, false});
		mapCaracteristicas.put(31, new Boolean[] {false, false, false, false, false, false, true, false, true, false, true, false, false , true, false, false });
		mapCaracteristicas.put(32, new Boolean[] {true, true, true, false, true, false, true, false, false, false, true, false, false , false, true, true });
		mapCaracteristicas.put(33, new Boolean[] {true, false, false, false, true, false, true, false, false, false, true, false, false , true, false, true });
	}

	public void events() {
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ala = JOptionPane.showConfirmDialog(null, "Deseja sair?");
				if(ala == 0) {
					System.exit(0);
				}
			}
		});
		
		if(rodada == 0) {
			JOptionPane.showMessageDialog(null, "Peça uma dica!");
		}
		
		PveJogo pj = new PveJogo();
		int	x = (int) Math.floor(Math.random() * 3);
		int	y = (int) Math.floor(Math.random() * 7);
		pj.setBot(buts[x][y]);
		
		jogoDao jdDao = new jogoDao();
		
		if(modo == 2) {
			if(jdDao.select() != null) {
				preRodada++;
			}else {
				jdDao.insert("jogador1");
				String aux = jdDao.select();
				int resp = JOptionPane.showConfirmDialog(null, aux);
				
				if(preRodada >= 3) {
					rodada = 1;
					btnAcao.setText("Chutar");
					JOptionPane.showMessageDialog(null, "Rodad de chutes");
				}
			}
		}
		btnAcao.addActionListener(new ActionListener() {
			int difX = 0;
			int dica = 1;
			public void actionPerformed(ActionEvent e) {
				if(modo == 1) {
					BotaoTab aux = pj.getBot();
					if(rodada == 0) {
						btnAcao.setText("Pedir dica");
						if(aux.getCaracteristicas()[box.getSelectedIndex() - 1]) {
							JOptionPane.showMessageDialog(null, "Sim");
							String textLb = dicasLb[box.getSelectedIndex()].getText();
							textLb += " SIM";
							dicasLb[box.getSelectedIndex()].setText(textLb);
						}else{
							JOptionPane.showMessageDialog(null, "Nao");
							String textLb = dicasLb[box.getSelectedIndex()].getText();
							textLb += " NAO";
							dicasLb[box.getSelectedIndex()].setText(textLb);
						}
						box.setSelectedIndex(dica);
						int resp = JOptionPane.showConfirmDialog(null, box.getSelectedItem());
						if(resp == 0) {
							pj.cacOutro[dica - 1] = true;
						}else {
							pj.cacOutro[dica - 1] = false;
						}
						int x = (int) Math.floor(Math.random() * 9) + 1;
						BotaoTab euBotao = buts[euX][euY];
						switch(dificuldade) {
						case 0:
							 difX = 8;
							 pontuacao = 100;
							break;
						case 1:
							 difX = 6;
							 pontuacao = 200;
							break;
						case 2:
							 difX = 4;
							 pontuacao = 300;
							break;
						case 3:
							 difX = 2;
							 pontuacao = 400;
							break;
						case 4:
							pontuacao = 500;
							 difX = 0;
							break;
						}
						if(x >= difX && x <= 10) {
							BotaoChute = euBotao;
							acerto = true;
						}else {
							xChute = (int) Math.floor(Math.random() * 3);
							yChute = (int) Math.floor(Math.random() * 7);
							acerto = false;
						}
						dica++;
						
					}else if(rodada == 1) {
						if(chute != null) {
							if(chute == aux) {
								JOptionPane.showMessageDialog(null, "Acertou \nParabéns!");
								euVenci = true;
								rodada = 0;
							}else {
								JOptionPane.showMessageDialog(null, "Errou! :(");
								euVenci = false;
								rodada = 0;
							}
						}
						
						if(acerto == true) {
							JOptionPane.showConfirmDialog(null, "O seu personagem é o " + nomes[BotaoChute.getLinha()][BotaoChute.getColuna()].getText() + "?");
							pontuacao /= 2;
							System.out.println(pontuacao);
							if(euVenci == true) {
								JOptionPane.showMessageDialog(null, "Empate");								
							}else {
								JOptionPane.showMessageDialog(null, "Voce Perdeu!");
							}
							setVisible(false);
							Ranking rank = new Ranking();
							RankingController rc = new RankingController();
							rank.setUsuario(nomePessoa);
							rank.setnPonto(String.valueOf(pontuacao));
							int pontosAntigos = 0;
							boolean existe = false;
							ArrayList<Ranking> auxRanking = new ArrayList<>();
							auxRanking = rc.listarPodrutos();
							RankingDao rd = new RankingDao();
							for(Ranking r : auxRanking) {
								System.out.println(r.getUsuario() + " 	 	"  + rank.getUsuario());
								if(r.getUsuario().equals(rank.getUsuario())) {
									existe = true;
									if(existe == true) {
										pontosAntigos = Integer.parseInt(r.getnPonto());
									}
								}
							}
							if(existe == true) {
								pontuacao += pontosAntigos;
								rank.setnPonto(String.valueOf(pontuacao));
								rd.att(nomePessoa, pontuacao);
							}else {
								rc.cadastrarPodruto(rank);								
							}
						}else {
							JOptionPane.showConfirmDialog(null, "O seu personagem é o " + nomes[xChute][yChute].getText() + "?");
							if(euVenci == true) {
								JOptionPane.showMessageDialog(null, "Você Venceu!");								
							}else {
								JOptionPane.showMessageDialog(null, "Ninguem Venceu");	
								continua = true;
								pontuacao = 0;
							}
							if(continua == false) {
								setVisible(false);
								Ranking rank = new Ranking();
								RankingController rc = new RankingController();
								rank.setUsuario(nomePessoa);
								rank.setnPonto(String.valueOf(pontuacao));
								int pontosAntigos = 0;
								boolean existe = false;
								ArrayList<Ranking> auxRanking = new ArrayList<>();
								auxRanking = rc.listarPodrutos();
								RankingDao rd = new RankingDao();
								for(Ranking r : auxRanking) {
									System.out.println(r.getUsuario() + " 	 	"  + rank.getUsuario());
									if(r.getUsuario().equals(rank.getUsuario())) {
										existe = true;
										if(existe == true) {
											pontosAntigos = Integer.parseInt(r.getnPonto());
										}
									}
								}
								if(existe) {
									pontuacao += pontosAntigos;
									rank.setnPonto(String.valueOf(pontuacao));
									System.out.println(rank.getnPonto());
									rd.att(nomePessoa, pontuacao);
								}else {
									rc.cadastrarPodruto(rank);								
								}								
							}
						}
						
						rodada = 0;
						preRodada = 0;
						btnAcao.setText("Pedir dica");
					}
					
					preRodada++;
					if(preRodada == 3) {
						rodada = 1;
						btnAcao.setText("Chutar");
						JOptionPane.showMessageDialog(null, "Rodada de chute \nEscolha alguem para ser seu chutes");
					}
				}else if(modo == 2) {
					if(rodada == 0) {
						String minhaJogada = (String) box.getSelectedItem();
						jdDao.insert(minhaJogada + "?");
						preRodada++;						
					}
				}
			}
		});
		
	}
	
	public void rand() {
		rm.ini();
		int contador = 0;
		
		for (int i = 0; i < mapN.length; i++) {
			for (int j = 0; j < dif; j++) {
				mapN[i][j] = rm.getMap(contador);
				contador++;
			}
		}
		
		
		for (int i = 0; i < mapN.length; i++) {
			for (int j = 0; j < dif; j++) {
				nomes[i][j] = new JLabel(nomesT[mapN[i][j] - 1],JLabel.CENTER);
			}
		}
	}
	
	public void tab() {
		rand();
		for (int i = 0; i < buts.length; i++) {
			for (int j = 0; j < dif; j++) {
				String url = "../personagens/Personagem" + mapN[i][j] +".png";	
				ImageIcon img =  new ImageIcon(getClass().getResource(url));
				img.setImage(img.getImage().getScaledInstance(tam, tam, Image.SCALE_FAST));
				buts[i][j] = new BotaoTab(img);
				buts[i][j].setLinha(i);
				buts[i][j].setColuna(j);
				buts[i][j].setCaracteristicas(mapCaracteristicas.get(mapN[i][j]));
				buts[i][j].addActionListener(this);
			}
		}
		if(dif == 7) {
			px = 100;
		}else {
			int aux = (dif - 7) * -1;
			rpx += aux * (tam / 2);
			px = rpx;
		}
		
		for (int i = 0; i < buts.length; i++) {
			for (int j = 0; j < dif; j++) {
				if(dif == 8) {
					tam -= 20;
				}
				buts[i][j].setBounds(px, py, tam, tam);
				nomes[i][j].setBounds(px, py + 60, tam, tam);
				px += 120;
			}
			px = rpx;
			py += 150;
		}
		for (int i = 0; i < buts.length; i++) {
			for (int j = 0; j < dif; j++) {
				add(buts[i][j]);
				add(nomes[i][j]);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		btnClicado = (BotaoTab) e.getSource();
		//Boolean[] aux = btnClicado.getCaracteristicas();
		//JOptionPane.showMessageDialog(null, btnClicado.getCaracteristicas()[box.getSelectedIndex() - 1]?"SIM":"NAO");
		if(rodada == 0) {
				if(!(btnClicado.getIcon() == carta)) {
					btnClicado.setIcon(carta);
				}else {
					int i = btnClicado.getLinha();
					int j = btnClicado.getColuna();
					String url2 = "../personagens/Personagem" + mapN[i][j] +".png";	
					ImageIcon im3 = new ImageIcon(getClass().getResource(url2));
					im3.setImage(im3.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
					btnClicado.setIcon(im3);
					
				}
		}else if(rodada == 1) {
			chute = btnClicado;
		}
		
	}
}