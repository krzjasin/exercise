import java.util.ArrayList;

public class FortuneWheel {
	
	private ArrayList<WheelInput> inputs;
	private int adjustmentSum = 0;
	
	public FortuneWheel(ArrayList<Chromosome> chromosomes){
		
		inputs = new ArrayList<>();
		
		getInputChromosomesSum(chromosomes);
		
		for(int i = 0 ; i < inputs.size() ; i++){
			if(i == 0){
				inputs.get(i).setFrom(0);
				inputs.get(i).setTo((inputs.get(i).getChromosome().getAdjustment())/adjustmentSum);
			} else {
				inputs.get(i).setFrom(inputs.get(i-1).getTo());
				inputs.get(i).setTo(((inputs.get(i).getChromosome().getAdjustment())/adjustmentSum)+(inputs.get(i-1).getTo()));
			}
		}
		
	}
	
	private void getInputChromosomesSum(ArrayList<Chromosome> chromosomes){
		for(Chromosome chromosome : chromosomes){
			adjustmentSum += chromosome.getAdjustment();
			inputs.add(new WheelInput(chromosome));
		}
	}
	
	public Chromosome turnWheel(){
		double choice = Math.random();
		for(WheelInput input : inputs)
			if((choice>=input.getFrom())&&(choice<input.getTo()))
				return input.getChromosome();
		
		return null;
	}
	
	private class WheelInput{
		
		private Chromosome chromosome;
		private double from;
		private double to;
		
		public WheelInput(Chromosome chromosome){
			this.chromosome = chromosome;
		}
		
		public void setFrom(double from) {
			this.from = from;
		}
		
		public void setTo(double to) {
			this.to = to;
		}
		
		public double getFrom() {
			return from;
		}
		
		public double getTo() {
			return to;
		}
		
		public Chromosome getChromosome(){
			return chromosome;
		}
		
		@Override
		public String toString() {
			return "Szansa chromosomu: " + chromosome.getId() + " od " + from + " do " + to;
		}
		
	}

}