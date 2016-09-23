package de.RR2015.modell;
import java.util.Random;


public class Zufall {
	
	private Random generator;
	
	public Zufall (int seed) {
		this.generator = new Random(seed);
	}
	
	public int getRandomeNumber () {
		return generator.nextInt();
	}
	
	public int fromToRandome (int from, int to) {
		int n = to - from + 1;
		int ruck = generator.nextInt() % n;
		ruck = Math.abs(ruck);
		ruck = from + ruck;
		return ruck;
	}
}
