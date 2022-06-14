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
    protected ArrayList<PrimerakKnjige> sviPrimerci;
    protected Jezik jezikOriginala;
    protected boolean obrisana;
    
    public Knjiga() {
    	this.sviPrimerci = new ArrayList<>();
    }

	public Knjiga(int id, String naslovKnjige, String originalniNaslovKnjige, String pisac, int godinaPublikacije,
			String opis, ZanrKnjige zanr, Jezik jezikOriginala, boolean obrisana) {
		super();
		this.id = id;
		this.naslovKnjige = naslovKnjige;
		this.originalniNaslovKnjige = originalniNaslovKnjige;
		this.pisac = pisac;
		this.godinaPublikacije = godinaPublikacije;
		this.opis = opis;
		this.zanr = zanr;
		this.sviPrimerci = new ArrayList<>();
		this.jezikOriginala = jezikOriginala;
		this.obrisana = obrisana;
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

	public Jezik getJezikOriginala() {
		return jezikOriginala;
	}

	public void setJezikOriginala(Jezik jezikOriginala) {
		this.jezikOriginala = jezikOriginala;
	}
	

	public ArrayList<PrimerakKnjige> getSviPrimerci() {
		return sviPrimerci;
	}

	public void setSviPrimerci(ArrayList<PrimerakKnjige> sviPrimerci) {
		this.sviPrimerci = sviPrimerci;
	}

	public boolean isObrisana() {
		return obrisana;
	}

	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}

	@Override
	public String toString() {
		return "Knjiga [id=" + id + ", naslovKnjige=" + naslovKnjige + ", originalniNaslovKnjige="
				+ originalniNaslovKnjige + ", pisac=" + pisac + ", godinaPublikacije=" + godinaPublikacije + ", opis="
				+ opis + ", zanr=" + zanr + ", sviPrimerci=" + sviPrimerci + ", jezikOriginala=" + jezikOriginala
				+ ", obrisana=" + obrisana + "]";
	}
}