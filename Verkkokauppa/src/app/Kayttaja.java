package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

import data.*;

public class Kayttaja {

	// Listat varastolle ja ostoskorille

	ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
	ArrayList<Kori> ostoskori = new ArrayList<Kori>();
	ArrayList<Kori> ostoskori2 = new ArrayList<Kori>();

	public String filename = "src/data/kuitti.txt";

	protected String nimi;
	protected String kuvaus;
	protected double hinta;
	protected int tuotenro;
	protected int kappalemaara;

	// private int kpl;

	Tuote tuote = new Tuote(nimi, kuvaus, hinta, tuotenro);
	Kori ostos = new Kori(tuotenro, nimi, hinta, kappalemaara);
}

//************************************************************
class Asiakas extends Kayttaja {

	protected String etunimi;
	protected String sukunimi;
	protected String sPosti;
	protected String puhelin;
	protected String osoite;
	
	private String[] tarvittavatAsiakasTiedot = {"Etunimi", "Sukunimi", "S�hk�posti", "Puhelin", "Osoite"};
	private String[] asiakasTiedot = new String[5];
	
	int i;

	// N�ytt�� asiakkaalle yksitt�isen tuotteen nimen ja hinnan, palauttaa
	// stringin�
	protected String naytaTuotteenTiedot(int tuotenro)

	{
		String testi;
		testi = Tietokanta.naytaTuotteenTiedot(tuotenro);
		return testi;
	}

	// N�ytt�� asiakkaalle tietyn tuotteen nimen ja kuvauksen tuotenumeron
	// perusteella
	protected void naytaTuotteenKuvaus(int tuotenro) {
		Tietokanta.naytaKuvaus(tuotenro);
	}

	// Tulostaa asiakkaalle n�kyviin tuotteiden id:t, nimet ja hinnat
	protected void tulostaTuotelista() {
		Tietokanta.naytaTuotelista();
	}

	// Lisää tuotteen ostoskoriin
	protected void lisaaTuoteKoriin()

	{
		ostoskori.add(ostos);
	}

	// Lista-ostoskorin sis�lt�
	protected void naytaOstoskori() {
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < ostoskori.size(); i++)

		{
			ostoskori.get(i).tulostaTiedot();
			System.out.println();
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------");
	}

	// Tulostaa asiakkaan ostoskorin kuitiksi tiedostoon
	protected void tulostaKoriTiedostoon(double yhteensa, String filename, String alennus) {

		try {
			FileWriter fwriter = new FileWriter(filename, true);
			java.util.Date date = new java.util.Date();
			
			fwriter.write("\r\n");
			fwriter.write("\t\t'~,.,~'�~,.,~�'~,.,~'�~,.,~�");
			fwriter.write("\r\n\r\n");
			fwriter.write("\t\tVerkkokauppa Kuokka ja Nakki");
			fwriter.write("\r\n\r\n\r\n");
			fwriter.write("\t\tT�ss� kuitti ostoksistasi");
			fwriter.write("\r\n\r\n");

			for (int i = 0; i < ostoskori.size(); i++)

			{
				fwriter.write("\t\t\t" + ostoskori.get(i).nimi);
				fwriter.write("\t");
				String hinta = Double.toString(ostoskori.get(i).hinta);
				fwriter.write(hinta);
				fwriter.write(" �");
				fwriter.write("\r\n");

			}
			fwriter.write("\r\n\r\n");
			fwriter.write("\t\t\tAlennus: " + alennus + "\r\n\r\n");
			fwriter.write("\t\t\tYhteens�: " + yhteensa + " �" );
			fwriter.write("\r\n\r\n\r\n");
			fwriter.write("\t\t" + date.toString());
			fwriter.write("\r\n\r\n");
			fwriter.write("\tKiitos k�ynnist� ja tervetuloa uudelleen!");
			fwriter.write("\r\n\r\n");
			fwriter.write("\t\t'~,.,~'�~,.,~�'~,.,~'�~,.,~�");
			fwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Tulostaa kuitin tiedostosta konsoliin
	protected String tulostaKuittiKonsoliin(String filename) {

		String alltext = "";

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);
			String line;

			while ((line = br.readLine()) != null) {
				alltext = alltext + line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			alltext = "";
		}

		return alltext;
	}

	// tyhjent�� tekstitiedoston
	protected void tyhjennaKuitti(String filename) {

		try {

			FileWriter fwriter = new FileWriter(filename, false);

			fwriter.write("");
			fwriter.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	//Laskee ostosten yhteissumman ja palauttaa sen
	public double laskeSumma()
	{
		
		double summa = 0;
		
		for (int i = 0; i < ostoskori.size(); i++)
			
		{
			summa = summa + (ostoskori.get(i).hinta) * (ostoskori.get(i).kappalemaara);
		
		}
		
		return summa;
	}
	
	public String getTarvittavatAsiakasTiedot(int i) {
		return tarvittavatAsiakasTiedot[i];
	}
	
	public int getTarvittavatAsiakasTiedotLength() {
		return tarvittavatAsiakasTiedot.length;
	}
	
	public void setAsiakasTieto(int i, String tieto) {
		asiakasTiedot[i] = tieto;
	}
	
	public String getAsiakasTieto(int i) {
		return asiakasTiedot[i];
	}
	
	//poistaa ostoskorista Asiakkaan antaman tuotteen nimen perusteella (String)
	public void poistaTuoteKorista(String poisto)
	{
		Iterator<Kori> itr = ostoskori.iterator();
		
		while (itr.hasNext()) {
			
			 ostos = itr.next();
			 
			 if (ostos.nimi.equalsIgnoreCase(poisto)) {
				 
				 itr.remove();
			 }
		}
			
		{
			
		
		}
			
			
	}
	public String palautaTuotenro(String txt) {

		String[] temp = txt.split(", ");
		return temp[0];
	}
	public String palautaNimi(String txt) {

		String[] temp = txt.split(", ");
		return temp[1];
	}
	public String palautaHinta(String txt) {

		String[] temp = txt.split(", ");
		return temp[2];
	}
	

	
}
	

//************************************************************
class Yllapito extends Asiakas {

	// tulostaa varaston tiedot
	protected void tulostaVarasto()

	{

		Tietokanta.haeTuotteet();
	}

	// muuttaa yksittäisen tuotteen hintaa
	protected void muutaHintaa(double hinta, int tuotenro)

	{
		Tietokanta.muutaHintaa(hinta, tuotenro);
	}

	// Lisää yksittäisen tuotteen varastoon

	protected void lisaaUusiTuote(String n, String k, double h)

	{
		Tietokanta.lisaaUusiTuote(n, k, h);
	}

	// md5 hash pin-koodille
	protected String crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}

		MessageDigest digester;
		try {
			digester = MessageDigest.getInstance("MD5");

			digester.update(str.getBytes());
			byte[] hash = digester.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	protected void naytaTilaukset()
	{
		Tietokanta.tulostaTilaukset();
	}
}
