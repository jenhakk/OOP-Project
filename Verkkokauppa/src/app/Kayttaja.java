package app;

import java.util.ArrayList;

public class Kayttaja {

	//Listat varastolle ja ostoskorille
	
	ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
	ArrayList<Kori> ostoskori = new ArrayList<Kori>();
	
	private String nimi;
	private String kuvaus;
	private double hinta;
	private int tuotenro;
	// private int kpl;
	
	
	Tuote tuote = new Tuote(nimi, kuvaus, hinta, tuotenro);
	Kori ostos = new Kori(nimi, hinta, tuotenro);
}

class Asiakas extends Kayttaja {
	
	int i;
	
	//N‰ytt‰‰ yksitt‰isen tuotteen tarkemmat tiedot, esim. kuvauksen
	protected void naytaTuotteenTiedot()
	
	{
		tuotteet.get(i).tulostaTiedot();
	}
	
	//Lis‰‰ tuotteen ostoskoriin
	protected void lisaaTuoteKoriin()

	{
		ostoskori.add(ostos);
	}
	
	//Tulostaa ostoskorin yhteissumman
	protected void naytaYhteissumma()
	
	{
		double summa;
	}
}

class Yllapito extends Asiakas {
	
	
	//tulostaa varaston tiedot
	private void tulostaVarasto()
	
	{
		for (int i=0; i<tuotteet.size(); i++)
			
		{
			System.out.println(tuotteet.get(i));
		}
	}
	
	//muuttaa yksitt‰isen tuotteen hintaa
	private void muutaHintaa()

	{
		
	}
	
	
	//Lis‰‰ yksitt‰isen tuotteen varastoon
	private void lisaaTuote()
	
	{
		tuotteet.add(tuote);
	}
}