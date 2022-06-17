package biblioteka;
import java.util.*;

/**
 * 
 */
public class Biblioteka {
	
    public Biblioteka() {
    	
    }
    protected String naziv;
    protected String adresa;
    protected String telefon;
    protected String radnoVreme;
    
	public Biblioteka(String naziv, String adresa, String telefon, String radnoVreme) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.radnoVreme = radnoVreme;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getRadnoVreme() {
		return radnoVreme;
	}

	public void setRadnoVreme(String radnoVreme) {
		this.radnoVreme = radnoVreme;
	}

	@Override
	public String toString() {
		return naziv;
	}
    
	
	

}