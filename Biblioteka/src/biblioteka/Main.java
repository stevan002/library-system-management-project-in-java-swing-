package biblioteka;

import java.time.LocalDate;

import gui.PrviProzor;
import gui.login;

public class Main {
	
	public static String KNJIGE_FAJL = "knjige.txt";
	public static String ZANROVI_FAJL = "zanrovi.txt";
	public static String ADMINISTRATORI_FAJL = "administratori.txt";
	public static String BIBLIOTEKARI_FAJL = "bibliotekari.txt";
	public static String PRIMERCI_KNJIGE_FAJL = "primerci.txt";
	public static String CLANOVI_BIBLIOTEKE_FAJL = "clanovi.txt";
	public static String IZNAJMLJIVANJA_FAJL = "iznajmljivanja.txt";
	public static String TIPOVI_CLANARINE_FAJL = "tipoviclanarine.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BibliotekaMain biblioteka = new BibliotekaMain();
		
		biblioteka.ucitajZanrove();
		biblioteka.ucitajKnjige();
		biblioteka.ucitajAdministratore();
		biblioteka.ucitajBibliotekare();
		biblioteka.ucitajTipoveClanarine();
		biblioteka.ucitajClanove();
		biblioteka.ucitajPrimerkeKnjiga();
		biblioteka.ucitajIznajmljivanje();
		
		PrviProzor prviProzor = new PrviProzor(biblioteka);
		prviProzor.setVisible(true);
		
	}

}
