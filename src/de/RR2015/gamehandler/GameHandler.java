package de.RR2015.gamehandler;

import java.util.LinkedList;

import de.RR2015.modell.*;

public class GameHandler {
	public int MAX_X;
	public int MAX_Y;
	
	Roboter aktuellerRoboter;
	Ziel aktuellesZiel;
	MapGenerator generator;
	LinkedList <iBlock> SteinListe = new LinkedList <iBlock>();
	int aktuellesGebot;
	int Zeit = 60;
	int Stepcounter = 0;
	
	public GameHandler(){
		
		generator = new MapGenerator(1234);
		SteinListe = generator.getBlockListe();
		
		MAX_Y = generator.getRows() - 1; //!!!!!!
		MAX_X = generator.getColoumns() - 1;
		
		aktuellerRoboter = (Roboter) SteinListe.getFirst();
		//hier jetzt get Last da das Ziel noch zum zeichnen gebraucht wird.
		aktuellesZiel = (Ziel) SteinListe.getLast();
		aktuellesGebot = Integer.MAX_VALUE;
	}
	
	/**
	 * Switch Case der die Bewegungsrichtung als eingabe bekommt
	 * 1:
	 * while Schleife die Solange geht bis es eine Kollision gegeben hat oder bis der Stein am Rand angekommen ist
	 * 2:
	 * for Schleife die alle Elemente aus der Liste durchgeht
	 * 3: 
	 * Wenn ein der Roboter mit einem Objekt kollidieren würde , würde die if Bedingung ein False auswerfen und unser boolscher Wert
	 * würde hier auf false gesetzt werden. Damit würde dann die bewegung aufhören.
	 * 4:
	 * Setzt die Position von dem Roboter um 1 in die Richtung in die er sich bewegen sollte
	 * 
	 * Am Ende wird der Stepcounter noch um einen erhöhrt, falls es einen Schritt gegeben hat
	 *  
	 * @param direct = Bewegungsrichtung 
	 */
	public void move(Direction direct){
		boolean oneStep = false;
		boolean stillMoving= true;
		switch (direct){
		case RIGHT:
			//1
			while (stillMoving && aktuellerRoboter.getX() < MAX_X){
				//2
				for (iBlock block: SteinListe){
					//3
					if (!block.Kollision(aktuellerRoboter.getX() + 1, aktuellerRoboter.getY()) 
							&& aktuellerRoboter.getX() + 1 <= MAX_X){
						stillMoving = false;
					}
				}
				//4
				if (stillMoving){
					oneStep = true;
					aktuellerRoboter.setLocation(aktuellerRoboter.getX() + 1, aktuellerRoboter.getY());
				}
			}
			break;
		case LEFT:
			while (stillMoving && aktuellerRoboter.getX() > 0){
				for (iBlock block: SteinListe){
					if (!block.Kollision(aktuellerRoboter.getX() - 1, aktuellerRoboter.getY()) 
							&& aktuellerRoboter.getX() - 1 >= 0){
						stillMoving = false;
					}
				}
				if (stillMoving){
					oneStep = true;
					aktuellerRoboter.setLocation(aktuellerRoboter.getX() - 1, aktuellerRoboter.getY());
				}
			}
			break;
		case UP:
			while (stillMoving && aktuellerRoboter.getY() > 0){
				for (iBlock block: SteinListe){
					if (!block.Kollision(aktuellerRoboter.getX(), aktuellerRoboter.getY() - 1) 
							&& aktuellerRoboter.getY() - 1 >= 0){
						stillMoving = false;
					}
				}
				if (stillMoving){
					oneStep = true;
					aktuellerRoboter.setLocation(aktuellerRoboter.getX(), aktuellerRoboter.getY() - 1);
				}
			}
			break;
		case DOWN:
			while (stillMoving && aktuellerRoboter.getY() < MAX_Y){
				for (iBlock block: SteinListe){
					if (!block.Kollision(aktuellerRoboter.getX(), aktuellerRoboter.getY() + 1) 
							&& aktuellerRoboter.getY() + 1 <= MAX_Y){
						stillMoving = false;
					}
				}
				if (stillMoving){
					oneStep = true;
					aktuellerRoboter.setLocation(aktuellerRoboter.getX(), aktuellerRoboter.getY() + 1);
				}
			}
			break;
		}
		//Erhöht den Stepcounter um 1, wenn eine Bewegung gemacht wurde
		if (oneStep){
		Stepcounter++;
		}
	}
	
	public LinkedList <iBlock> getMap () {
		return SteinListe;
	}
	
	public Ziel getZiel () {
		return aktuellesZiel;
	}
	
//	public void setAktuellerRoboter(Farbe f){
//		for (int i = 0; i < 4; i++){
//			if (((Roboter)SteinListe.get(i)).getFarbe().equals(f)){
//				aktuellerRoboter = (Roboter)SteinListe.get(i); 
//			}
//		}
//	}
	
	/**
	 * ACHTUNG i darf nur von 0 bis 3 groß sein
	 * @param i
	 */
	public void setRobot(int i){
		aktuellerRoboter = (Roboter)SteinListe.get(i);
	}
	
	public int getRows (){
		return generator.getRows();
	}
	
	public int getColoumns (){
		return generator.getColoumns();
	}
	
	public void newOperation(){
		
	}
}
