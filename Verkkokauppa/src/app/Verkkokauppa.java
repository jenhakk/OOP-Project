package app;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.*;

public class Verkkokauppa {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String vastaus;
		String vastaus2;
		int vastausint = 0;
		String nimi;
		double hinta = 0;
		String kuvaus;
		String alennus = "0 %";
		int kappalemaara;
		int tuotenro = 0;
		double yhteissumma;
		int check = 1;
//		int koritestaus;
		String kuittitxt = "src/data/kuitti.txt";
		String kuittihistoriatxt = "src/data/kuittihistoria.txt";

		System.out.println("Tervetuloa Verkkokauppaan!");
		System.out.println("Oletko asiakas vai yllĂ¤pito? (a/y)");
		do {
			vastaus = input.nextLine();

			if (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y")) {

				System.out.println("Virheellinen syĂ¶te, yritĂ¤ uudelleen.");

			}

		} while (!vastaus.equalsIgnoreCase("a") && !vastaus.equalsIgnoreCase("y"));

		// Asiakas valittu
		if (vastaus.equalsIgnoreCase("a")) {

			Asiakas Asiakas = new Asiakas();

			System.out.println("Tervetuloa ostoksille!");
			
		//Asiakkaalle tulostetaan ostettavissa olevat tuotteet
			Asiakas.tulostaTuotelista();

			do {
				//Asiakas voi tuotenumeron perusteella tarkastella tuotetta tarkemmin tai lis�t� ostoskoriin
				do {
					do {
						System.out.println("Anna tuotteen tuotenumero lisĂ¤tĂ¤Ă¤ksesi tuote ostoskoriin: ");
						System.out.println("Voit lukea tuotteen tarkemmat tiedot valitsemalla (t)");
						vastaus = input.nextLine();
						vastaus2 = vastaus;
						if (vastaus.equalsIgnoreCase("t")) {
							System.out.println("Anna tuotteen tuotenumero:");
							check = 1;
							do {
								check = 1;
								try {
	
									vastausint = Integer.parseInt(input.nextLine());
								} catch (Exception e) {
									check = 0;
									System.out.println("Virheellinen syote, yritĂ¤ uudelleen.");
								}
							} while (check == 0);
							
							//Asiakkaalle tulostetaan tuotteen tarkempi kuvaus
							Asiakas.naytaTuotteenKuvaus(vastausint);
	
						}
					} while (vastaus2.equalsIgnoreCase("t"));
	
					
						check = 1;
	
						try {
							vastausint = Integer.parseInt(vastaus);
						} catch (Exception e) {
							System.out.println("Virheellinen syĂ¶te, yritĂ¤ uudelleen.");
							
							
							check = 0;
						}
				} while (check == 0);

//					koritestaus = vastausint;
				
				//Tuotteen tiedot String-muodossa > erotellaan oikeiksi muuttujiksi
				String teksti = Asiakas.naytaTuotteenTiedot(vastausint);
				int tuoteID = Integer.parseInt(Asiakas.palautaTuotenro(teksti));
				String tuote = Asiakas.palautaNimi(teksti);
				double hintaDbl = Double.parseDouble(Asiakas.palautaHinta(teksti));
				
				//Kappalem��r�n kysely
				System.out.println("Montako kappaletta haluat?");
				do {
					vastaus = input.nextLine();
					check = 1;

					try {
						vastausint = Integer.parseInt(vastaus);
					} catch (Exception e) {
						System.out.println("Virheellinen syĂ¶te, yritĂ¤ uudelleen.");
						check = 0;
					}
				} while (check == 0);
				kappalemaara = vastausint;
				
				//Luodaan ostoskoriolio, johon tuotteet luetaan
				Kori ostos = new Kori(tuoteID, tuote, hintaDbl, kappalemaara);
				Asiakas.ostoskori.add(ostos);
//					Kori ostos2 = new Kori(tuoteID, tuote, hintaDbl, kappalemaara);
//					for (int i = 0; i < Asiakas.ostoskori.size(); i++) {
//						if (Asiakas.ostoskori.get(i).tuotenro == koritestaus){
//						Asiakas.ostoskori2.add(ostos);
//						}
//					}

//					System.out.println("AAAAAA " + vastausint);
//					for(int i = 0; i < Asiakas.ostoskori.size(); i++) {
//                        if(Asiakas.ostoskori.get(i).tuotenro == vastausint) {
//                            System.out.println("Tuote on jo ostoskorissa");
//                        }
//                        else {
//                        	Kori ostos = new Kori(tuoteID, tuote, hintaDbl, kappalemaara);
//        					Asiakas.ostoskori.add(ostos);
//                        }
//                    }

				System.out.println("Voit lisĂ¤tĂ¤ uuden tuotteen ostoskoriin valitsemalla (k)");
				System.out.println("Voit siirtyĂ¤ ostoskoriin valitsemalla (o)");
				System.out.println("Voit lukea tuotteen tarkemmat tiedot valitsemalla (t)");

				do {
					vastaus = input.nextLine();
					
					//Asiakas voi tarkastella ostoskorin sis�lt�� valitsemalla "o", kori tulostetaan
					if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("o"))
							&& (!vastaus.equalsIgnoreCase("t"))) {
						System.out.println("Virheellinen syĂ¶te, yritĂ¤ uudelleen.");

					}
					if (vastaus.equalsIgnoreCase("o")) {
						do {
							System.out.println("Ostoskorisi sisĂ¤ltĂ¤Ă¤:");
							System.out.println("");
							Asiakas.naytaOstoskori();
							System.out.println("Jos haluat lisĂ¤tĂ¤ uuden tuotteen ostoskoriin, valitse (k)");
							System.out.println("Voit poistaa tuotteen ostoskorista valitsemalla (p)");
							System.out.println("Jos haluat hyvĂ¤ksyĂ¤ tilauksen, valitse (e)");

							vastaus = input.nextLine();
							if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e"))
									&& (!vastaus.equalsIgnoreCase("p"))) {
								System.out.println("Virheellinen syote, yrita uudelleen.");
							}
							
							//Asiakas voi poistaa korista tuotteen kirjoittamalla poistettavan tuotteen nimen
							if (vastaus.equalsIgnoreCase("p")) {
								System.out.println("Kirjoita tuotteen nimi jonka haluat poistaa ostoskorista: ");
								vastaus = input.nextLine();
								Asiakas.poistaTuoteKorista(vastaus);
								System.out.println("Tuote " + vastaus + " poistettu ostoskorista.");
							}
						} while (!vastaus.equalsIgnoreCase("e") && !vastaus.equalsIgnoreCase("k"));

					}
						//Jos asiakas kirjoittaa "t", h�n voi tarkastella tietty� tuotetta tarkemmin
					if (vastaus.equalsIgnoreCase("t")) {
						System.out.println("Anna tuotteen tuotenumero:");

						do {
							check = 1;
							try {

								vastausint = Integer.parseInt(input.nextLine());
							} catch (Exception e) {
								check = 0;
								System.out.println("Virheellinen syote, yrita uudelleen.");
							}
						} while (check == 0);
						Asiakas.naytaTuotteenKuvaus(vastausint);
						System.out.println("Voit lisĂ¤tĂ¤ uuden tuotteen ostoskoriin valitsemalla (k)");
						System.out.println("Voit siirtyĂ¤ ostoskoriin valitsemalla (o)");
						System.out.println("Voit lukea tuotteen tarkemmat tiedot valitsemalla (t)");

					}

				} while (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e")));

			} while (vastaus.equalsIgnoreCase("k"));

			System.out.println("Lopullinen ostoskorisi: ");

