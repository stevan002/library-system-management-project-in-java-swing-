package biblioteka;
import java.util.*;

/**
 * 
 */
public class Bibliotekar extends Zaposleni {

    
    public Bibliotekar() {
    	
    }

	public Bibliotekar(int id, String ime, String prezime, String jmbg, String adresa, Pol pol, boolean obrisana, String korisnickoIme,
			String korisnickaSifra, double plata) {
		super(id, ime, prezime, jmbg, adresa, pol, obrisana, korisnickoIme, korisnickaSifra, plata);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Bibliotekar [korisnickoIme=" + korisnickoIme + ", korisnickaSifra=" + korisnickaSifra + ", plata="
				+ plata + ", id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa="
				+ adresa + ", pol=" + pol + ", obrisana=" + obrisana + "]";
	}

}