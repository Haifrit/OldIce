package de.RR2015.modell;


public class iBlock {
	//True wenn man durchlaufen kann
	//False wenn man nicht durchlaufen kann
	protected boolean Zustand;
	
	protected int x;
	protected int y;
	
	public iBlock (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public boolean Kollision(int x, int y){
		if (this.x == x && this.y == y){
			return Zustand;
		}else {
			return true;
		}
	}
	
	
	@Override
	public boolean equals (Object b) {
		
		iBlock s = (iBlock) b;
		
		if (this.x == s.getX() && this.y == s.getY()) {
			return true;
		} else {
			return false;
		}
		
	}
}











