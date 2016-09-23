package de.RR2015.modell;

public enum Farbe {GRUN,ROT,BLAU,GELB;
	
	public Farbe getFarbeIndex (int index) {
	
	switch(index) {
	case 0:
		return Farbe.GRUN;
	case 1:
		return Farbe.ROT;
	case 2:
		return Farbe.BLAU;
	case 3:
		return Farbe.GELB;
	default:
		return Farbe.GELB;
	}
  }
}



