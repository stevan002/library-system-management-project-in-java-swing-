package biblioteka;
import java.util.*;

/**
 * 
 */
public class ZanrKnjige {

    
    public ZanrKnjige() {
    }
    protected int id;
    protected String oznaka;
    protected String opis;
    protected ArrayList<Knjiga> knjige; 
    
	public ZanrKnjige(int id, String oznaka, String opis) {
		super();
		this.id = id;
		this.oznaka = oznaka;
		this.opis = opis;
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}



	@Override
	public String toString() {
		return "ZanrKnjige [id=" + id + ", oznaka=" + oznaka + ", opis=" + opis + "]";
	}

	
	
    

}