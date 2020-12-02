package app;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Tietokanta;

public class Verkkokauppa {

	public static void main(String[] args) {


		
		Scanner input = new Scanner(System.in);
		String vastaus;
		String nimi;
		double hinta;
		String kuvaus;
		int tuotenro;
		
		System.out.println("Tervetuloa Verkkokauppaan!");
		System.out.println("Oletko asiakas vai yll√§pito? (a/y)");
		do {
			vastaus = input.nextLine();
			
			if (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y")) {

				System.out.println("Virheellinen sy√∂te, yrit√§ uudelleen.");

			}
			
		} while (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y"));
		
		//Asiakas valittu
		if (vastaus.equalsIgnoreCase("a")) {
			
			Asiakas Asiakas = new Asiakas();

			System.out.println("Tervetuloa ostoksille!");
			Asiakas.tulostaTuotelista();
			//Asiakas.naytaTuotteenKuvaus(5);
			do {
				
					System.out.println("Anna tuotteen ID lisÔøΩtÔøΩksesi tuote ostoskoriin: ");
					vastaus = input.nextLine();
					int vastausint = 0;
					try {
						 vastausint = Integer.parseInt(vastaus);
					} catch (Exception e){
						
					}
					
					String testi;
					String string1 = "";
					String string2 = "";
					double string2dbl = 0;
					testi = Asiakas.naytaTuotteenTiedot(vastausint);
					Pattern pattern = Pattern.compile(", *");
					Matcher matcher = pattern.matcher(testi);
					if (matcher.find()) {
					    string1 = testi.substring(0, matcher.start());
					    string2 = testi.substring(matcher.end());
					}
					try {
						 string2dbl = Double.parseDouble(string2);
					} catch (Exception e){
						
					}
					
					Kori ostos = new Kori(string1, string2dbl);
					Asiakas.ostoskori.add(ostos);
					System.out.println("Haluatko lis‰t‰ uuden tuotteen ostoskoriin? (k/e)");
					vastaus = input.nextLine();
				} while (vastaus.equalsIgnoreCase("k"));
			
			
			Asiakas.naytaOstoskori();
			
//			Asiakas.lisaaTuoteKoriin();
//			Asiakas.naytaYhteissumma();

			
		}
		
		if (vastaus.equalsIgnoreCase("y")) {
			
			Yllapito Yllapito = new Yllapito();
			
			

			// kirjautuminen: kovakoodattu md5 hash
			//pin koodi on: 1234
			String vastauscrypt;
			System.out.println("Anna pin-koodi: ");
			
			do {
					vastaus = input.nextLine();
					vastauscrypt = Yllapito.crypt(vastaus);
					if (!vastauscrypt.equals("81dc9bdb52d04dc20036dbd8313ed055")) {
						System.out.println("V√§√§r√§ pin-koodi, yrit√§ uudelleen: ");
					}
				} while (!vastauscrypt.equals("81dc9bdb52d04dc20036dbd8313ed055"));
			

			
			System.out.println("Mit√§ haluaisit tehd√§?");
			System.out.println("Tulosta varasto: valitse (T)");
			System.out.println("Muuttaa tuotteen hintaa: valitse (M)");
			System.out.println("Lis‰‰ tuote varastoon: valitse (L)");
			
			do {
					vastaus = input.nextLine();
					
					if (vastaus.equalsIgnoreCase("t")) {
						Yllapito.tulostaVarasto();
					}
					
					if (vastaus.equalsIgnoreCase("m")) {
						
						Yllapito.tulostaVarasto();
						System.out.println("Anna tuotenro:");
						tuotenro = Integer.parseInt(input.nextLine());
						System.out.println("Anna uusi hinta esim (2.50)");
						hinta = Double.parseDouble(input.nextLine());
						
						//ei toimi viel‰ t‰m‰ 
						Yllapito.muutaHintaa(hinta, tuotenro);
						
						Yllapito.tulostaVarasto();

					}
					
					if (vastaus.equalsIgnoreCase("l")) {
						System.out.println("Anna tuotteen nimi:");
						nimi = input.nextLine();
						System.out.println("Kirjoita tuotteen kuvaus:");
						kuvaus = input.nextLine();
						System.out.println("Anna tuotteen hinta (esim. 1.50)");
						hinta = Double.parseDouble(input.nextLine());
						
						Yllapito.lisaaUusiTuote(nimi, kuvaus, hinta);
						Yllapito.tulostaVarasto();
					}
					
					if (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m") && !vastaus.equalsIgnoreCase("l")) {

						System.out.println("Virheellinen sy√∂te, yrit√§ uudelleen.");

					}
					
				} while (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m") && !vastaus.equalsIgnoreCase("l"));

			
		}
		
	}

}