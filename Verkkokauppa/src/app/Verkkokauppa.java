package app;
import java.util.Scanner;

import data.Tietokanta;

public class Verkkokauppa {

	public static void main(String[] args) {
		Tietokanta.haeTuotteet(); // testi
		
		Scanner input = new Scanner(System.in);
		String vastaus;
		
		System.out.println("Tervetuloa Verkkokauppaan!");
		System.out.println("Oletko asiakas vai ylläpito? (a/y)");
		do {
			vastaus = input.nextLine();
			
			if (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y")) {
				System.out.println("virheellinen syöte, yritä uudelleen.");
			}
			
		} while (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y"));
		
		//Asiakas valittu
		if (vastaus.equalsIgnoreCase("a")) {
			
			Asiakas Asiakas = new Asiakas();
			
			Asiakas.naytaTuotteenTiedot();
			Asiakas.lisaaTuoteKoriin();
			Asiakas.naytaYhteissumma();
			
		}
		
		if (vastaus.equalsIgnoreCase("y")) {
			
			Yllapito Yllapito = new Yllapito();
			
			// kirjautuminen?
			
			System.out.println("Mitä haluaisit tehdä?");
			System.out.println("Tulosta varasto: valitse (T)");
			System.out.println("Muuttaa tuotteen hintaa: valitse (M)");
			System.out.println("Lisätä tuotteen: valitse (L)");
			
			do {
					vastaus = input.nextLine();
					
					if (vastaus.equalsIgnoreCase("t")) {
						Yllapito.tulostaVarasto();
					}
					
					if (vastaus.equalsIgnoreCase("m")) {
						Yllapito.muutaHintaa();
					}
					
					if (vastaus.equalsIgnoreCase("l")) {
						Yllapito.lisaaTuote();
					}
					
					if (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m") && !vastaus.equalsIgnoreCase("l")) {
						System.out.println("virheellinen syöte, yritä uudelleen.");
					}
					
				} while (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m") && !vastaus.equalsIgnoreCase("l"));

			
		}
		
	}

}
