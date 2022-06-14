package biblioteka;
import java.util.*;

/**
 * 
 */
public class TipClanarine {

	public TipClanarine() {
    }

    protected int id;
    protected String naziv;
    protected double cena;
    boolean obrisan;
    
	public TipClanarine(int id, String naziv, double cena, boolean obrisan) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.obrisan = obrisan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "TipClanarine [id=" + id + ", naziv=" + naziv + ", cena=" + cena + ", obrisan=" + obrisan + "]";
	}

	
	
	
    
    

}