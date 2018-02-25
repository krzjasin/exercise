public class Chromosome {
	
	private static int idCounter = 1;
	private int fenotype;
	private String geneticInfo = "";
	
	private int id;
	private int adjustment;
	
	public Chromosome(){	
		for(int i = 0 ; i < 5 ; i++)
			geneticInfo += (Math.random()>=0.5)?"0":"1";
		
		id = idCounter++;
		fenotype = Integer.parseInt(geneticInfo,2);
	}
	
	public Chromosome(Chromosome chromosome){
		this.geneticInfo = chromosome.getGeneticInfo();
		fenotype = Integer.parseInt(geneticInfo,2);
		this.adjustment = 0;
		
		this.id = idCounter++;
	}
	
	public Chromosome(String geneticInfo){
		this.setGeneticInfo(geneticInfo);
		id = idCounter++;
	}
	
	public int getFenotype(){
		return fenotype;
	}
	
	public void setAdjustment(int adjustment) {
		this.adjustment = adjustment;
	}
	
	public double getAdjustment() {
		return adjustment;
	}
	
	public int getId() {
		return id;
	}
	
	public void setGeneticInfo(String geneticInfo) {
		this.geneticInfo = geneticInfo;
		fenotype = Integer.parseInt(geneticInfo,2);
	}
	
	public String getGeneticInfo() {
		return geneticInfo;
	}
	
	public Chromosome crossWith(Chromosome breeder, int locus){
		String firstGeneticPart = this.getGeneticInfo().substring(0, locus);
		String secondGeneticPart = breeder.getGeneticInfo().substring(locus);
		return new Chromosome(firstGeneticPart+secondGeneticPart);
	}
	
	public void mutate(){
		int random = (int) Math.floor(Math.random()*5);
		char[] bits = geneticInfo.toCharArray();
		bits[random] = (bits[random]=='0')?'1':'0';
		setGeneticInfo(geneticBitsToString(bits));
	}
	
	@Override
	public String toString() {
		return "Chromosom " + id + " posiada informację genetyczną: [ " + geneticInfoToString() + " ] i Fenotyp: " + fenotype + " Wynikujący dostosowaniem: " + adjustment;
	}
	
	private String geneticInfoToString(){
		String returnValue = "";
		char[] bits = geneticInfo.toCharArray();
		for(char bit : bits)
			returnValue += bit + " ";
		
		return returnValue;
	}
	
	private String geneticBitsToString(char[] bits){
		String returnValue = "";
		for(char bit : bits){
			returnValue += bit;
		}
		return returnValue;
	}
	
}