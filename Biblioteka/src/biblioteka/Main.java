package biblioteka;

public class Main {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BibliotekaMain biblioteka = new BibliotekaMain();
		
		biblioteka.ucitajZanrove();
		biblioteka.ucitajKnjige();
		biblioteka.ucitajAdministratore();
		biblioteka.ucitajTipoveClanarine();
		biblioteka.ucitajClanove();
		biblioteka.ucitajPrimerkeKnjiga();
		
		ispisiSvePodatke(biblioteka);
		
		

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
