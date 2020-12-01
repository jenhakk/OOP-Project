package app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	protected void tulostaVarasto()
	
	{
		for (int i=0; i<tuotteet.size(); i++)
			
		{
			System.out.println(tuotteet.get(i));
		}
	}
	
	//muuttaa yksitt‰isen tuotteen hintaa
	protected void muutaHintaa()

	{
		
	}
	
	
	//Lis‰‰ yksitt‰isen tuotteen varastoon
	protected void lisaaTuote()
	
	{
		tuotteet.add(tuote);
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