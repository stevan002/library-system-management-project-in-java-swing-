package biblioteka;
import java.util.*;

/**
 * 
 */
public class Knjiga {
    
    protected int id;
    protected String naslovKnjige;
    protected String originalniNaslovKnjige;
    protected String pisac;
    protected int godinaPublikacije;
    protected String opis;
    protected ZanrKnjige zanr;
    protected List<PrimerakKnjige> sviPrimerci;
    protected Jezik jezikOriginala;
    
    public Knjiga() {
    }
    
	public Knjiga(int id, String naslovKnjige, String originalniNaslovKnjige, String pisac, int godinaPublikacije,
			String opis, ZanrKnjige zanr, List<PrimerakKnjige> sviPrimerci, Jezik jezikOriginala) {
		super();
		this.id = id;
		this.naslovKnjige = naslovKnjige;
		this.originalniNaslovKnjige = originalniNaslovKnjige;
		this.pisac = pisac;
		this.godinaPublikacije = godinaPublikacije;
		this.opis = opis;
		this.zanr = zanr;
		this.sviPrimerci = sviPrimerci;
		this.jezikOriginala = jezikOriginala;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaslovKnjige() {
		return naslovKnjige;
	}

	public void setNaslovKnjige(String naslovKnjige) {
		this.naslovKnjige = naslovKnjige;
	}

	public String getOriginalniNaslovKnjige() {
		return originalniNaslovKnjige;
	}

	public void setOriginalniNaslovKnjige(String originalniNaslovKnjige) {
		this.originalniNaslovKnjige = originalniNaslovKnjige;
	}

	public String getPisac() {
		return pisac;
	}

	public void setPisac(String pisac) {
		this.pisac = pisac;
	}

	public int getGodinaPublikacije() {
		return godinaPublikacije;
	}

	public void setGodinaPublikacije(int godinaPublikacije) {
		this.godinaPublikacije = godinaPublikacije;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ZanrKnjige getZanr() {
		return zanr;
	}

	public void setZanr(ZanrKnjige zanr) {
		this.zanr = zanr;
	}

	public List<PrimerakKnjige> getSviPrimerci() {
		return sviPrimerci;
	}

	public void setSviPrimerci(List<PrimerakKnjige> sviPrimerci) {
		this.sviPrimerci = sviPrimerci;
	}

	public Jezik getJezikOriginala() {
		return jezikOriginala;
	}

	public void setJezikOriginala(Jezik jezikOriginala) {
		this.jezikOriginala = jezikOriginala;
	}

	@Override
	public String toString() {
		return "Knjiga [id=" + id + ", naslovKnjige=" + naslovKnjige + ", originalniNaslovKnjige="
				+ originalniNaslovKnjige + ", pisac=" + pisac + ", godinaPublikacije=" + godinaPublikacije + ", opis="
				+ opis + ", zanr=" + zanr + ", sviPrimerci=" + sviPrimerci + ", jezikOriginala=" + jezikOriginala + "]";
	}
	
	
    
    

}