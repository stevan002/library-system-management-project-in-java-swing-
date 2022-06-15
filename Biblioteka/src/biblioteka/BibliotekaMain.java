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
	
	public Zaposleni login(String korisnickoIme, String lozinka) {
		for(Administrator administrator: this.administratori.values()) {
			if(administrator.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && administrator.getKorisnickaSifra().equals(lozinka) && !administrator.isObrisana()) {
				return administrator;
			}
		}
		for(Bibliotekar bibliotekar: this.bibliotekari.values()) {
			if(bibliotekar.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && bibliotekar.getKorisnickaSifra().equals(lozinka) && !bibliotekar.isObrisana()) {
				return bibliotekar;
			}
		}
		return null;
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
				boolean obrisana = Boolean.parseBoolean(split[8]);
				
				
				Knjiga knjiga = new Knjiga(idKod, naslov, originalNaslov, pisac, godinaPublikacije, opis, zanr, jezik, obrisana);
				
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
					+ knjiga.getJezikOriginala() + "|" + knjiga.isObrisana() + "\n";
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
	
	public HashMap<Integer, Knjiga> sveNeobrisaneKnjige() {
		HashMap<Integer, Knjiga> neobrisaneKnjige = new HashMap<Integer, Knjiga>();
		for(Knjiga knjiga: knjige.values()) {
			if(!knjiga.isObrisana()) {
				neobrisaneKnjige.put(knjiga.getId(), knjiga);
			}
		}
		return neobrisaneKnjige;
	}
	
	public Knjiga pronadjiKnjigu(int id) {
		for (Knjiga knjiga : sveNeobrisaneKnjige().values()) {
			if(knjiga.getId() == id) {
				return knjiga;
			}
		}
		return null;
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
				boolean obrisan = Boolean.parseBoolean(split[3]);
				
				ZanrKnjige zanr = new ZanrKnjige(id, oznaka, opis, obrisan);
				
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
			sadrzaj += zanr.getId() + "|" + zanr.getOznaka() + "|" + zanr.getOpis() + "|" + zanr.isObrisan() + "\n";
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
	
	public HashMap<Integer, ZanrKnjige> sviNeobrisaniZanrovi() {
		HashMap<Integer, ZanrKnjige> neobrisaniZanrovi = new HashMap<Integer, ZanrKnjige>();
		for(ZanrKnjige zanr: zanrovi.values()) {
			if(!zanr.isObrisan()) {
				neobrisaniZanrovi.put(zanr.getId(), zanr);
			}
		}
		return neobrisaniZanrovi;
	}
	
	public ZanrKnjige pronadjiZanr(int id) {
		for (ZanrKnjige zanr : sviNeobrisaniZanrovi().values()) {
			if(zanr.getId() == id) {
				return zanr;
			}
		}
		return null;
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
				boolean obrisana = Boolean.parseBoolean(split[9]);
				Administrator admin = new Administrator(idKod, ime, prezime, jmbg, adresa, pol, obrisana, korisnickoIme, korisnickaSifra, plata);
				
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
					administrator.getAdresa() + "|" + administrator.getPol()  + "|" + administrator.getKorisnickoIme() + "|" + administrator.getKorisnickaSifra() + "|" + administrator.getPlata() + "|" + administrator.isObrisana() + "\n";
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
	
	public HashMap<Integer, Administrator> sviNeobrisaniAdministratori() {
		HashMap<Integer, Administrator> neobrisaniAdministratori = new HashMap<Integer, Administrator>();
		for(Administrator administrator : administratori.values()) {
			if(!administrator.isObrisana()) {
				neobrisaniAdministratori.put(administrator.getId(), administrator);
			}
		}
		return neobrisaniAdministratori;
	}
	
	public Administrator pronadjiAdministratora(int id) {
		for (Administrator administrator : sviNeobrisaniAdministratori().values()) {
			if(administrator.getId() == id) {
				return administrator;
			}
		}
		return null;
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
				boolean obrisana = Boolean.parseBoolean(split[9]);
				Bibliotekar bibliotekar = new Bibliotekar(idKod, ime, prezime, jmbg, adresa, pol, obrisana, korisnickoIme, korisnickaSifra, plata);
				
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
					bibliotekar.getAdresa() + "|" + bibliotekar.getPol()  + "|" + bibliotekar.getKorisnickoIme() + "|" + bibliotekar.getKorisnickaSifra() + "|" + bibliotekar.getPlata() + "|" + bibliotekar.isObrisana() + "\n";
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
	
	public HashMap<Integer, Bibliotekar> sviNeobrisaniBibliotekari() {
		HashMap<Integer, Bibliotekar> neobrisaniBibliotekari = new HashMap<Integer, Bibliotekar>();
		for(Bibliotekar bibliotekar : bibliotekari.values()) {
			if(!bibliotekar.isObrisana()) {
				neobrisaniBibliotekari.put(bibliotekar.getId(), bibliotekar);
			}
		}
		return neobrisaniBibliotekari;
	}
	
	public Bibliotekar pronadjiBibliotekara(int id) {
		for (Bibliotekar bibliotekar : sviNeobrisaniBibliotekari().values()) {
			if(bibliotekar.getId() == id) {
				return bibliotekar;
			}
		}
		return null;
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
				boolean obrisan = Boolean.parseBoolean(split[3]);
				TipClanarine tip = new TipClanarine(idKod, naziv, cena, obrisan);
				
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
			sadrzaj += tipoviClanarine.getId() + "|" + tipoviClanarine.getNaziv() + "|" + tipoviClanarine.getCena() + "|" + tipoviClanarine.isObrisan() + "\n";
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
	
	public HashMap<Integer, TipClanarine> sviNeobrisaniTipoviClanarine() {
		HashMap<Integer, TipClanarine> neobrisaniTipoviClanarine = new HashMap<Integer, TipClanarine>();
		for(TipClanarine tipClanarine : tipoviClanarine.values()) {
			if(!tipClanarine.isObrisan()) {
				neobrisaniTipoviClanarine.put(tipClanarine.getId(), tipClanarine);
			}
		}
		return neobrisaniTipoviClanarine;
	}
	
	public TipClanarine pronadjiTipClanarine(int id) {
		for (TipClanarine tip : sviNeobrisaniTipoviClanarine().values()) {
			if(tip.getId() == id) {
				return tip;
			}
		}
		return null;
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
				boolean obrisana = Boolean.parseBoolean(split[10]);
				ClanBiblioteke clan = new ClanBiblioteke(idKod, ime, prezime, jmbg, adresa, pol, obrisana, brClanKarte, datumPoslednjeUplate, brMeseciUplacenih, aktivan, tipClanarine);
				
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
			 	+ "|" + clan.getTipClanarine().getId() + "|" + clan.isObrisana() + "\n";
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
	
	public HashMap<Integer, ClanBiblioteke> sviNeobrisaniClanoviBiblioteke() {
		HashMap<Integer, ClanBiblioteke> neobrisaniClanoviBiblioteke = new HashMap<Integer, ClanBiblioteke>();
		for(ClanBiblioteke clan : clanovi.values()) {
			if(!clan.isObrisana()) {
				neobrisaniClanoviBiblioteke.put(clan.getId(), clan);
			}
		}
		return neobrisaniClanoviBiblioteke;
	}
	
	public ClanBiblioteke pronadjiClanaBiblioteke(int id) {
		for (ClanBiblioteke clanBiblioteke : sviNeobrisaniClanoviBiblioteke().values()) {
			if(clanBiblioteke.getId() == id) {
				return clanBiblioteke;
			}
		}
		return null;
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
					boolean obrisan = Boolean.parseBoolean(split[6]);
					
					
					PrimerakKnjige primerak = new PrimerakKnjige(idKod, brStrana, godinaStampanja, jeIznajmljena, knjiga, tipPoveza, jezik, obrisan);
					
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
				+ "|" + primerakKnjige.getTipPoveza() + "|" + primerakKnjige.getJezikStampanja() + "|" + primerakKnjige.getKnjiga().getId() + "|" + primerakKnjige.isObrisan() + "\n";
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
		
		public HashMap<Integer, PrimerakKnjige> sviNeobrisaniPrimerciKnjige() {
			HashMap<Integer, PrimerakKnjige> neobrisaniPrimerciKnjige = new HashMap<Integer, PrimerakKnjige>();
			for(PrimerakKnjige primerak : primerci.values()) {
				if(!primerak.isObrisan()) {
					neobrisaniPrimerciKnjige.put(primerak.getId(), primerak);
				}
			}
			return neobrisaniPrimerciKnjige;
		}
		
		public PrimerakKnjige pronadjiPrimerak(int id) {
			for (PrimerakKnjige primerakKnjige : sviNeobrisaniPrimerciKnjige().values()) {
				if(primerakKnjige.getId() == id) {
					return primerakKnjige;
				}
			}
			return null;
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
					boolean obrisana = Boolean.parseBoolean(lineSplit[6]);
					
					Iznajmljivanje iznajmljivanje = new Iznajmljivanje(id, datumIznajmljivanja, datumVracanja, zaposleni, clan, obrisana);				
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
				+ "|" + iznajmljivanja.getZaposleni().getId() + "|" + iznajmljivanja.getClan().getId() + "|" + idPrimeraka + "|" + iznajmljivanja.isObrisana() + "\n";
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
		
		public HashMap<Integer, Iznajmljivanje> svaNeobrisanaIznajmljivanja() {
			HashMap<Integer, Iznajmljivanje> neobrisanaIznajmljivanja = new HashMap<Integer, Iznajmljivanje>();
			for(Iznajmljivanje iznajmljivanje : iznajmljeneKnjige.values()) {
				if(!iznajmljivanje.isObrisana()) {
					neobrisanaIznajmljivanja.put(iznajmljivanje.getId(), iznajmljivanje);
				}
			}
			return neobrisanaIznajmljivanja;
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
