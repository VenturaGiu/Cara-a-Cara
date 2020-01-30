package dao;

import javax.swing.Icon;
import javax.swing.JButton;

public class BotaoTab extends JButton{

	private Boolean caracteristicas[];
	private int coluna;
	private int linha;
	
	public BotaoTab(Icon icon) {
		super(icon);
		caracteristicas = new Boolean[16];
		for(int i=0;i<caracteristicas.length;i++) {
			caracteristicas[i] = false;
		}
	}
	
	public Boolean[] getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(Boolean[] caracteristicas) {
		this.caracteristicas = caracteristicas;
	}	
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	
	
	
	
}
