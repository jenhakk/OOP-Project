package app;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	Kori ostos = new Kori(nimi, hinta);
}

class Asiakas extends Kayttaja {
	
	int i;
	

	protected void naytaTuotteet()
	{
		Tietokanta.haeTuotteet();
	}
	
	
	//Näyttää yksittäisen tuotteen tarkemmat tiedot, esim. kuvauksen
	protected String naytaTuotteenTiedot(int tuotenro)
	
	{	
		String testi;
		testi = Tietokanta.naytaTuotteenTiedot(tuotenro);
		return testi;
	}
	
	//Lisää tuotteen ostoskoriin
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
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
		for (int i=0; i<ostoskori.size(); i++)
			
		{
			ostoskori.get(i).tulostaTiedot();
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
	}

}

class Yllapito extends Asiakas {
	
	
	//tulostaa varaston tiedot
	public void tulostaVarasto()
	
	{

		Tietokanta.haeTuotteet();
	}
	
	//muuttaa yksittäisen tuotteen hintaa
	protected void muutaHintaa(double hinta, int tuotenro)


	{
		
	}
	
	
	//Lisää yksittäisen tuotteen varastoon


	protected void lisaaUusiTuote(String n, String k, double h)

	
	{
		Tietokanta.lisaaUusiTuote(n, k, h);
	}

	
	//md5 hash pin-koodille
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