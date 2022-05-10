package biblioteka;
import java.util.*;

/**
 * 
 */
public class Zaposleni extends Osoba {

    public Zaposleni() {
    }
    
    protected String korisnickoIme;
    protected String korisnickaSifra;
    protected double plata;
	public Zaposleni(int id, String ime, String prezime, String jmbg, String adresa, Pol pol, String korisnickoIme,
			String korisnickaSifra, double plata) {
		super(id, ime, prezime, jmbg, adresa, pol);
		this.korisnickoIme = korisnickoIme;
		this.korisnickaSifra = korisnickaSifra;
		this.plata = plata;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getKorisnickaSifra() {
		return korisnickaSifra;
	}
	public void setKorisnickaSifra(String korisnickaSifra) {
		this.korisnickaSifra = korisnickaSifra;
	}
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
	@Override
	public String toString() {
		return "Zaposleni [korisnickoIme=" + korisnickoIme + ", korisnickaSifra=" + korisnickaSifra + ", plata=" + plata
				+ ", id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa
				+ ", pol=" + pol + "]";
	}
    
    

}