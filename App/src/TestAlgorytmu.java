import java.util.ArrayList;
import java.util.Scanner;

public class TestAlgorytmu {
	
	static ArrayList<Miasto> miastaDoOdwiedzenia;
	static ArrayList<Droga> drogi;

	public static void main(String[] args) {

		
		miastaDoOdwiedzenia = new ArrayList<>();
		drogi = new ArrayList<>();

		System.out.println("Podaj ilosc miast: ");
		Scanner in = new Scanner(System.in);
		int iloscMiast = in.nextInt();

		WpiszMiasta(iloscMiast);
		

		for(Miasto miasto : miastaDoOdwiedzenia){
			System.out.println(miasto);
		}

		
		System.out.println("Podaj miasto poczatkowe: ");
		int poczatkowe = in.nextInt();

		
		UstawPoczatkowe(poczatkowe);
		// Utworzenie zmiennej przechowującej obiekt Djikstra
		Djikstra djikstra = new Djikstra(miastaDoOdwiedzenia, drogi);

		djikstra.wyliczTrasy();

		int sumaDrog = 0;

		for(Droga droga : drogi){
			System.out.println(droga);
			sumaDrog += droga.getDystans();
		}
		System.out.println("Suma dystansu przebytych dróg: " + sumaDrog);
		
	}
	
	public static void WpiszMiasta(int iloscMiast){
		Scanner in = new Scanner(System.in);
		for(int i = 0 ; i < iloscMiast ; i++){
			System.out.println("Podaj X i Y dla miasta "+i);
			miastaDoOdwiedzenia.add(new Miasto(in.nextInt(), in.nextInt(), i+1));
		}
	}
	
	public static void UstawPoczatkowe(int poczatkowe){
		miastaDoOdwiedzenia.get(poczatkowe-1).ustawJakoPoczatkowe();
	}

}