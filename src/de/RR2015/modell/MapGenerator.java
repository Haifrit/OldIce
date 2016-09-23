package de.RR2015.modell;
import java.util.LinkedList;

/**
 * Map Generator
 * 
 * ZEILENANZAHL:
 * Gibt die Tiefe des Spielfeldes an, wobei die Oberste Zeile die Zeile 0 ist,
 * die Unterste Zeile ist ZEILENANZAHL - 1.
 * 
 * SPALTENANZAHL:
 * Gibt die breite des Spielfeldes an, wobei die Spalte ganz links die Spalte 0 ist,
 * die Unterste Zeile ist SPALTENANZAHL - 1.
 * 
 * BLOCKS_OUTER_ROW:
 * Gibt die Anzahl der Blöcke in der Obersten und Untersten Zeile an. Bei z.B. 2 gibt
 * es in der Obersten und Untersten Zeile jeweils 2 Blöcke.
 * 
 * BLOCKS_OUTER_COLOUMN:
 * Gibt die Anzahl der Blöcke in den beiden äußersten Spalten an. Bei z.B. 3 gibt
 * es in den beiden äußeren Spalten jeweils 3 Blöcke.
 * 
 * CORNER_AMOUNT:
 * Gibt die maximale Anzahl an Ecken an. Wobei eine Ecke aus drei aneinander angrenzenden Blöcken besteht.
 * Falls eine Ecke teilweise über einer anderen Ecke liegt wird die komplette Ecke nicht generiert, dadurch können
 * weniger als die gewünschte Anzahl enstehen.
 * 
 * BLOCK_SPAWN_BORDER_RIGHT / BLOCK_SPAWN_BORDER_BOTTOM :
 * Gibt an in welchem bereich innere Blöcke und Ecken Spawnwn können. Wird durch die Vergrößerung des Spielfeldes
 * automatisch vergrößert.
 * 
 * INNER_BLOCK_AMOUNT:
 * Gibt an wie viele einzelne innere Blöcke Spawnen.
 * 
 * blockliste:
 * Enthält an den ersten 4 Positionen Roboter elemente ( GRUN,ROT,BLAU,GELB,BLOCK... ).
 * 
 * ziele:
 * Enthält alle möglichen Ziele.
 * 
 *  -------------------------------------------------------------------------------
 *  Werde viele innere Blöcke und Ecken gespawnd kann es sein das ein Ziel nicht erreicht werden kann!
 *  
 *  
 * @author Jan
 *
 */

public class MapGenerator {
	
	LinkedList<iBlock> blockListe = new LinkedList<iBlock>();
	LinkedList<Ziel> ziele = new LinkedList<Ziel>();
	int actualseed;
	private static final int ZEILENANZAHL = 14;
	private static final int SPALTENANZAHL = 9;
	private static final int BLOCKS_OUTER_ROW = 2;
	private static final int BLOCKS_OUTER_COLOUMN = 2;
	private static final int CORNER_AMOUNT = 3;
	private static final int BLOCK_SPAWN_BORDER_RIGHT = SPALTENANZAHL - 3;
	private static final int BLOCK_SPAWN_BORDER_BOTTOM = ZEILENANZAHL - 3;
	private static final int INNER_BLOCK_AMOUNT = 2;
	private Zufall z;
	
	/**
	 * Konstruktor ohne vorgegebenen Seed, der Zufällige Seed kann
	 * über getActualSeed ausgelesen werden.
	 */
	
	public MapGenerator () {
		int seed =(int) Math.random();
		this.z = new Zufall(seed);
		actualseed = seed;
		
		placeRobots();
		placeOuterBlocks(BLOCKS_OUTER_COLOUMN, BLOCKS_OUTER_ROW);
		placeCorner(CORNER_AMOUNT);
		placeInnerBlocks(INNER_BLOCK_AMOUNT);
	}
	
	/**
	 * Map generator mit Seed.
	 * @param seed kann angegeben werden um eine bestimmte karte zu "laden".
	 */
	
	public MapGenerator (int seed) {
		
		this.z = new Zufall(seed);
		actualseed = seed;
		
		placeRobots();
		placeOuterBlocks(BLOCKS_OUTER_COLOUMN, BLOCKS_OUTER_ROW);
		placeCorner(CORNER_AMOUNT);
		placeInnerBlocks(INNER_BLOCK_AMOUNT);
		

	}
	
	public int getRows() {
		return ZEILENANZAHL;
	}
	
	public int getColoumns() {
		return SPALTENANZAHL;
	}
	
	
	/**
	 * 
	 * @param amount gibt die Anzahl der inneren Blöcke an
	 */
	
	private void placeInnerBlocks (int amount) {
		boolean check = true;
		Stein  s;
		for (int i = 0; i < amount; i++) {
			check = true;
			while (check) {
				s = new Stein(z.fromToRandome(2, BLOCK_SPAWN_BORDER_RIGHT), z.fromToRandome(2, BLOCK_SPAWN_BORDER_BOTTOM));
				if (!blockListe.contains(s)) {
					blockListe.add(s);
					check = false;
				}
			}
		}
	}
	
	/**
	 * Platziert vier Roboter an zufälligen Positionen auf der Karte, können nicht in Blöcken Spawnen,
	 * könnten aber auf Zielen spawnen.
	 * 
	 */
	
