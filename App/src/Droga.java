
public class Droga {
	
	private int dystans;
	
	private Miasto odMiasta;
	private Miasto doMiasta;
	
	Droga(Miasto odMiasta, Miasto doMiasta){
		this.odMiasta = odMiasta;
		this.doMiasta = doMiasta;
		dystans = odMiasta.drogaDo(doMiasta);
	}
	
	public int getDystans(){
		return dystans;
	}
	
	public Miasto skad(){
		return odMiasta;
	}
	
	public Miasto dokad(){
		return doMiasta;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Droga od: "+this.skad()+" ==> Droga do: "+this.dokad() + " Ma dystans :" + this.getDystans();
	}

}