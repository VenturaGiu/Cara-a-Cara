package mains;

import java.util.HashMap;

import dao.BotaoTab;

public class PveJogo {
	int jogada = 0;
	BotaoTab bot;
	private int dica;
	Boolean[] cacOutro = new Boolean[16];
	
	public PveJogo() {
	}
	
	public int getDica() {
		dica = (int) Math.floor(Math.random() * 16);
		return dica;
	}
	
	public BotaoTab getBot() {
		return bot;
	}
	public void setBot(BotaoTab bot) {
		this.bot = bot;
	}
	
}
