package biblioteka;
import java.util.*;

/**
 * 
 */
public class Knjiga {

    /**
     * Default constructor
     */
    public Knjiga() {
    }

    /**
     * 
     */
    protected int id;

    /**
     * 
     */
    protected String naslovKnjige;

    /**
     * 
     */
    protected String originalniNaslovKnjige;

    /**
     * 
     */
    protected String pisac;

    /**
     * 
     */
    protected int godinaPublikacije;

    /**
     * 
     */
    protected String opis;

    /**
     * 
     */
    protected ZanrKnjige zanr;

    /**
     * 
     */
    protected List<PrimerakKnjige> sviPrimerci;

    /**
     * 
     */
    protected Jezik jezikOriginala;

}