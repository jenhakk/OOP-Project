package app;

public class Tuote {
	
	
	protected String nimi;
	protected String kuvaus;
	protected double hinta;
	protected int tuotenro;
	protected int kappalemaara;
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
		//System.out.println("Kuvaus: " + kuvaus);
		System.out.println("Hinta: " + hinta);
		//System.out.println("Tuote nro: " + tuotenro);
		System.out.println("kappalemäärä: " + kappalemaara);
	}
	
public void tulostaTiedostoon() {
		
		
	}
}

class Kori extends Tuote {
	
	public Kori()
	{
		nimi = "";
		kuvaus = "";
		hinta = 0;
		tuotenro = 0;
		kappalemaara = 0;
	}
	
	public Kori(int t, String n, double h, int k) {
		
		tuotenro = t;
		nimi = n;
		hinta = h;
		kappalemaara = k;
	}
}




	
	
	
	
	
	

