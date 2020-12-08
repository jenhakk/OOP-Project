package app;

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
		String alennus = "";

		int tuotenro = 0;
		double yhteissumma;

		String filename = "src/data/kuitti.txt";

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

			Asiakas.tulostaTuotelista();

			// Asiakas.naytaTuotteenKuvaus(5);


			// Tietokanta.haeTuotteet();



			do {
				

				do {
						System.out.println("Anna tuotteen numero lisĂ¤tĂ¤Ă¤ksesi tuote ostoskoriin: ");
						System.out.println("Voit lukea tuotteen tarkemmat tiedot valitsemalla (t)");
						vastaus = input.nextLine();
						vastaus2 = vastaus;
						if (vastaus.equalsIgnoreCase("t")) {
							System.out.println("Anna tuotteen tuotenumero:");
							int check = 1;
							do {
								check = 1;
								try {
									
									vastausint = Integer.parseInt(input.nextLine());
									}
									catch (Exception e) {
										check = 0;
										System.out.println("Virheellinen syote, yritĂ¤ uudelleen.");
									}
							} while (check == 0);
							Asiakas.naytaTuotteenKuvaus(vastausint);
							
						}
				} while (vastaus2.equalsIgnoreCase("t"));
				
				
						boolean loopcheck = true;
					do {
							loopcheck = true;
							
							try {
								 vastausint = Integer.parseInt(vastaus);
							} catch (Exception e){
								System.out.println("Virheellinen syĂ¶te, yritĂ¤ uudelleen.");
								loopcheck = false;
							}
						} while (loopcheck = false);
					
					String testi = Asiakas.naytaTuotteenTiedot(vastausint);
					int tuoteID = Integer.parseInt(Asiakas.palautaTuotenro(testi));
					String tuote = Asiakas.palautaNimi(testi);
					double hintaDbl = Double.parseDouble(Asiakas.palautaHinta(testi));
					//System.out.println(tuoteID + tuote + hintaDbl);
					
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
//						System.out.println("Onneksi olkoon, onnistuit rikkomaan ohjelman. Korkeasti koulutetut simpanssit ovat lĂ¤hetetty korjaamaan asia.");
//					}
//					

					Kori ostos = new Kori(tuoteID, tuote, hintaDbl);
					Asiakas.ostoskori.add(ostos);
					System.out.println("Voit lisĂ¤tĂ¤ uuden tuotteen ostoskoriin valitsemalla (k)");
					System.out.println("Voit siirtyĂ¤ ostoskoriin valitsemalla (o)");
					System.out.println("Voit lukea tuotteen tarkemmat tiedot valitsemalla (t)");
					
					do {
						vastaus = input.nextLine();
						if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("o")) && (!vastaus.equalsIgnoreCase("t"))) {
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
									if (!vastaus.equalsIgnoreCase("k") && (!vastaus.equalsIgnoreCase("e")) && (!vastaus.equalsIgnoreCase("p"))) {
										System.out.println("Virheellinen syote, yrita uudelleen.");
									}
									if (vastaus.equalsIgnoreCase("p")) {
										System.out.println("Kirjoita tuotteen nimi jonka haluat poistaa ostoskorista: ");
										vastaus = input.nextLine();
										Asiakas.poistaTuoteKorista(vastaus);
										System.out.println("Tuote " + vastaus + " poistettu ostoskorista.");
									}
								} while (!vastaus.equalsIgnoreCase("e") && !vastaus.equalsIgnoreCase("k"));
							
						}

						if (vastaus.equalsIgnoreCase("t")) {
							System.out.println("Anna tuotteen tuotenumero:");
							int check = 1;
							do {
								check = 1;
								try {
									
									vastausint = Integer.parseInt(input.nextLine());
									}
									catch (Exception e) {
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

			// Asiakas antaa poistettavan tuotteen nimen esim "Sukka" ja tuote poistuu
			// ostoskorista
			// Asiakas.poistaTuoteKorista("Sukka");

			Asiakas.naytaOstoskori();

			// laskee ostosten yhteissumman korista
			yhteissumma = Asiakas.laskeSumma();
			System.out.printf("Ostostesi yhteissumma on: %.2f €\r\n", yhteissumma);

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
			
			System.out.println("Täytä asiakastietosi");
			
			for(int i = 0; i < Asiakas.getTarvittavatAsiakasTiedotLength(); i++) {
				System.out.println(Asiakas.getTarvittavatAsiakasTiedot(i) + ":");
				
				try {
					vastaus = input.nextLine();
				} catch(Exception e) {
					System.out.println("Väärä syöte");
					i--;
				}

				Asiakas.setAsiakasTieto(i, vastaus);
			}
			
			int tilausID = Tietokanta.lisaaTilaus(Asiakas.getAsiakasTieto(0), 
					Asiakas.getAsiakasTieto(1), 
					Asiakas.getAsiakasTieto(2), 
					Asiakas.getAsiakasTieto(3), 
					Asiakas.getAsiakasTieto(4),
					yhteissumma);
			
			for(int i = 0; i < Asiakas.ostoskori.size(); i++) {
				Tietokanta.lisaaTuoteTilaukseen(tilausID, Asiakas.ostoskori.get(i).tuotenro, 1); 
			}
			
			// kuitin tulostus tekstitiedostoon, kuitin tiedostosta tulostus konsoliin

			// ja sen jĂ¤lkeen tekstitiedoston tyhjennys
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
					System.out.println("VĂ¤Ă¤rĂ¤ pin-koodi, yritĂ¤ uudelleen: ");
				}
			} while (!vastauscrypt.equals("81dc9bdb52d04dc20036dbd8313ed055"));

			System.out.println("MitĂ¤ haluaisit tehdĂ¤?");
			System.out.println("Tulosta varasto: valitse (T)");
			System.out.println("Muuttaa tuotteen hintaa: valitse (M)");
			System.out.println("LisĂ¤Ă¤ tuote varastoon: valitse (L)");
			System.out.println("Näytä tämän hetkiset tilaukset: valitse (N)");
			

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
							System.out.println("Virheellinen syĂ¶te luvuissa, yritĂ¤ uudestaan.");
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

						System.out.println("Haluatko lisĂ¤tĂ¤ uuden tuotteen varastoon?");
						vastaus = input.nextLine();

					} while (vastaus.equals("k"));

					Yllapito.tulostaVarasto();
				}
				
				if (vastaus.equalsIgnoreCase("n")) {
					
					Yllapito.naytaTilaukset();
				}

				if (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m")
						&& !vastaus.equalsIgnoreCase("l") && !vastaus.equalsIgnoreCase("n")) {

					System.out.println("Virheellinen syĂ¶te, yritĂ¤ uudelleen.");

				}

			} while (!vastaus.equalsIgnoreCase("t") && !vastaus.equalsIgnoreCase("m")
					&& !vastaus.equalsIgnoreCase("l") && !vastaus.equalsIgnoreCase("n"));

		}

	}

}