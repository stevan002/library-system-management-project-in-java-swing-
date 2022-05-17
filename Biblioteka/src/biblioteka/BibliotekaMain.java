package biblioteka;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class BibliotekaMain {
	
	private static String folder = "src/fajlovi/";
	
	private HashMap<Integer, Knjiga> knjige;
	private HashMap<Integer, PrimerakKnjige> primerci;
	private HashMap<Integer, Administrator> administratori;
	private HashMap<Integer, Bibliotekar> bibliotekari;
	private HashMap<Integer, ClanBiblioteke> clanovi;
	private HashMap<Integer, ZanrKnjige> zanrovi;
	private HashMap<Integer, Iznajmljivanje> iznajmljeneKnjige;
	private HashMap<Integer, TipClanarine> tipoviClanarine;
	
	public BibliotekaMain() {
		this.zanrovi = new HashMap<Integer, ZanrKnjige>();
		this.knjige = new HashMap<Integer, Knjiga>();
		this.primerci = new HashMap<Integer, PrimerakKnjige>();
		this.administratori = new HashMap<Integer, Administrator>();
		this.bibliotekari = new HashMap<Integer, Bibliotekar>();
		this.clanovi = new HashMap<Integer, ClanBiblioteke>();
		this.iznajmljeneKnjige = new HashMap<Integer, Iznajmljivanje>();
		this.tipoviClanarine = new HashMap<Integer, TipClanarine>();
	}

	public HashMap<Integer, Knjiga> getKnjige() {
		return knjige;
	}


	public HashMap<Integer, PrimerakKnjige> getPrimerci() {
		return primerci;
	}


	public HashMap<Integer, Administrator> getAdministratori() {
		return administratori;
	}


	public HashMap<Integer, Bibliotekar> getBibliotekari() {
		return bibliotekari;
	}


	public HashMap<Integer, ClanBiblioteke> getClanovi() {
		return clanovi;
	}


	public HashMap<Integer, ZanrKnjige> getZanrovi() {
		return zanrovi;
	}


	public HashMap<Integer, Iznajmljivanje> getIznajmljeneKnjige() {
		return iznajmljeneKnjige;
	}


	public HashMap<Integer, TipClanarine> getTipoviClanarine() {
		return tipoviClanarine;
	}


	public void ucitajKnjige() {
		try {
			File file = new File("src/fajlovi/knjige.txt");
			BufferedReader reader = new BufferedReader (new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				int idKod = Integer.parseInt(split[0]);
				String naslov = split[1];
				String originalNaslov = split[2];
				String pisac = split[3];
				String godinaPublikacijeStr = split[4];
				int godinaPublikacije = Integer.parseInt(godinaPublikacijeStr);
				String opis = split[5];
				int zanrID = Integer.parseInt(split[6]);
				ZanrKnjige zanr = this.zanrovi.get(zanrID);
				Jezik jezik = Jezik.valueOf(split[7]);
				
				
				Knjiga knjiga = new Knjiga(idKod, naslov, originalNaslov, pisac, godinaPublikacije, opis, zanr, jezik);
				
				this.knjige.put(knjiga.getId(), knjiga);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o knjigama");
			e.printStackTrace();
		}
	}
	
	public void snimiKnjige(String imeFajla) {
		String sadrzaj = "";
		for (Knjiga knjiga: this.knjige.values()) {
			sadrzaj += knjiga.getId() + "|" + knjiga.getNaslovKnjige() + "|" + knjiga.getOriginalniNaslovKnjige() + "|"
					+ knjiga.getPisac() + "|" + knjiga.getGodinaPublikacije() + "|" + knjiga.getOpis() + "|" + knjiga.getZanr().getId() + "|"
					+ knjiga.getJezikOriginala() + "\n";
		}
		try {
			File file = new File(folder + imeFajla);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja knjiga.");
		}
	}
	
	public void ucitajZanrove() {
		try {
			File file = new File("src/fajlovi/zanrovi.txt");
			BufferedReader reader = new BufferedReader (new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				int id = Integer.parseInt(split[0]);
				String oznaka = split[1];
				String opis = split[2];
				
				ZanrKnjige zanr = new ZanrKnjige(id, oznaka, opis);
				
				this.zanrovi.put(zanr.getId(), zanr);
				
				}
			
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o knjigama");
			e.printStackTrace();
		}
	}
	
	public void snimiZanrove(String imeFajla) {
		String sadrzaj = "";
		for (ZanrKnjige zanr: this.zanrovi.values()) {
			sadrzaj += zanr.getId() + "|" + zanr.getOznaka() + "|" + zanr.getOpis() + "\n";
		}
		try {
			File file = new File(folder + imeFajla);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja knjiga.");
		}
	}
	
	public void ucitajAdministratore() {
		try {
			File file = new File ("src/fajlovi/administratori.txt");
			BufferedReader reader = new BufferedReader (new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String idString = split[0];
				int idKod = Integer.parseInt(idString);
				String ime = split[1];
				String prezime = split[2];
				String jmbg = split[3];
				String adresa = split[4];
//				int polInt = Integer.parseInt(split[5]);
				Pol pol = Pol.valueOf(split[5]);
				String korisnickoIme = split[6];
				String korisnickaSifra = split[7];
				double plata = Double.parseDouble(split[8]);
				Administrator admin = new Administrator(idKod, ime, prezime, jmbg, adresa, pol, korisnickoIme, korisnickaSifra, plata);
				
				this.administratori.put(admin.getId(), admin);
				
				}
				// 003|560.0|Test Knjiga|false|Test autor|14|false
			
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o knjigama");
			e.printStackTrace();
		}
	}
	
	public void snimiAdministratore(String imeFajla) {
		String sadrzaj = "";
		for (Administrator administrator: this.administratori.values()) {
			sadrzaj += administrator.getId() + "|" + administrator.getIme() + "|" + administrator.getPrezime() + "|" + administrator.getJmbg() + "|" + 
					administrator.getAdresa() + "|" + administrator.getPol()  + "|" + administrator.getKorisnickoIme() + "|" + administrator.getKorisnickaSifra() + "|" + administrator.getPlata() + "\n";
		}
		try {
			File file = new File(folder + imeFajla);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja administratora.");
		}
	}
	public void ucitajBibliotekare() {
		try {
			File file = new File ("src/fajlovi/bibliotekari.txt");
			BufferedReader reader = new BufferedReader (new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String idString = split[0];
				int idKod = Integer.parseInt(idString);
				String ime = split[1];
				String prezime = split[2];
				String jmbg = split[3];
				String adresa = split[4];
				Pol pol = Pol.valueOf(split[5]);
				String korisnickoIme = split[6];
				String korisnickaSifra = split[7];
				double plata = Double.parseDouble(split[8]);
				Bibliotekar bibliotekar = new Bibliotekar(idKod, ime, prezime, jmbg, adresa, pol, korisnickoIme, korisnickaSifra, plata);
				
				this.bibliotekari.put(bibliotekar.getId(), bibliotekar);
				
				}
				// 003|560.0|Test Knjiga|false|Test autor|14|false
			
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o knjigama");
			e.printStackTrace();
		}
	}
	
	public void snimiBibliotekare(String imeFajla) {
		String sadrzaj = "";
		for (Bibliotekar bibliotekar: this.bibliotekari.values()) {
			sadrzaj += bibliotekar.getId() + "|" + bibliotekar.getIme() + "|" + bibliotekar.getPrezime() + "|" + bibliotekar.getJmbg() + "|" + 
					bibliotekar.getAdresa() + "|" + bibliotekar.getPol()  + "|" + bibliotekar.getKorisnickoIme() + "|" + bibliotekar.getKorisnickaSifra() + "|" + bibliotekar.getPlata() + "\n";
		}
		try {
			File file = new File(folder + imeFajla);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja administratora.");
		}
	}
		
	public void ucitajTipoveClanarine() {
		try {
			File file = new File("src/fajlovi/tipoviclanarine.txt");
			BufferedReader reader = new BufferedReader (new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String idString = split[0];
				int idKod = Integer.parseInt(idString);
				String naziv = split[1];
				double cena = Double.parseDouble(split[2]);
				TipClanarine tip = new TipClanarine(idKod, naziv, cena);
				
				this.tipoviClanarine.put(tip.getId(), tip);
				
				}
				// 003|560.0|Test Knjiga|false|Test autor|14|false
			
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o knjigama");
			e.printStackTrace();
		}
	}
	
	public void snimiTipClanarine(String imeFajla) {
		String sadrzaj = "";
		for (TipClanarine tipoviClanarine: this.tipoviClanarine.values()) {
			sadrzaj += tipoviClanarine.getId() + "|" + tipoviClanarine.getNaziv() + "|" + tipoviClanarine.getCena() + "\n";
		}
		try {
			File file = new File(folder + imeFajla);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja tipa clanarine.");
		}
	}
	
	public void ucitajClanove() {
		try {
			File file = new File("src/fajlovi/clanovi.txt");
			BufferedReader reader = new BufferedReader (new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String idString = split[0];
				int idKod = Integer.parseInt(idString);
				String ime = split[1];
				String prezime = split[2];
				String jmbg = split[3];
				String adresa = split[4];
				Pol pol = Pol.valueOf(split[5]);
				String brClanKarte = split[6];
				LocalDate datumPoslednjeUplate = LocalDate.parse(split[7]);
				int brMeseciUplacenih = Integer.parseInt(split[8]);
				boolean aktivan = Boolean.parseBoolean(split[9]);
				int tipClanarineId = Integer.valueOf(split[10]);
				TipClanarine tipClanarine = this.tipoviClanarine.get(tipClanarineId);
				ClanBiblioteke clan = new ClanBiblioteke(idKod, ime, prezime, jmbg, adresa, pol, brClanKarte, datumPoslednjeUplate, brMeseciUplacenih, aktivan, tipClanarine);
				
				this.clanovi.put(clan.getId(), clan);
				
				}
				// 003|560.0|Test Knjiga|false|Test autor|14|false
			
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o knjigama");
			e.printStackTrace();
		}
	}
	
	public void snimiClanoveBiblioteke(String imeFajla) {
		String sadrzaj = "";
		for (ClanBiblioteke clan: this.clanovi.values()) {
			sadrzaj += clan.getId() + "|" + clan.getIme() + "|" + clan.getPrezime() + "|" + clan.getJmbg() + "|" + clan.getAdresa() + "|" + clan.getPol()
			 	+ "|" + clan.getBrClanKarte() + "|" + clan.getDatumPoslednjeUplate() + "|" + clan.getBrojMeseciClanarine() + "|" + clan.isAktivan()
			 	+ "|" + clan.getTipClanarine().getId() + "\n";
		}
		try {
			File file = new File(folder + imeFajla);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(sadrzaj);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja clana biblioteke.");
		}
	}
		
		public void ucitajPrimerkeKnjiga() {
			try {
				File file = new File("src/fajlovi/primerci.txt");
				BufferedReader reader = new BufferedReader (new FileReader(file));
				String line;
				while((line = reader.readLine()) != null) {
					String[] split = line.split("\\|");
					String idString = split[0];
					int idKod = Integer.parseInt(idString);
					int brStrana = Integer.parseInt(split[1]);
					int godinaStampanja = Integer.parseInt(split[2]);
					boolean jeIznajmljena = Boolean.parseBoolean(split[3]);
					int knjigaId = Integer.parseInt(split[6]);
					Knjiga knjiga = this.knjige.get(knjigaId);
					TipPoveza tipPoveza = TipPoveza.valueOf(split[4]);
					Jezik jezik = Jezik.valueOf(split[5]);
					
					
					PrimerakKnjige primerak = new PrimerakKnjige(idKod, brStrana, godinaStampanja, jeIznajmljena, knjiga, tipPoveza, jezik);
					
					this.primerci.put(primerak.getId(), primerak);
					knjiga.getSviPrimerci().add(primerak); 
					
					}
				reader.close();
			} catch (IOException e) {
				System.out.println("Greska prilikom ucitavanja podataka o knjigama");
				e.printStackTrace();
			}
		}
		
		public void snimiPrimerkeKnjige(String imeFajla) {
			String sadrzaj = "";
			for (PrimerakKnjige primerakKnjige: this.primerci.values()) {
				sadrzaj += primerakKnjige.getId() + "|" + primerakKnjige.getBrStrana() + "|" + primerakKnjige.getGodinaStampanja() + "|" + primerakKnjige.getIznajmljena()
				+ "|" + primerakKnjige.getTipPoveza() + "|" + primerakKnjige.getJezikStampanja() + "|" + primerakKnjige.getKnjiga().getId() + "\n";
			}
			try {
				File file = new File(folder + imeFajla);
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(sadrzaj);
				writer.close();
			} catch (IOException e) {
				System.out.println("Greska prilikom snimanja primerka knjige.");
			}
		}
		
		public void ucitajIznajmljivanje() {
			try {
				File file = new File ("src/fajlovi/iznajmljivanja.txt");
				BufferedReader reader = new BufferedReader (new FileReader(file));
				String line;
				while((line = reader.readLine()) != null) {
					String[] lineSplit = line.split("\\|");
					int id = Integer.parseInt(lineSplit[0]);
					LocalDate datumIznajmljivanja = LocalDate.parse(lineSplit[1]);
					LocalDate datumVracanja = LocalDate.parse(lineSplit[2]);
					int zaposleniId = Integer.parseInt(lineSplit[3]);
					Zaposleni zaposleni = this.bibliotekari.get(zaposleniId);
					if (zaposleni == null) {
						zaposleni = this.administratori.get(zaposleniId);
					}
					int clanId = Integer.parseInt(lineSplit[4]);
					ClanBiblioteke clan = this.clanovi.get(clanId);
					String idPrimerakaStr = lineSplit[5];
					String[] idPrimeraka = idPrimerakaStr.substring(1, idPrimerakaStr.length() - 1).split(", ");
					
					Iznajmljivanje iznajmljivanje = new Iznajmljivanje(id, datumIznajmljivanja, datumVracanja, zaposleni, clan);				
					iznajmljeneKnjige.put(iznajmljivanje.getId(), iznajmljivanje);
					
					for(String idP : idPrimeraka) {
						int intId = Integer.parseInt(idP);
						PrimerakKnjige pk = this.primerci.get(intId);
						iznajmljivanje.getPrimerci().add(pk);
					}
				}
				reader.close();
			} catch (IOException e) {
				System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
			}
		}
		
		public void snimiIznajmljivanja(String imeFajla) {
			String sadrzaj = "";
			for (Iznajmljivanje iznajmljivanja : this.iznajmljeneKnjige.values()) {
				ArrayList<Integer> idPrimeraka = new ArrayList<>();
				for (PrimerakKnjige primerak : iznajmljivanja.getPrimerci()) {
					idPrimeraka.add(primerak.getId());
				}
				
				sadrzaj += iznajmljivanja.getId() + "|" +iznajmljivanja.getDatumIznajmljivanja() + "|" + iznajmljivanja.getDatumVracanja()
				+ "|" + iznajmljivanja.getZaposleni().getId() + "|" + iznajmljivanja.getClan().getId() + "|" + idPrimeraka + "\n";
			}
			try {
				File file = new File(folder + imeFajla);
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(sadrzaj);
				writer.close();
			} catch (IOException e) {
				System.out.println("Greska prilikom snimanja iznajmljivanja knjige.");
			}
		}
		
		public void dodajKnjigu(Knjiga knjiga) {
			this.knjige.put(knjiga.getId(), knjiga);
		}
		
		public void dodajZanr(ZanrKnjige zanr) {
			this.zanrovi.put(zanr.getId(), zanr);
		}
		
		public void dodajAdministratora(Administrator administrator) {
			this.administratori.put(administrator.getId(), administrator);
		}
		
		public void dodajBibliotekara(Bibliotekar bibliotekar) {
			this.bibliotekari.put(bibliotekar.getId(), bibliotekar);
		}
		
		public void dodajTipClanarine(TipClanarine clanarina) {
			this.tipoviClanarine.put(clanarina.getId(), clanarina);
		}
		
		public void dodajClanaBiblioteke(ClanBiblioteke clan) {
			this.clanovi.put(clan.getId(), clan);
		}
		
		public void dodajPrimerakKnjige(PrimerakKnjige primerak) {
			this.primerci.put(primerak.getId(), primerak);
		}
	
		public void dodajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
			this.iznajmljeneKnjige.put(iznajmljivanje.getId(), iznajmljivanje);
		}
		
}