//			for(int i = 0; i < Asiakas.ostoskori.size(); i++) {
//                if (Asiakas.ostoskori.get(i).tuotenro == Asiakas.ostoskori2.get(i).tuotenro) {
//                	int maara = Asiakas.ostoskori.get(i).kappalemaara + Asiakas.ostoskori2.get(i).kappalemaara;
//                	System.out.println("AAAAAAAAAAAAA    " + (maara));
//                }
//            }
			
			//K�yd��n ostoskori l�pi, mik�li samaa tuotetta on lis�tty moneen kertaan, duplikaatit poistetaan
			//ja kpl-m��r�t lasketaan yhteen
			for (int i = 0; i < Asiakas.ostoskori.size(); i++) {
				for (int j = i + 1; j < Asiakas.ostoskori.size(); j++) {
					if (Asiakas.ostoskori.get(i).tuotenro == Asiakas.ostoskori.get(j).tuotenro) {
						int summa = Asiakas.ostoskori.get(i).kappalemaara + Asiakas.ostoskori.get(j).kappalemaara;
						Asiakas.ostoskori.get(i).kappalemaara = summa;
						Asiakas.ostoskori.remove(j);
						j--;
					}
				}
			}
			
			//Tulostetaan lopullinen ostoskori
			Asiakas.naytaOstoskori();

			// laskee ostosten yhteissumman korista
			yhteissumma = Asiakas.laskeSumma();
			System.out.printf("Ostostesi yhteissumma on: %.2f €\r\n", yhteissumma);

			System.out.println("Onko sinulla alennuskoodia? (k/e)");
			
			do {
				vastaus = input.nextLine();
				if (!vastaus.equalsIgnoreCase("k") && !vastaus.equalsIgnoreCase("e")) {
					System.out.println("Virheellinen syote, yrita uudelleen.");
				}
				} while (!vastaus.equalsIgnoreCase("k") && !vastaus.equalsIgnoreCase("e"));
			

			// Alennuskoodin kysely
			if (vastaus.equalsIgnoreCase("k"))
				do {

					System.out.println("Anna alennuskoodi:");
					vastaus = input.nextLine();

					if (vastaus.equalsIgnoreCase("jippii")) {

						System.out.println("Koodi oikein! Saat 10% alennuksen ostostesi yhteissummasta!");

						yhteissumma = yhteissumma * 0.9;
						alennus = "-10 %";

						System.out.printf("Ostostesi yhteissumma on nyt: %.2f €\r\n", yhteissumma);
						break;
					} else if (vastaus.equalsIgnoreCase("hurraa")) {

						System.out.println("Koodi oikein! Saat 15% alennuksen ostostesi yhteissummasta!");

						yhteissumma = yhteissumma * 0.85;
						alennus = "-15 %";

						System.out.printf("Ostostesi yhteissumma on nyt: %.2f €\r\n", yhteissumma);
						break;

					} else if (vastaus.equalsIgnoreCase("wuhuu")) {

						System.out.println("Koodi oikein! Saat 20% alennuksen ostostesi yhteissummasta!");

						yhteissumma = yhteissumma * 0.80;
						alennus = "-20 %";

						System.out.printf("Ostostesi yhteissumma on nyt: %.2f €\r\n", yhteissumma);
						break;

					} else {
						alennus = "0 %";
						System.out.println(
								"Väärä koodi! Yritä uudelleen tai jatka syöttämään asiakastietosi valitsemalla (e)");
					}

				} while (!vastaus.equalsIgnoreCase("e"));

			// Asiakastietojen kysely
			System.out.println("Täytä asiakastietosi");

			for (int i = 0; i < Asiakas.getTarvittavatAsiakasTiedotLength(); i++) {
				System.out.println(Asiakas.getTarvittavatAsiakasTiedot(i) + ":");

				try {
					vastaus = input.nextLine();
				} catch (Exception e) {
					System.out.println("Väärä syöte");
					i--;
				}
				
				// Jos ollaan maksutavan kohdalla
				if(i == 5) {
					if(vastaus.equals("Visa") || vastaus.equals("Klarna") || vastaus.equals("Pankki")) {
						Asiakas.setAsiakasTieto(i, vastaus);
					} else {
						i--;
					}
				} else {
					Asiakas.setAsiakasTieto(i, vastaus);
				}
			}

			//Asiakastiedot lis�t��n tietokantaan (tilauksen_tuote)
			int tilausID = Tietokanta.lisaaTilaus(Asiakas.getAsiakasTieto(0), Asiakas.getAsiakasTieto(1),
					Asiakas.getAsiakasTieto(2), Asiakas.getAsiakasTieto(3), Asiakas.getAsiakasTieto(4), yhteissumma, Asiakas.getAsiakasTieto(5));

			for (int i = 0; i < Asiakas.ostoskori.size(); i++) {
				Tietokanta.lisaaTuoteTilaukseen(tilausID, Asiakas.ostoskori.get(i).tuotenro,
						Asiakas.ostoskori.get(i).kappalemaara);
			}

			// kuitin tulostus tekstitiedostoon, kuitin tiedostosta tulostus konsoliin
			// ja sen jĂ¤lkeen tekstitiedoston tyhjennys
			Asiakas.tulostaKoriTiedostoon(yhteissumma, kuittitxt, alennus);
			Asiakas.tulostaKoriTiedostoon(yhteissumma, kuittihistoriatxt, alennus);

			System.out.println(Asiakas.tulostaKuittiKonsoliin(kuittitxt));
			Asiakas.tyhjennaKuitti(kuittitxt);
			
			System.out.println("Kiitos k�ynnist�!");

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
					System.out.println("VĂ¤Ă¤rĂ¤ pin-koodi, yritĂ¤ uudelleen: ");
				}
			} while (!vastauscrypt.equals("81dc9bdb52d04dc20036dbd8313ed055"));

			System.out.println("MitĂ¤ haluaisit tehdĂ¤?");

			do {
				System.out.println("Tulosta varasto: valitse (T)");
				System.out.println("Muuttaa tuotteen hintaa: valitse (M)");
				System.out.println("LisĂ¤Ă¤ tuote varastoon: valitse (L)");
				System.out.println("Näytä tämän hetkiset tilaukset: valitse (N)");
				System.out.println("Poistu (P)");

				vastaus = input.nextLine();

				// Yll�pito voi tulostaa tuotevaraston
				if (vastaus.equalsIgnoreCase("t")) {
					Yllapito.tulostaVarasto();

					// Yll�pito voi muuttaa tietyn tuotteen hintaa
				} else if (vastaus.equalsIgnoreCase("m")) {

					Yllapito.tulostaVarasto();

					do {
						System.out.println("Anna tuotenro:");
						try {
							tuotenro = Integer.parseInt(input.nextLine());
							System.out.println("Anna uusi hinta esim (2.50)");
							hinta = Double.parseDouble(input.nextLine());
							break;
						} catch (Exception e) {
							System.out.println("Virheellinen sy�te, yrit� uudelleen.");
						}
					} while (true);

					Yllapito.muutaHintaa(hinta, tuotenro);

					Yllapito.tulostaVarasto();

					// Yll�pito voi lis�t� uuden tuotteen varastoon
				} else if (vastaus.equalsIgnoreCase("l")) {

					do {
						System.out.println("Anna tuotteen nimi:");
						nimi = input.nextLine();
						System.out.println("Kirjoita tuotteen kuvaus:");
						kuvaus = input.nextLine();
						System.out.println("Anna tuotteen hinta (esim. 1.50)");
						hinta = Double.parseDouble(input.nextLine());

						Yllapito.lisaaUusiTuote(nimi, kuvaus, hinta);

						System.out.println("Haluatko lisĂ¤tĂ¤ uuden tuotteen varastoon? (k/e)");
						vastaus = input.nextLine();

					} while (vastaus.equals("k"));

					Yllapito.tulostaVarasto();
				} else if (vastaus.equalsIgnoreCase("n")) {

					// Yll�pito voi tarkastella tehtyj� asiakastilauksia
					Yllapito.naytaTilaukset();
				} else {
					if (!vastaus.equalsIgnoreCase("p")) {
						System.out.println("Virheellinen sy�te, yrit� uudelleen.");
					}
				}

			} while (!vastaus.equalsIgnoreCase("p"));
			System.out.println("");
			System.out.println("Uloskirjautuminen onnistui. N�hd��n seuraavalla kerralla!");

		}

	}

}