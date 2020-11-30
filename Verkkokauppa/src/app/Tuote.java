package app;

public class Tuote {
	
	
	protected String nimi;
	protected String kuvaus;
	protected double hinta;
	protected int tuotenro;
	// private int kpl;

	public Tuote()
	{
		nimi = "";
		kuvaus = "";
		hinta = 0;
		tuotenro = 0;
	}
	
	public Tuote(String n, String k, double h, int t) {
		
		nimi = n;
		kuvaus = k;
		hinta = h;
		tuotenro = t;
	}
	
	public void tulostaTiedot() {
		
		System.out.println("Nimi: " + nimi);
		System.out.println("Kuvaus: " + kuvaus);
		System.out.println("Hinta: " + hinta);
		System.out.println("Tuote nro: " + tuotenro);
	}
}

class Kori extends Tuote {
	
	public Kori()
	{
		nimi = "";
		kuvaus = "";
		hinta = 0;
		tuotenro = 0;
	}
	
	public Kori(String n, double h, int t) {
		
		nimi = n;
		hinta = h;
		tuotenro = t;
	}
}




	
	
	
	
	
	