	private void placeRobots () {
		boolean check;
		Farbe f = Farbe.GRUN;	// bessere Lösung ?
		for (int i = 0; i < 4; i++) {
			check = true;
			while (check) {
				Roboter r = new Roboter(f.getFarbeIndex(i), z.fromToRandome(0, 8), z.fromToRandome(0, 13));
				if (!blockListe.contains(r)) {
					blockListe.add(r);
					check = false;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param vertical platziert die angegebene anzahl an Blöcken auf der rechten und linken Seite
	 * @param horizontal platziert die angegebene anzahl an Blöcken oben und unten
	 */
	
	private void placeOuterBlocks(int vertical, int horizontal) {
		boolean check = true;
		int counter = 0;
		Stein s = new Stein(0, 0);
		
		//Obere Zeile
		while (counter < horizontal) {
			while (check) {
				s = new Stein(z.fromToRandome(1, 6), 0);
				if (!blockListe.contains(s)) {
					check = false;
				}
			}
			blockListe.add(s);
			counter++;
			check = true;
		}
		counter = 0;
		//Ganz Linke Spalte
		while (counter < vertical) {
			while (check) {
				s = new Stein(0, z.fromToRandome(1, 12));
				if (!blockListe.contains(s)) {
					check = false;
				}
			}
			blockListe.add(s);
			counter++;
			check = true;
		}
		counter = 0;
		//Unterste Zeile
		while (counter < horizontal) {
			while (check) {
				s = new Stein(z.fromToRandome(1, 6), 13);
				if (!blockListe.contains(s)) {
					check = false;
				}
			}
			blockListe.add(s);
			counter++;
			check = true;
		}
		counter = 0;
		//Ganz Rechte Spalte
		while (counter < horizontal) {
			while (check) {
				s = new Stein(SPALTENANZAHL - 1, z.fromToRandome(1, 12));
				if (!blockListe.contains(s)) {
					check = false;
				}
			}
			blockListe.add(s);
			counter++;
			check = true;
		}
	}
	
	/**
	 * 
	 * @param amount platziert die angegebene Anzahl an Ecken.
	 */
	
	private void placeCorner (int amount) {
		boolean check;
		Stein s;
		for (int i = 0; i < amount; i++) {
			check = true;
			while (check == true) {
				s = new Stein(z.fromToRandome(2, 5), z.fromToRandome(2, 11));
				if (!blockListe.contains(s)) {
					blockListe.add(s);
					makeCorner(s.getX(), s.getY());
					check = false;
				}
			}
		}
	}
	
	public LinkedList<iBlock> getBlockListe() {
		blockListe.add(this.getRandomeZiel());
		return blockListe;
	}
	
	/**
	 * Generiert zufällig eine von 4 Ecken und schreibt das dazugehörige Ziel in ziele.
	 * @param x Startposition der Ecke auf der x Achse
	 * @param y Startposition der Ecke auf der y Achse
	 */
	
	private void makeCorner (int x, int y) {
		int number = z.fromToRandome(1, 4);
		Stein alt = new Stein(x, y);
		Farbe farbe = Farbe.GRUN;
		Ziel ziel;
		Stein ecke1;
		Stein ecke2;
		
		switch (number) {
		case 1:
					ecke1 = new Stein(x, y + 1);
					ecke2 = new Stein(x + 1, y + 1);
					if (!blockListe.contains(ecke1) && !blockListe.contains(ecke2)) {
						blockListe.add(ecke1);
						blockListe.add(ecke2);
						ziel = new Ziel(farbe.getFarbeIndex(z.fromToRandome(0, 3)), x + 1, y);
						ziele.add(ziel);
					} else {
						blockListe.remove(alt);
					}
			break;
		case 2:
				ecke1 = new Stein(x, y + 1);
				ecke2 = new Stein(x - 1, y + 1);
				if (!blockListe.contains(ecke1) && !blockListe.contains(ecke2)) {
					blockListe.add(ecke1);
					blockListe.add(ecke2);
					ziel = new Ziel(farbe.getFarbeIndex(z.fromToRandome(0, 3)), x - 1, y);
					ziele.add(ziel);
				} else {
					blockListe.remove(alt);
				}
			break;
		case 3:
				ecke1 = new Stein(x, y - 1);
				ecke2 = new Stein(x - 1, y - 1);
				if (!blockListe.contains(ecke1) && !blockListe.contains(ecke2)) {
					blockListe.add(ecke1);
					blockListe.add(ecke2);
					ziel = new Ziel(farbe.getFarbeIndex(z.fromToRandome(0, 3)), x - 1, y);
					ziele.add(ziel);
				} else {
					blockListe.remove(alt);
				}
			break;
		case 4:
				ecke1 = new Stein(x, y - 1);
				ecke2 = new Stein(x + 1, y - 1);
				if (!blockListe.contains(ecke1) && !blockListe.contains(ecke2)) {
					blockListe.add(ecke1);
					blockListe.add(ecke2);
					ziel = new Ziel(farbe.getFarbeIndex(z.fromToRandome(0, 3)), x + 1, y);
					ziele.add(ziel);
				} else {
					blockListe.remove(alt);
				}
			break;
		default:
		}
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		iBlock block;
		
		for (Ziel ziel : ziele) {
			blockListe.add(ziel);
		}
		
		for (int i = 0; i < ZEILENANZAHL; i++) {
			for (int j = 0; j < SPALTENANZAHL; j++) {
				block = new iBlock(j, i);
				if (blockListe.contains(block)) {
					block = blockListe.get(blockListe.indexOf(block));
					if (block instanceof Stein) {
						sb.append("B");
					} else if ( block instanceof Roboter) {
					sb.append("R");
					} else if (block instanceof Ziel ) {
					sb.append("Z");
					} else {
					sb.append("O");
					}
			} else {
				sb.append("O");
			}
		}
			sb.append("\n");
	}
	return sb.toString();
}
	
	public Ziel getRandomeZiel () {
		int index = z.fromToRandome(0, ziele.size() - 1);
		return ziele.get(index);
	}
	

	public int getActualseed() {
		return actualseed;
	}
	
	
}

