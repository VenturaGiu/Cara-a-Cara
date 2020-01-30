package mains;

public class RandomMatriz {
	int num[] = new int[33];
	public void ini() {
		for (int i = 0; i < num.length; i++) {
			num[i] = i + 1;
		}
		num = aleatoria(num);
		
	}
	
	public static int[] aleatoria(int a[]) {
		int j, x, i;
		for (i = a.length - 1; i > 0; i--) {
			j = (int) Math.floor(Math.random() * (i + 1));
			x = a[i];
			a[i] = a[j];
			a[j] = x;
		}
		return a;
	}
	
	public int getMap(int a) {
		return num[a];
	}
}