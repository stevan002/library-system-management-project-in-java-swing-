package biblioteka;

/**
 * 
 */
public class PrimerakKnjige {
    

    protected int id;
    protected int brStrana;
    protected int godinaStampanja;
    protected boolean iznajmljena;
    protected Knjiga knjiga;
    protected TipPoveza tipPoveza;
    protected Jezik jezikStampanja;
    protected boolean obrisan;
    
    public PrimerakKnjige() {
    }
    
	public PrimerakKnjige(int id, int brStrana, int godinaStampanja, boolean iznajmljena, Knjiga knjiga,
			TipPoveza tipPoveza, Jezik jezikStampanja, boolean obrisan) {
		super();
		this.id = id;
		this.brStrana = brStrana;
		this.godinaStampanja = godinaStampanja;
		this.iznajmljena = iznajmljena;
		this.knjiga = knjiga;
		this.tipPoveza = tipPoveza;
		this.jezikStampanja = jezikStampanja;
		this.obrisan = obrisan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBrStrana() {
		return brStrana;
	}

	public void setBrStrana(int brStrana) {
		this.brStrana = brStrana;
	}

	public int getGodinaStampanja() {
		return godinaStampanja;
	}

	public void setGodinaStampanja(int godinaStampanja) {
		this.godinaStampanja = godinaStampanja;
	}

	public boolean getIznajmljena() {
		return iznajmljena;
	}

	public void setIznajmljena(boolean iznajmljena) {
		this.iznajmljena = iznajmljena;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public TipPoveza getTipPoveza() {
		return tipPoveza;
	}

	public void setTipPoveza(TipPoveza tipPoveza) {
		this.tipPoveza = tipPoveza;
	}

	public Jezik getJezikStampanja() {
		return jezikStampanja;
	}

	public void setJezikStampanja(Jezik jezikStampanja) {
		this.jezikStampanja = jezikStampanja;
	}
	

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "PrimerakKnjige [id=" + id + ", brStrana=" + brStrana + ", godinaStampanja=" + godinaStampanja
				+ ", iznajmljena=" + iznajmljena + ", knjiga=" + knjiga + ", tipPoveza=" + tipPoveza
				+ ", jezikStampanja=" + jezikStampanja + ", obrisan=" + obrisan + "]";
	}
	
    

}