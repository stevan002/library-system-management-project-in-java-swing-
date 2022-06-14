package biblioteka;

import java.time.LocalDate;

import gui.login;

public class Main {
	
	protected static String KNJIGE_FAJL = "knjige.txt";
	protected static String ZANROVI_FAJL = "zanrovi.txt";
	protected static String ADMINISTRATORI_FAJL = "administratori.txt";
	protected static String BIBLIOTEKARI_FAJL = "bibliotekari.txt";
	protected static String PRIMERCI_KNJIGE_FAJL = "primerci.txt";
	protected static String CLANOVI_BIBLIOTEKE_FAJL = "clanovi.txt";
	protected static String IZNAJMLJIVANJA_FAJL = "iznajmljivanja.txt";
	protected static String TIPOVI_CLANARINE_FAJL = "tipoviclanarine.txt";
	
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
		
		login login = new login(biblioteka);
		login.setVisible(true);
		
	}

}
