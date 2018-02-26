
public class Miasto {
	
	private int id;
	private int x;
	private int y;
	private boolean czyPoczatkowe;
	
	Miasto(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void ustawJakoPoczatkowe(){
		czyPoczatkowe=true;
	}
	
	public boolean czyPoczatkowe(){
		return czyPoczatkowe;
	}
	
	public int drogaDo(Miasto miasto){
		Double dist = Math.sqrt(Math.pow(this.x-miasto.getX(), 2)+(Math.pow(this.y-miasto.getY(),2)));
		return dist.intValue();
	}
	
	@Override
	public String toString() {
		return "Miasto "+id+" ["+x+" , "+y+"]";
	}

}