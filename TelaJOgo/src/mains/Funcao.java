package mains;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Funcao {
	public Clip clip;

	public void tocarSom(Clip oClip) {
		clip = oClip;
		try {
            // Carrega o arquivo de áudio (não funciona com .mp3, só .wav) 
        String resource = "../somFundo.wav";
        InputStream input = getClass().getResourceAsStream(resource);
        clip = AudioSystem.getClip();
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(input);
        clip.open(audioInput);
 
        //clip.loop(0); // Toca uma vez
        clip.loop(Clip.LOOP_CONTINUOUSLY); // Toca continuamente (para o caso de músicas)
 
        } catch (Exception e) {
        }
	}
	public float getVolume() {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
	
	public void mutar() {
		clip.close();
	}
}
