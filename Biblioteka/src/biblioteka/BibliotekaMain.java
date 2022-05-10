package biblioteka;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class BibliotekaMain {

	private ArrayList<Knjiga> knjige;
	private ArrayList<PrimerakKnjige> primerci;
	private ArrayList<Administrator> administratori;
	private ArrayList<Bibliotekar> bibliotekari;
	private ArrayList<ClanBiblioteke> clanovi;
	private ArrayList<ZanrKnjige> zanrovi;
	
	public BibliotekaMain() {
		this.knjige = new ArrayList<Knjiga>();
		this.primerci = new ArrayList<PrimerakKnjige>();
		this.administratori = new ArrayList<Administrator>();
		this.bibliotekari = new ArrayList<Bibliotekar>();
		this.clanovi = new ArrayList<ClanBiblioteke>();
		this.zanrovi = new ArrayList<ZanrKnjige>();
	}

	public ArrayList<Knjiga> getKnjige() {
		return knjige;
	}

	public ArrayList<PrimerakKnjige> getPrimerci() {
		return primerci;
	}

	public ArrayList<Administrator> getAdministratori() {
		return administratori;
	}

	public ArrayList<Bibliotekar> getBibliotekari() {
		return bibliotekari;
	}

	public ArrayList<ClanBiblioteke> getClanovi() {
		return clanovi;
	}
	
	public ArrayList<ZanrKnjige> getZanrovi() {
		return zanrovi;
	}
	
	public ZanrKnjige pronadjiZanr(String id) {
		for (ZanrKnjige zanr : zanrovi) {
			if(Integer.valueOf(zanr.getId()).equals(id)) {
				return zanr;
			}
		}
		return null;
	}

	public void ucitajKnjige() {
		try {
			File file = new File("src/fajlovi/knjige");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String idString = split[0];
				int idKod = Integer.parseInt(idString);
				String naslov = split[1];
				String originalNaslov = split[2];
				String pisac = split[3];
				String godinaPublikacijeStr = split[4];
				int godinaPublikacije = Integer.parseInt(godinaPublikacijeStr);
				String opis = split[5];
				String zanr = split[6]; //OVO ZA POPRAVITI
				ArrayList<PrimerakKnjige> primerak = new ArrayList<PrimerakKnjige>();
				int jezikInt = Integer.parseInt(split[8]);
				Jezik jezik = Jezik.values()[jezikInt];
				
				Knjiga knjiga = new Knjiga(idKod, naslov, originalNaslov, pisac, godinaPublikacije, opis, zanr, primerak, jezik);
				knjige.add(knjiga);
				// 003|560.0|Test Knjiga|false|Test autor|14|false
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o knjigama");
			e.printStackTrace();
		}
	}
	
	

}
