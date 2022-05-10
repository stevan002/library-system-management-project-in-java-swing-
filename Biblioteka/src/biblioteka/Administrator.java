package biblioteka;
import java.util.*;


public class Administrator extends Zaposleni {

    
    public Administrator() {
    	
    }

	public Administrator(int id, String ime, String prezime, String jmbg, String adresa, Pol pol, String korisnickoIme,
			String korisnickaSifra, double plata) {
		super(id, ime, prezime, jmbg, adresa, pol, korisnickoIme, korisnickaSifra, plata);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Administrator [korisnickoIme=" + korisnickoIme + ", korisnickaSifra=" + korisnickaSifra + ", plata="
				+ plata + ", id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa="
				+ adresa + ", pol=" + pol + "]";
	}
	

}