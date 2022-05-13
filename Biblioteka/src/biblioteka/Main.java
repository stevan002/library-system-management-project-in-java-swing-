package biblioteka;

public class Main {
	
	protected static String KNJIGE_FAJL = "Knjige.txt";
	protected static String ZANROVI_FAJL = "Zanrovi.txt";
	protected static String ADMINISTRATORI_FAJL = "Administratori.txt";
	protected static String BIBLIOTEKARI_FAJL = "Bibliotekari.txt";
	protected static String PRIMERCI_KNJIGE_FAJL = "PrimerciKnjige.txt";
	protected static String CLANOVI_BIBLIOTEKE_FAJL = "ClanoviBiblioteke.txt";
	protected static String IZNAJMLJIVANJE_FAJL = "Iznajmljivanje.txt";
	protected static String TIPOVI_CLANARINE_FAJL = "TipClanarine.txt";
	
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
		
		System.out.println("PODACI UCITANI IZ DATOTEKA: ");
		System.out.println("------------------------------------------------");
		ispisiSvePodatke(biblioteka);
		
		System.out.println("-------------------------------------------------");
		System.out.println("DODAVANJE PODATAKA U DATOTEKE... ");
		
		ZanrKnjige testZanr = new ZanrKnjige(504, "Oznaka", "Opis");
		biblioteka.dodajZanr(testZanr);
		
		Knjiga testKnjiga = new Knjiga(1004, "NASLOV", "ORIGINALNI NASLOV", "PISAC", 1953, "Ovde se nalazi opis knjige",testZanr,Jezik.RUSKI);
		biblioteka.dodajKnjigu(testKnjiga);
		
		
		
		
		System.out.println("SNIMANJE PODATAKA U FAJL... ");
		biblioteka.snimiZanrove(ZANROVI_FAJL);
		biblioteka.snimiKnjige(KNJIGE_FAJL);
		
		

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
		
		
		
	}

}
