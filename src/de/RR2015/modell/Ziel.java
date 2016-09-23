package de.RR2015.modell;

import javafx.scene.paint.Color;

public class Ziel extends iBlock{
	
	
	Farbe zielFarbe;
	public Ziel(Farbe f,int x, int y){
		super( x,  y);
		zielFarbe = f;
		Zustand = true;
	}
	public Color getZielFarbe() {
		switch (zielFarbe) {
		case BLAU:
			return Color.BLUE;
		case GRUN:
			return Color.GREEN;
		case ROT:
			return Color.RED;
		case GELB:
			return Color.YELLOW;
		default :
			return Color.RED;
		}
	}
	
	
}