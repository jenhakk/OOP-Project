package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.io.Serializable;
import data.*;

public class Kayttaja {

	// Listat varastolle ja ostoskorille

	ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
	ArrayList<Kori> ostoskori = new ArrayList<Kori>();

	public String filename = "src/data/kuitti.txt";

	protected String nimi;
	protected String kuvaus;
	protected double hinta;
	protected int tuotenro;

	// private int kpl;

	Tuote tuote = new Tuote(nimi, kuvaus, hinta, tuotenro);
	Kori ostos = new Kori(nimi, hinta);
}

//************************************************************
class Asiakas extends Kayttaja {

	int i;

	// Nï¿½yttï¿½ï¿½ asiakkaalle yksittï¿½isen tuotteen nimen ja hinnan, palauttaa
	// stringinï¿½
	protected String naytaTuotteenTiedot(int tuotenro)

	{
		String testi;
		testi = Tietokanta.naytaTuotteenTiedot(tuotenro);
		return testi;
	}

	// Nï¿½yttï¿½ï¿½ asiakkaalle tietyn tuotteen nimen ja kuvauksen tuotenumeron
	// perusteella
	protected void naytaTuotteenKuvaus(int tuotenro) {
		Tietokanta.naytaTuotteenKuvaus(tuotenro);
	}

	// Tulostaa asiakkaalle nï¿½kyviin tuotteiden id:t, nimet ja hinnat
	protected void tulostaTuotelista() {
		Tietokanta.naytaTuotelista();
	}

	// LisÃ¤Ã¤ tuotteen ostoskoriin
	protected void lisaaTuoteKoriin()

	{
		ostoskori.add(ostos);
	}

	// Tulostaa ostoskorin yhteissumman
	protected void naytaYhteissumma()

	{
		double summa;
	}

	// Lista-ostoskorin sisï¿½ltï¿½
	protected void naytaOstoskori() {
		for (int i = 0; i < ostoskori.size(); i++)

		{
			ostoskori.get(i).tulostaTiedot();
			System.out.println();
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------");
	}

	// Tulostaa asiakkaan ostoskorin kuitiksi tiedostoon
	protected void tulostaKoriTiedostoon(String filename) {

		try {
			FileWriter fwriter = new FileWriter(filename, true);
			java.util.Date date = new java.util.Date();

			fwriter.write("\t\t'~,.,~'‘~,.,~’'~,.,~'‘~,.,~’");
			fwriter.write("\r\n\r\n");
			fwriter.write("\t\tVerkkokauppa Kuokka ja Nakki");
			fwriter.write("\r\n\r\n\r\n");
			fwriter.write("\t\tTässä kuitti ostoksistasi");
			fwriter.write("\r\n\r\n");

			for (int i = 0; i < ostoskori.size(); i++)

			{
				fwriter.write("\t\t\t" + ostoskori.get(i).nimi);
				fwriter.write("\t");
				String hinta = Double.toString(ostoskori.get(i).hinta);
				fwriter.write(hinta);
				fwriter.write(" €");
				fwriter.write("\r\n");

			}
			fwriter.write("\r\n\r\n");
			fwriter.write("\t\tYhteensä:");
			fwriter.write("\r\n\r\n\r\n");
			fwriter.write("\t\t" + date.toString());
			fwriter.write("\r\n\r\n");
			fwriter.write("\tKiitos käynnistä ja tervetuloa uudelleen!");
			fwriter.write("\r\n\r\n");
			fwriter.write("\t\t'~,.,~'‘~,.,~’'~,.,~'‘~,.,~’");
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
	
protected void tyhjennaKuitti(String filename) {
		
		try {
			
			FileWriter fwriter = new FileWriter(filename, false);
			
			fwriter.write("");
			fwriter.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}

//************************************************************
class Yllapito extends Asiakas {

	// tulostaa varaston tiedot
	public void tulostaVarasto()

	{

		Tietokanta.haeTuotteet();
	}

	// muuttaa yksittÃ¤isen tuotteen hintaa
	protected void muutaHintaa(double hinta, int tuotenro)

	{
		Tietokanta.muutaHintaa(hinta, tuotenro);
	}

	// LisÃ¤Ã¤ yksittÃ¤isen tuotteen varastoon

	protected void lisaaUusiTuote(String n, String k, double h)

	{
		Tietokanta.lisaaUusiTuote(n, k, h);
	}

	// Kirjoittaa kuitin tiedostoon
//	public void kirjoitaTiedostoon(String txt, String filename) {
//		
//		try {
//			java.util.Date date = new java.util.Date();
//			//Valinta true lopussa aiheuttaa sen ettei ylikirjoiteta vaan jatketaan olemassaolevan listan loppuun
//			
//			FileWriter fwriter = new FileWriter(filename, true);
//			fwriter.write(date.toString() + "\n");
//			fwriter.write(txt + "\n");
//			fwriter.close();
//			
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}

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
}
