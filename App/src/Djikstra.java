import java.util.ArrayList;

public class Djikstra {
	
	ArrayList<Miasto> miasta;
	ArrayList<Miasto> miastaDoOdwiedzenia;
	ArrayList<Droga> drogi;
	ArrayList<Miasto> miastaOdwiedzone;

	Djikstra(ArrayList<Miasto> miasta,
			ArrayList<Droga> drogi){
		this.miasta = miasta;
		this.drogi = drogi;
		miastaOdwiedzone = new ArrayList<>();
		miastaDoOdwiedzenia = new ArrayList<>();
	}
	
	public void wyliczTrasy(){

		Miasto poczatkowe = null;

		for (int i = 0; i < miasta.size(); i++) {
			if (miasta.get(i).czyPoczatkowe()) {
				poczatkowe = miasta.get(i);
			} else {
				miastaDoOdwiedzenia.add(miasta.get(i));
			}
		}

		Miasto obecne = poczatkowe;

		Droga droga = null;
	
		Miasto najblizsze = null;

		do {
			
			int dystans = Integer.MAX_VALUE;
			
			int indeksWybranegoMiasta = 0;
			
			int i = 0;

			for (Miasto miasto : miastaDoOdwiedzenia) {

				if ((obecne.drogaDo(miasto) < dystans) && (obecne != miasto)) {

					droga = new Droga(obecne, miasto);

					dystans = droga.getDystans();

					najblizsze = miasto;

					indeksWybranegoMiasta = i;
				}

				i++;
			}
			
			miastaOdwiedzone.add(najblizsze);
			miastaDoOdwiedzenia.remove(indeksWybranegoMiasta);
			obecne = najblizsze;

			drogi.add(droga);

		} while (!miastaDoOdwiedzenia.isEmpty());

		droga = new Droga(obecne, poczatkowe);

		drogi.add(droga);

	}

}