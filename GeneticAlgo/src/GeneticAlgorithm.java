import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GeneticAlgorithm {
	
	private static final double populationAmount = 6;
	
	private static final double crossProbability = 0.7;
	private static final double mutationProbability = 0.5;
	
	private static final int finishCondition = 15;
	
	private static ArrayList<Chromosome> currentPopulation = new ArrayList<>();
	private static ArrayList<Chromosome> breedingCandidates = new ArrayList<>();
	private static ArrayList<Chromosome> oldPopulation = new ArrayList<>();
	
	private static Chromosome bestChromosome;
	
	private static Function function;
	
	private static ArrayList<Integer> iterationAdjustment = new ArrayList<>();

	public static void main(String[] args) {
				
		getInputFromUser();
		
		generateStartingPopulation();
		
		do{
			geneticPhases();
		}while(!sameAdjustmentForIteration());
		
		System.out.println("<------------- NAJLEPSZY CHROMOSOM: ---------->");
		System.out.println(bestChromosome);
		
	}
	
	private static void getInputFromUser(){
		Scanner in = new Scanner(System.in);
		System.out.println("Podaj cztery wartosci dla kolejno: a, b, c, d ");
		function = new Function(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
	}
	
	private static int bestChromosomeAdjustmentInPopulation(){
		int bestAdjustment = 0;
		for(int highest : iterationAdjustment)
			if(highest > bestAdjustment) bestAdjustment = highest;
		return bestAdjustment;
	}
	
	private static void geneticPhases(){
		generateAdjustmentForCurrentPopulation();
		getHighestAdjustment();
			showArray(currentPopulation);
		selectingChromosomesForBreeding();
			showArray(breedingCandidates);
		crossBreeding();
			showArray(currentPopulation);
		mutationProcess();
			showArray(currentPopulation);
	}
	
	public static void generateStartingPopulation(){
		System.out.println("<---GENEROWANIE POPULACJI STARTOWEJ--->");
		for(int i = 0; i < populationAmount; i++)
			currentPopulation.add(new Chromosome());
	}
	
	public static void generateAdjustmentForCurrentPopulation() {
		System.out.println("<---SPRAWDZANIE DOSTOSOWANIA CHROMOSOMÓW--->");
		for(Chromosome chromosome : currentPopulation)
			chromosome.setAdjustment(function.getValueForX(chromosome.getFenotype()));
	}
	
	public static void selectingChromosomesForBreeding(){
		System.out.println("<---DOBÓR KANDYDATÓW DO KRZYŻOWANIA--->");
		breedingCandidates = new ArrayList<>();
		FortuneWheel wheel = new FortuneWheel(currentPopulation);
		for(int i = 0; i < populationAmount -1 ; i++)
			breedingCandidates.add(wheel.turnWheel());	
		breedingCandidates.add(bestChromosome);
	}
	
	public static void crossBreeding(){
		oldPopulation = currentPopulation;
		currentPopulation = new ArrayList<>();
		System.out.println("<---KRZYŻOWANIE CHROMOSOMÓW--->");
		for(int i = 0; i < populationAmount; i=i+2){
			double cross = Math.random();
			if(cross<=crossProbability){
				int locus = (int)(Math.random()* populationAmount);
				currentPopulation.add(breedingCandidates.get(i).crossWith(breedingCandidates.get(i+1),locus));
				currentPopulation.add(breedingCandidates.get(i+1).crossWith(breedingCandidates.get(i),locus));
			} else {
				currentPopulation.add(new Chromosome(breedingCandidates.get(i)));
				currentPopulation.add(new Chromosome(breedingCandidates.get(i+1)));
			}
		}
		
	}
	
	public static void mutationProcess(){
		System.out.println("<---PROCES MUTOWANIA-->");
		for(Chromosome chromosome : currentPopulation)
			if(Math.random() <= mutationProbability)
				chromosome.mutate();	
	}
	
	public static void showArray(ArrayList<Chromosome> chromosomes){
		for(Chromosome chromosome : chromosomes)
			System.out.println(chromosome);
	}
	
	public static void getHighestAdjustment(){
		int highest = (int) ((bestChromosome==null)?0:bestChromosome.getAdjustment());

		for(Chromosome chromosome : currentPopulation)
			if(chromosome.getAdjustment()>=highest){
				highest = (int)chromosome.getAdjustment();
				bestChromosome = chromosome;
			}

		if(iterationAdjustment.isEmpty())
			iterationAdjustment.add(highest);
		else if(iterationAdjustment.get(0)==highest)
				iterationAdjustment.add(highest);
		else {
			iterationAdjustment = new ArrayList<>();
			iterationAdjustment.add(highest);
		}
	}
	
	public static boolean sameAdjustmentForIteration(){
		if(iterationAdjustment.size()==finishCondition)
			return true;
		else return false;
	}

}