package app;

import java.util.ArrayList;

import data.Tietokanta;

public class Kayttaja {

	//Listat varastolle ja ostoskorille
	
	ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
	ArrayList<Kori> ostoskori = new ArrayList<Kori>();
	
	protected String nimi;
	protected String kuvaus;
	protected double hinta;
	protected int tuotenro;
	// private int kpl;
	
	
	Tuote tuote = new Tuote(nimi, kuvaus, hinta, tuotenro);
	Kori ostos = new Kori(nimi, hinta, tuotenro);
}

class Asiakas extends Kayttaja {
	
	int i;
	
	protected void naytaTuotteet()
	{
		Tietokanta.haeTuotteet();
	}
	
	
	//N‰ytt‰‰ yksitt‰isen tuotteen tarkemmat tiedot, esim. kuvauksen
	protected void naytaTuotteenTiedot(int tuotenro)
	
	{
		Tietokanta.naytaTuotteenKuvaus(tuotenro);
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
	
	protected void naytaOstoskori()
	{
		for (int i=0; i<ostoskori.size(); i++)
			
		{
			ostoskori.get(i).tulostaTiedot();
			System.out.println();
		}
	}
}

class Yllapito extends Asiakas {
	
	
	//tulostaa varaston tiedot
	protected void tulostaVarasto()
	
	{
		Tietokanta.haeTuotteet();
	}
	
	//muuttaa yksitt‰isen tuotteen hintaa
	protected void muutaHintaa(double hinta, int tuotenro)

	{
		
	}
	
	
	//Lis‰‰ yksitt‰isen tuotteen varastoon
	protected void lisaaUusiTuote()
	
	{
		tuotteet.add(tuote);
	}

	
}