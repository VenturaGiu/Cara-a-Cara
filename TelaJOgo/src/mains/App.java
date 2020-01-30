package mains;
import javax.sound.sampled.Clip;

public class App {
	private static Clip oClip;
	
	public static void main(String[] args) {
		Funcao f = new Funcao();
		TelaLogin login = new TelaLogin(f);
		f.tocarSom(oClip);
	}
}
 