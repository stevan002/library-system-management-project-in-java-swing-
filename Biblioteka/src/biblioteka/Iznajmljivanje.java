package biblioteka;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Iznajmljivanje {

    public Iznajmljivanje() {
    }
    protected LocalDate datumIznajmljivanja;
    protected LocalDate datumVracanja;
    protected ClanBiblioteke clan;
    protected PrimerakKnjige primerak;
	public Iznajmljivanje(LocalDate datumIznajmljivanja, LocalDate datumVracanja, ClanBiblioteke clan,
			PrimerakKnjige primerak) {
		super();
		this.datumIznajmljivanja = datumIznajmljivanja;
		this.datumVracanja = datumVracanja;
		this.clan = clan;
		this.primerak = primerak;
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
	public PrimerakKnjige getPrimerak() {
		return primerak;
	}
	public void setPrimerak(PrimerakKnjige primerak) {
		this.primerak = primerak;
	}
	@Override
	public String toString() {
		return "Iznajmljivanje [datumIznajmljivanja=" + datumIznajmljivanja + ", datumVracanja=" + datumVracanja
				+ ", clan=" + clan + ", primerak=" + primerak + "]";
	}
    
    

}