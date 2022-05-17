package biblioteka;

import java.time.LocalDate;

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
		
		System.out.println("PODACI UCITANI IZ DATOTEKA: ");
		System.out.println("------------------------------------------------");
		ispisiSvePodatke(biblioteka);
		
		System.out.println("-------------------------------------------------");
		System.out.println("DODAVANJE PODATAKA U DATOTEKE... ");
		
		ZanrKnjige testZanr = new ZanrKnjige(504, "Oznaka", "Opis");
		biblioteka.dodajZanr(testZanr);
		
		Knjiga testKnjiga = new Knjiga(1004, "NASLOV", "ORIGINALNI NASLOV", "PISAC", 1953, "Ovde se nalazi opis knjige",testZanr,Jezik.RUSKI);
		biblioteka.dodajKnjigu(testKnjiga);
		
		Administrator testAdministrator = new Administrator(203, "Marija", "Ivanovic", "1234467891555", "Test Test 21", Pol.ZENSKI, "MarijaIvanovic", "marija12331", 72000.00);
		biblioteka.dodajAdministratora(testAdministrator);
		
		Bibliotekar testBibliotekar = new Bibliotekar(999, "Tanja", "Tankosic", "1234567894333", "Test Test 35", Pol.ZENSKI, "TanjaTankosic", "tanja123", 62000.00);
		biblioteka.dodajBibliotekara(testBibliotekar);
		
		Bibliotekar testBibliotekar1 = new Bibliotekar(355, "Mara", "Klincov", "1234567894333", "Test Test 22", Pol.ZENSKI, "mara", "klincov123", 45000.00);
		biblioteka.dodajBibliotekara(testBibliotekar1);
		
		TipClanarine testTipClanarine = new TipClanarine(904,"CLANARINA", 300);
		biblioteka.dodajTipClanarine(testTipClanarine);
		
		ClanBiblioteke testClanBiblioteke = new ClanBiblioteke(304, "Jovan", "Ivanovic", "2211057891234", "Test Test 49A", Pol.MUSKI, "17d", LocalDate.of(2022, 03, 15), 1, true, testTipClanarine);
		biblioteka.dodajClanaBiblioteke(testClanBiblioteke);
		
		PrimerakKnjige testPrimerakKnjige = new PrimerakKnjige(122, 315, 2003, true, testKnjiga, TipPoveza.MEK, Jezik.ENGLESKI);
		biblioteka.dodajPrimerakKnjige(testPrimerakKnjige);
		
		Iznajmljivanje testIznajmljivanje = new Iznajmljivanje(995, LocalDate.of(2022, 04, 12), LocalDate.of(2022, 05, 12), testBibliotekar, testClanBiblioteke);		
		testIznajmljivanje.getPrimerci().add(testPrimerakKnjige);		
		biblioteka.dodajIznajmljivanje(testIznajmljivanje);
		
		
		
		System.out.println("SNIMANJE PODATAKA U FAJL... ");
		biblioteka.snimiZanrove(ZANROVI_FAJL);
		biblioteka.snimiKnjige(KNJIGE_FAJL);
		biblioteka.snimiPrimerkeKnjige(PRIMERCI_KNJIGE_FAJL);
		biblioteka.snimiAdministratore(ADMINISTRATORI_FAJL);
		biblioteka.snimiBibliotekare(BIBLIOTEKARI_FAJL);
		biblioteka.snimiTipClanarine(TIPOVI_CLANARINE_FAJL);
		biblioteka.snimiClanoveBiblioteke(CLANOVI_BIBLIOTEKE_FAJL);	
		biblioteka.snimiIznajmljivanja(IZNAJMLJIVANJA_FAJL);
		
		
		

	}
	
	public static void ispisiSvePodatke(BibliotekaMain biblioteka) {
		for(ZanrKnjige zanr: biblioteka.getZanrovi().values()) {
			System.out.println(zanr);
		}
		System.out.println();
		
		for(Knjiga knjiga: biblioteka.getKnjige().values()) {
			System.out.println(knjiga);
		}
		System.out.println();
		
		for(Administrator administrator: biblioteka.getAdministratori().values()) {
			System.out.println(administrator);
		}
		System.out.println();
		
		for(Bibliotekar bibliotekar: biblioteka.getBibliotekari().values()) {
			System.out.println(bibliotekar);
		}
		System.out.println();
		
		for(TipClanarine tip: biblioteka.getTipoviClanarine().values()) {
			System.out.println(tip);
		}
		System.out.println();
		
		for(ClanBiblioteke clan: biblioteka.getClanovi().values()) {
			System.out.println(clan);
		}
		System.out.println();
		
		for(PrimerakKnjige primerak: biblioteka.getPrimerci().values()) {
			System.out.println(primerak);
		}
		System.out.println();
		
		for(Iznajmljivanje iznajmljivanje: biblioteka.getIznajmljeneKnjige().values()) {
			System.out.println(iznajmljivanje);
		}
		System.out.println();
		
		
		
	}

}
