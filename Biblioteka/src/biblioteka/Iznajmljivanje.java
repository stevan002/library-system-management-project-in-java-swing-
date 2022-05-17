package biblioteka;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Iznajmljivanje {

    public Iznajmljivanje() {
    	
    }
    protected int id;
    protected LocalDate datumIznajmljivanja;
    protected LocalDate datumVracanja;
    protected ClanBiblioteke clan;
    protected ArrayList<PrimerakKnjige> primerci;
    protected Zaposleni zaposleni;
	
	public Iznajmljivanje(int id, LocalDate datumIznajmljivanja, LocalDate datumVracanja, Zaposleni zaposleni, ClanBiblioteke clan) {
		super();
		this.id = id;
		this.datumIznajmljivanja = datumIznajmljivanja;
		this.datumVracanja = datumVracanja;
		this.clan = clan;
		this.primerci = new ArrayList<>();
		this.zaposleni = zaposleni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}
	public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}
	public LocalDate getDatumVracanja() {
		return datumVracanja;
	}
	public void setDatumVracanja(LocalDate datumVracanja) {
		this.datumVracanja = datumVracanja;
	}
	public ClanBiblioteke getClan() {
		return clan;
	}
	public void setClan(ClanBiblioteke clan) {
		this.clan = clan;
	}

	public ArrayList<PrimerakKnjige> getPrimerci() {
		return primerci;
	}

	public void setPrimerci(ArrayList<PrimerakKnjige> primerci) {
		this.primerci = primerci;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	@Override
	public String toString() {
		return "Iznajmljivanje [id=" + id + ", datumIznajmljivanja=" + datumIznajmljivanja + ", datumVracanja="
				+ datumVracanja + ", zaposleni=" + zaposleni + ", clan=" + clan + "]";
	}

	
	
    
    

}