package de.RR2015.modell;

import javafx.scene.paint.Color;

public class Roboter extends iBlock{
	
	private Farbe farbe;
	
	public Roboter (Farbe f, int x, int y){
		super(x,y);
		Zustand = false;
		this.farbe = f;
		
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Color getFarbe() {
		switch (farbe) {
		case BLAU:
			return Color.BLUE;
		case GRUN:
			return Color.GREEN;
		case ROT:
			return Color.RED;
		case GELB:
			return Color.YELLOW;
		default :
			return Color.BISQUE;
		}
	}
}