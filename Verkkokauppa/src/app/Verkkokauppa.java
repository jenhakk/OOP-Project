package app;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.*;

public class Verkkokauppa {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String vastaus;
		String nimi;
		double hinta = 0;
		String kuvaus;
		String alennus = "";

		int tuotenro = 0;
		double yhteissumma;

		String filename = "src/data/kuitti.txt";

		System.out.println("Tervetuloa Verkkokauppaan!");
		System.out.println("Oletko asiakas vai ylläpito? (a/y)");
		do {
			vastaus = input.nextLine();

			if (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y")) {

				System.out.println("Virheellinen syöte, yritä uudelleen.");

			}

		} while (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y"));

		// Asiakas valittu
		if (vastaus.equalsIgnoreCase("a")) {

			Asiakas Asiakas = new Asiakas();

			System.out.println("Tervetuloa ostoksille!");

			Asiakas.tulostaTuotelista();

			// Asiakas.naytaTuotteenKuvaus(5);

			// Tietokanta.haeTuotteet();
			int vastausint = 0;

			do {
				
				System.out.println("Anna tuotteen numero lisätääksesi tuote ostoskoriin: ");
				boolean loopcheck = true;
				do {
					loopcheck = true;
					vastaus = input.nextLine();
					try {
						vastausint = Integer.parseInt(vastaus);
					} catch (Exception e) {
						System.out.println("Virheellinen syöte, yritä uudelleen.");
						loopcheck = false;
					}
				} while (loopcheck = false);

				String testi = Asiakas.naytaTuotteenTiedot(vastausint);
				int tuoteID = Integer.parseInt(Asiakas.palautaTuotenro(testi));
				String tuote = Asiakas.palautaNimi(testi);
				double hintaDbl = Double.parseDouble(Asiakas.palautaHinta(testi));
				// System.out.println(tuoteID + tuote + hintaDbl);

//					String testi;
//					String string1 = "";
//					String string2 = "";
//					double string2dbl = 0;
//					testi = Asiakas.naytaTuotteenTiedot(vastausint);
//					Pattern pattern = Pattern.compile(", *");
//					Matcher matcher = pattern.matcher(testi);
//					if (matcher.find()) {
//					    string1 = testi.substring(0, matcher.start());
//					    string2 = testi.substring(matcher.end());
//					}
//					try {
//						 string2dbl = Double.parseDouble(string2);
//					} catch (Exception e){
//						System.out.println("Onneksi olkoon, onnistuit rikkomaan ohjelman. Korkeasti koulutetut simpanssit ovat lähetetty korjaamaan asia.");
//					}
//					
				Kori ostos = new Kori(tuoteID, tuote, hintaDbl);
				Asiakas.ostoskori.add(ostos);
				System.out.println("Haluatko lisätä uuden tuotteen ostoskoriin? (k/e)");
				System.out.println("Voit tarkastella ostoskorisi sisältöä valitsemalla (o)");
				System.out.println("Voit poistaa tuotteen ostoskorista valitsemalla (p)");

				do {
					vastaus = input.nextLine();
					if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e"))
							&& (!vastaus.equalsIgnoreCase("o")) && (!vastaus.equalsIgnoreCase("p"))) {
						System.out.println("Virheellinen syöte, yritä uudelleen.");
					}
					if (vastaus.equalsIgnoreCase("o")) {
						System.out.println("Ostoskorisi sisältää:");
						System.out.println("");
						Asiakas.naytaOstoskori();
						System.out.println("Haluatko lisätä uuden tuotteen ostoskoriin? (k/e)");
						vastaus = input.nextLine();
						if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e"))) {
							System.out.println("Virheellinen syote, yrita uudelleen.");
						}
					}
					if (vastaus.equalsIgnoreCase("p")) {
						System.out.println("Kirjoita tuotteen nimi jonka haluat poistaa ostoskorista: ");
						vastaus = input.nextLine();
						Asiakas.poistaTuoteKorista(vastaus);
						System.out.println("Tuote " + vastaus + " poistettu ostoskorista.");
						System.out.println("Haluatko lisätä uuden tuotteen ostoskoriin? (k/e)");
						System.out.println("Voit tarkastella ostoskorisi sisältöä valitsemalla (o)");
						vastaus = input.nextLine();
						if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e"))
								&& (!vastaus.equalsIgnoreCase("o"))) {
							System.out.println("Virheellinen syote, yrita uudelleen.");
						}
						if (vastaus.equalsIgnoreCase("o")) {
							System.out.println("Ostoskorisi sisältää:");
							System.out.println("");
							Asiakas.naytaOstoskori();
							System.out.println("Haluatko lisätä uuden tuotteen ostoskoriin? (k/e)");
							vastaus = input.nextLine();
							if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e"))) {
								System.out.println("Virheellinen syote, yrita uudelleen.");
							}
						}
					}

				} while (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e")));

			} while (vastaus.equalsIgnoreCase("k"));

			System.out.println("Lopullinen ostoskorisi: ");

			// Asiakas antaa poistettavan tuotteen nimen esim "Sukka" ja tuote poistuu
			// ostoskorista
			// Asiakas.poistaTuoteKorista("Sukka");

			Asiakas.naytaOstoskori();

			// laskee ostosten yhteissumman korista
			yhteissumma = Asiakas.laskeSumma();
			System.out.printf("Ostostesi yhteissumma on: %.2f �\r\n", yhteissumma);

			System.out.println("Onko sinulla alennuskoodia? (k/e)");
			vastaus = input.nextLine();

			if (vastaus.equalsIgnoreCase("k"))
				do {
					System.out.println("Anna alennuskoodi:");
					vastaus = input.nextLine();

					if (vastaus.equalsIgnoreCase("jippii")) {

						System.out.println("Koodi oikein! Saat 10% alennuksen ostostesi yhteissummasta!");

						yhteissumma = yhteissumma * 0.9;
						alennus = "-10 %";

						System.out.printf("Ostostesi yhteissumma on nyt: %.2f �\r\n", yhteissumma);
						break;
					} else if (vastaus.equalsIgnoreCase("hurraa")) {

						System.out.println("Koodi oikein! Saat 15% alennuksen ostostesi yhteissummasta!");

						yhteissumma = yhteissumma * 0.85;
						alennus = "-15 %";

						System.out.printf("Ostostesi yhteissumma on nyt: %.2f �\r\n", yhteissumma);
						break;

					} else if (vastaus.equalsIgnoreCase("wuhuu")) {

						System.out.println("Koodi oikein! Saat 20% alennuksen ostostesi yhteissummasta!");

						yhteissumma = yhteissumma * 0.80;
						alennus = "-20 %";

						System.out.printf("Ostostesi yhteissumma on nyt: %.2f �\r\n", yhteissumma);
						break;

					} else {
						alennus = "0 %";
						System.out.println(
								"V��r� koodi! Yrit� uudelleen tai jatka sy�tt�m��n asiakastietosi kirjoittamalla 'e'");
					}

				} while (!vastaus.equalsIgnoreCase("e"));
			
			System.out.println("T�yt� asiakastietosi");
			
			for(int i = 0; i < Asiakas.getTarvittavatAsiakasTiedotLength(); i++) {
				System.out.println(Asiakas.getTarvittavatAsiakasTiedot(i) + ":");
				
				try {
					vastaus = input.nextLine();
				} catch(Exception e) {
					System.out.println("V��r� sy�te");
					i--;
				}

				Asiakas.setAsiakasTieto(i, vastaus);
			}
			
			int tilausID = Tietokanta.lisaaTilaus(Asiakas.getAsiakasTieto(0), 
					Asiakas.getAsiakasTieto(1), 
					Asiakas.getAsiakasTieto(2), 
					Asiakas.getAsiakasTieto(3), 
					Asiakas.getAsiakasTieto(4));
			
			for(int i = 0; i < Asiakas.ostoskori.size(); i++) {
				Tietokanta.lisaaTuoteTilaukseen(tilausID, Asiakas.ostoskori.get(i).tuotenro, 1); 
			}
			
			// kuitin tulostus tekstitiedostoon, kuitin tiedostosta tulostus konsoliin

			// ja sen jälkeen tekstitiedoston tyhjennys
			Asiakas.tulostaKoriTiedostoon(yhteissumma, filename, alennus);

			System.out.println(Asiakas.tulostaKuittiKonsoliin(filename));
			Asiakas.tyhjennaKuitti(filename);

			
			
			 

		}

		if (vastaus.equalsIgnoreCase("y")) {

			Yllapito Yllapito = new Yllapito();

			// kirjautuminen: kovakoodattu md5 hash
			// pin koodi on: 1234
			String vastauscrypt;
			System.out.println("Anna pin-koodi: ");

			do {
				vastaus = input.nextLine();
				vastauscrypt = Yllapito.crypt(vastaus);
				if (!vastauscrypt.equals("81dc9bdb52d04dc20036dbd8313ed055")) {
					System.out.println("Väärä pin-koodi, yritä uudelleen: ");
				}
			} while (!vastauscrypt.equals("81dc9bdb52d04dc20036dbd8313ed055"));

			System.out.println("Mitä haluaisit tehdä?");
			System.out.println("Tulosta varasto: valitse (T)");
			System.out.println("Muuttaa tuotteen hintaa: valitse (M)");
			System.out.println("Lisää tuote varastoon: valitse (L)");
			System.out.println("N�yt� t�m�n hetkiset tilaukset: valitse (N)");
			

			do {
				vastaus = input.nextLine();

				if (vastaus.equalsIgnoreCase("t")) {
					Yllapito.tulostaVarasto();
				}

				if (vastaus.equalsIgnoreCase("m")) {

					Yllapito.tulostaVarasto();
					int loopcheck = 1;
					do {

						System.out.println("Anna tuotenro:");
						loopcheck = 1;
						try {
							tuotenro = Integer.parseInt(input.nextLine());
						} catch (Exception e) {
							loopcheck = 0;
						}
						System.out.println("Anna uusi hinta esim (2.50)");
						try {
							hinta = Double.parseDouble(input.nextLine());
						} catch (Exception e) {
							loopcheck = 0;
							System.out.println("asd");
						}

						if (loopcheck == 0) {
							System.out.println("Virheellinen syöte luvuissa, yritä uudestaan.");
						}
					} while (loopcheck == 0);

					Yllapito.muutaHintaa(hinta, tuotenro);

					Yllapito.tulostaVarasto();

				}

				if (vastaus.equalsIgnoreCase("l")) {

					do {
						System.out.println("Anna tuotteen nimi:");
						nimi = input.nextLine();
						System.out.println("Kirjoita tuotteen kuvaus:");
						kuvaus = input.nextLine();
						System.out.println("Anna tuotteen hinta (esim. 1.50)");
						hinta = Double.parseDouble(input.nextLine());

						Yllapito.lisaaUusiTuote(nimi, kuvaus, hinta);

						System.out.println("Haluatko lisätä uuden tuotteen varastoon?");
						vastaus = input.nextLine();

					} while (vastaus.equals("k"));

					Yllapito.tulostaVarasto();
				}
				
				if (vastaus.equalsIgnoreCase("n")) {
					
					Yllapito.naytaTilaukset();
				}

				if (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m")
						&& !vastaus.equalsIgnoreCase("l") && !vastaus.equalsIgnoreCase("n")) {

					System.out.println("Virheellinen syöte, yritä uudelleen.");

				}

			} while (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m")
					&& !vastaus.equalsIgnoreCase("l") && !vastaus.equalsIgnoreCase("n"));

		}

	}

}