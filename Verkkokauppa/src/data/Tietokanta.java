package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tietokanta {
	
	private static Connection connection = null; 
	private static Statement statement = null;  
	private static ResultSet resultSet = null;
	
	// Yhteyden tiedot
	private static String db = "trtkp20a3";
	private static String url = "jdbc:mysql://shell.hamk.fi/" + db + "?useSSL=false";
	private static String username = "trtkp20a3";
	private static String password = "trtkp20a3passwd";
	
	// Yhteys tietokantaan
	private static void yhdistaTietokanta() {
		try {
			connection = DriverManager.getConnection(url, username, password);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void haeTuotteet() {
		
		// Ota yhteys tietokantaan
		yhdistaTietokanta();
		

		// Tietokannan taulun kentät tulostusta varten

		int id;
		String nimi;
		String kuvaus;
		double hinta;
		
		try {
			
			// Luo MySQL-kysely
			statement = connection.createStatement();

			String querySelect = "SELECT * FROM ryhma1_tuotteet";
			
			// Suorita kysely
			resultSet = statement.executeQuery(querySelect);
			

			// Vastauksen käsittely
			System.out.println("tuoteID\t\tTuote\t\tHinta �\t\tKuvaus");


			while (resultSet.next()) {
				id = resultSet.getInt("tuoteID");
				nimi = resultSet.getString("nimi");
				kuvaus = resultSet.getString("kuvaus");
				hinta = resultSet.getDouble("hinta");


				System.out.println(id + "\t\t" + nimi + "\t\t" + hinta + "\t\t" + kuvaus);

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Sulje yhteys ja nollaa kyselyt
			if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
			if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
			if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}



public static void naytaTuotteenKuvaus(int tuotenro) {
	
	// Ota yhteys tietokantaan
	yhdistaTietokanta();
	
	// Tietokannan taulun kentät tulostusta varten
	int id;
	String nimi;
	String kuvaus;
	double hinta;
	
	try {
		
		// Luo MySQL-kysely
		statement = connection.createStatement();

		String querySelect = "SELECT tuoteID, nimi, kuvaus FROM ryhma1_tuotteet where tuoteID =" + tuotenro ;
		
		// Suorita kysely
		resultSet = statement.executeQuery(querySelect);
		
		// Vastauksen käsittely
		System.out.println("TuoteID\tTuote\tKuvaus");

		while (resultSet.next()) {
			id = resultSet.getInt("tuoteID");
			nimi = resultSet.getString("nimi");
			kuvaus = resultSet.getString("kuvaus");
			//hinta = resultSet.getDouble("hinta");
			System.out.println(nimi + "\t" + kuvaus);
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		// Sulje yhteys ja nollaa kyselyt
		if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
		if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	}
}
public static void muutaHintaa(double h, int tuotenro) {
	
	// Ota yhteys tietokantaan
	yhdistaTietokanta();
	
	// Tietokannan taulun kentät tulostusta varten
	int id = tuotenro;
	String nimi;
	String kuvaus;
	double hinta = h;
	
	try {
		
		// Luo MySQL-kysely
		statement = connection.createStatement();
		//UPDATE `ryhmä1_tuotteet` SET `hinta` = '12' WHERE `ryhma1_tuotteet`.`tuoteID` = 1;
		String queryInsert = "UPDATE ryhm�1_tuotteet SET hinta = " + h +" where tuoteID = 1";
		System.out.println(queryInsert);
		// Suorita kysely
		statement.executeUpdate(queryInsert);
		
		// Vastauksen käsittely
		System.out.println("tuoteID\tTuote\tHinta �\tKuvaus");

		while (resultSet.next()) {
			id = resultSet.getInt("tuoteID");
			nimi = resultSet.getString("nimi");
			kuvaus = resultSet.getString("kuvaus");
			hinta = resultSet.getDouble("hinta");
			System.out.println(id + "\t" + nimi + "\t" + hinta + "\t" + kuvaus);
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		// Sulje yhteys ja nollaa kyselyt
		if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
		if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	}
}

public static void lisaaUusiTuote(String n, String k, double h) {
	
	// Ota yhteys tietokantaan
	yhdistaTietokanta();
	
	// Tietokannan taulun kentät tulostusta varten
	//int id = tuotenro;
	String nimi = n;
	String kuvaus = k;
	double hinta = h;
	
	try {
		
		// Luo MySQL-kysely
		statement = connection.createStatement();
		//UPDATE `ryhmä1_tuotteet` SET `hinta` = '12' WHERE `ryhma1_tuotteet`.`tuoteID` = 1;
		String queryInsert = "INSERT INTO ryhma1_tuotteet (nimi, kuvaus, hinta) VALUES ('"+nimi+"','"+kuvaus+"',"+hinta+")";
		System.out.println(queryInsert);
		// Suorita kysely
		statement.executeUpdate(queryInsert);
		
		// Vastauksen käsittely
		System.out.println("tuoteID\tTuote\tHinta �\tKuvaus");

		while (resultSet.next()) {
			//id = resultSet.getInt("tuoteID");
			nimi = resultSet.getString("nimi");
			kuvaus = resultSet.getString("kuvaus");
			hinta = resultSet.getDouble("hinta");
			System.out.println("\t" + nimi + "\t" + hinta + "\t" + kuvaus);
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		// Sulje yhteys ja nollaa kyselyt
		if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
		if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	}
}
}


