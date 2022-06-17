package biblioteka;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class ClanBiblioteke extends Osoba {

    
    public ClanBiblioteke() {
    }
    protected String brClanKarte;
    protected LocalDate datumPoslednjeUplate;
    protected int brojMeseciClanarine;
    protected boolean aktivan;
    protected TipClanarine tipClanarine;
	public ClanBiblioteke(int id, String ime, String prezime, String jmbg, String adresa, Pol pol, boolean obrisana, String brClanKarte,
			LocalDate datumPoslednjeUplate, int brojMeseciClanarine, boolean aktivan, TipClanarine tipClanarine) {
		super(id, ime, prezime, jmbg, adresa, pol, obrisana);
		this.brClanKarte = brClanKarte;
		this.datumPoslednjeUplate = datumPoslednjeUplate;
		this.brojMeseciClanarine = brojMeseciClanarine;
		this.aktivan = aktivan;
		this.tipClanarine = tipClanarine;
	}
	public String getBrClanKarte() {
		return brClanKarte;
	}
	public void setBrClanKarte(String brClanKarte) {
		this.brClanKarte = brClanKarte;
	}
	public LocalDate getDatumPoslednjeUplate() {
		return datumPoslednjeUplate;
	}
	public void setDatumPoslednjeUplate(LocalDate datumPoslednjeUplate) {
		this.datumPoslednjeUplate = datumPoslednjeUplate;
	}
	public int getBrojMeseciClanarine() {
		return brojMeseciClanarine;
	}
	public void setBrojMeseciClanarine(int brojMeseciClanarine) {
		this.brojMeseciClanarine = brojMeseciClanarine;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	public TipClanarine getTipClanarine() {
		return tipClanarine;
	}
	public void setTipClanarine(TipClanarine tipClanarine) {
		this.tipClanarine = tipClanarine;
	}
	
	@Override
	public String toString() {
		return ime + prezime; 
	}

}