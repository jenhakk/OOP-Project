package app;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Tietokanta;

public class Verkkokauppa {

	public static void main(String[] args) {


		
		Scanner input = new Scanner(System.in);
		String vastaus;
		
		System.out.println("Tervetuloa Verkkokauppaan!");
		System.out.println("Oletko asiakas vai yllÃ¤pito? (a/y)");
		do {
			vastaus = input.nextLine();
			
			if (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y")) {

				System.out.println("Virheellinen syÃ¶te, yritÃ¤ uudelleen.");

			}
			
		} while (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y"));
		
		//Asiakas valittu
		if (vastaus.equalsIgnoreCase("a")) {
			
			Asiakas Asiakas = new Asiakas();

			System.out.println("Tervetuloa ostoksille!");
			Tietokanta.haeTuotteet();
			int vastausint = 0;
			do {
				
					System.out.println("Anna tuotteen ID lisätääksesi tuote ostoskoriin: ");
						boolean loopcheck = true;
					do {
							loopcheck = true;
							vastaus = input.nextLine();
							try {
								 vastausint = Integer.parseInt(vastaus);
							} catch (Exception e){
								System.out.println("Virheellinen syöte, yritä uudelleen.");
								loopcheck = false;
							}
						} while (loopcheck = false);
					
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
						System.out.println("Onneksi olkoon, onnistuit rikkomaan ohjelman. Korkeasti koulutetut simpanssit ovat lähetetty korjaamaan asia.");
					}
					
					Kori ostos = new Kori(string1, string2dbl);
					Asiakas.ostoskori.add(ostos);
					System.out.println("Haluatko lisätä uuden tuotteen ostoskoriin? (k/e)");
					System.out.println("Voit tarkastella ostoskorisi sisältöä valitsemalla (o)");
					do {
						vastaus = input.nextLine();
						if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e")) && (!vastaus.equalsIgnoreCase("o"))) {
							System.out.println("Virheellinen syöte, yritä uudelleen.");
						}
						if (vastaus.equalsIgnoreCase("o")) {
							System.out.println("Ostoskorisi sisältö:");
							System.out.println("");
							Asiakas.naytaOstoskori();
							System.out.println("Haluatko lisätä uuden tuotteen ostoskoriin? (k/e)");
							vastaus = input.nextLine();
							if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e"))) {
								System.out.println("Virheellinen syöte, yritä uudelleen.");
							}
						}
					} while (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e")));
					
				} while (vastaus.equalsIgnoreCase("k"));
			
			System.out.println("Lopullinen ostoskorisi: ");
			Asiakas.naytaOstoskori();
			


			
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
						System.out.println("VÃ¤Ã¤rÃ¤ pin-koodi, yritÃ¤ uudelleen: ");
					}
				} while (!vastauscrypt.equals("81dc9bdb52d04dc20036dbd8313ed055"));
			

			
			System.out.println("MitÃ¤ haluaisit tehdÃ¤?");
			System.out.println("Tulosta varasto: valitse (T)");
			System.out.println("Muuttaa tuotteen hintaa: valitse (M)");
			System.out.println("Lisää tuote varastoon: valitse (L)");
			
			do {
					vastaus = input.nextLine();
					
					if (vastaus.equalsIgnoreCase("t")) {
						Yllapito.tulostaVarasto();
					}
					
					if (vastaus.equalsIgnoreCase("m")) {

						
						double hinta = 15.00;
						int tuotenro = 1;
						Yllapito.muutaHintaa(hinta, tuotenro);

					}
					
					if (vastaus.equalsIgnoreCase("l")) {
						
						Tietokanta.lisaaUusiTuote("Pallo", "pyöreä", 250);
					}
					
					if (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m") && !vastaus.equalsIgnoreCase("l")) {

						System.out.println("Virheellinen syÃ¶te, yritÃ¤ uudelleen.");

					}
					
				} while (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m") && !vastaus.equalsIgnoreCase("l"));

			
		}
		
	}

}