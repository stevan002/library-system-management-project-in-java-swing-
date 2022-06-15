package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import biblioteka.BibliotekaMain;
import biblioteka.Zaposleni;
import gui.FormezaPrikaz.KnjigeProzor;

public class glavniProzor extends JFrame {
	
	private static final long serialVersionUID = -5647113125523453915L;

	private JMenuBar mainMeni = new JMenuBar();
	
	private JMenu katalogMeni = new JMenu("Katalog");
	private JMenuItem knjigeItem = new JMenuItem("Knjige");
	private JMenuItem primerciKnjigeItem = new JMenuItem("Primerci Knjige");
	private JMenuItem zanrItem = new JMenuItem("Zanrovi");
	private JMenuItem tipClanarineItem = new JMenuItem("Tipovi Clanarine");
	private JMenuItem iznajmljivanjeItem = new JMenuItem("Iznajmljivanja");
	
	private JMenu zaposleniMeni = new JMenu("Zaposleni");
	private JMenuItem administratoriItem = new JMenuItem("Adminstratori");
	private JMenuItem bibliotekariItem = new JMenuItem("Bibliotekari");
	
	private JMenu clanoviMeni = new JMenu("Clanovi");
	private JMenuItem clanoviItem = new JMenuItem("Clanovi biblioteke");
	
	private BibliotekaMain biblioteka;
	private Zaposleni prijavljeniKorisnik;
	
	private String radnoMesto;
	
	public glavniProzor(BibliotekaMain biblioteka, Zaposleni prijavljeniKorisnik){
		this.biblioteka = biblioteka;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		radnoMesto = prijavljeniKorisnik.getClass().getSimpleName();
		
		setTitle("Zaposleni: " +  radnoMesto );
		
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon LoginImage = new ImageIcon("src/slike/login-logo.png");
		setIconImage(LoginImage.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		initMenu();
		initActions();
		
	}
	public void initMenu() {
		setJMenuBar(mainMeni);
		mainMeni.add(katalogMeni);
		katalogMeni.add(knjigeItem);
		katalogMeni.add(primerciKnjigeItem);
		katalogMeni.add(zanrItem);
		katalogMeni.add(tipClanarineItem);
		katalogMeni.add(iznajmljivanjeItem);
		
		mainMeni.add(zaposleniMeni);
		zaposleniMeni.add(administratoriItem);
		zaposleniMeni.add(bibliotekariItem);
		
		mainMeni.add(clanoviMeni);
		clanoviMeni.add(clanoviItem);
	}
	
	public void initActions() {
		radnoMesto = prijavljeniKorisnik.getClass().getSimpleName();
		knjigeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				KnjigeProzor kp = new KnjigeProzor(biblioteka);
				kp.setVisible(true);
			}
		});
		
//		zanrItem.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				ZanroviProzor zr = new ZanroviProzor(biblioteka);
//				zp.setVisible(true);
//				
//			}
//		});
	}

}
