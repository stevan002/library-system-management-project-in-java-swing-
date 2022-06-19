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
import gui.FormezaPrikaz.AdministratoriProzor;
import gui.FormezaPrikaz.IznajmljivanjaProzor;
import gui.FormezaPrikaz.KnjigeProzor;
import gui.FormezaPrikaz.PrimerciKnjigeProzor;
import gui.FormezaPrikaz.TipClanarineProzor;
import gui.FormezaPrikaz.ZanroviProzor;

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
	
//	private JMenu bibliotekaMeni = new JMenu("Biblioteka");
//	private JMenuItem bibliotekaItem = new JMenuItem("Izmena podataka");
	
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
		
//		mainMeni.add(bibliotekaMeni);
//		bibliotekaMeni.add(bibliotekaItem);
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
		
		zanrItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ZanroviProzor zr = new ZanroviProzor(biblioteka);
				zr.setVisible(true);
				
			}
		});
		
		if(radnoMesto.equals("Bibliotekar")) {
			administratoriItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AdministratoriProzor ap = new AdministratoriProzor(biblioteka);
					ap.setVisible(false);
					JOptionPane.showMessageDialog(null, "Bibliotekari nemaju pristup administratorima!", "Greksa", JOptionPane.ERROR_MESSAGE);
					
				}
			});
		}else {
			administratoriItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AdministratoriProzor ap = new AdministratoriProzor(biblioteka);
					ap.setVisible(true);
					
				}
			});
		}
		
		primerciKnjigeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerciKnjigeProzor pkp = new PrimerciKnjigeProzor(biblioteka);
				pkp.setVisible(true);
				
			}
		});
		
		tipClanarineItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TipClanarineProzor tcp = new TipClanarineProzor(biblioteka);
				tcp.setVisible(true);
				
			}
		});
		
		iznajmljivanjeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmljivanjaProzor ip = new IznajmljivanjaProzor(biblioteka);
				ip.setVisible(true);
				
			}
		});
		
//		if(radnoMesto.equals("Bibliotekar")) {
//			bibliotekariItem.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					BibliotekariProzor bp = new BibliotekariProzor(biblioteka);
//					bp.setVisible(false);
//					JOptionPane.showMessageDialog(null, "Bibliotekari nemaju pristup drugim bibliotekarima!", "Greksa", JOptionPane.ERROR_MESSAGE;)
//					
//				}
//			});
//		}else {
//			bibliotekariItem.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					BibliotekariProzor bp = new BibliotekariProzor(biblioteka);
//					bp.setVisible(true);
//					
//				}
//			});
//		}
//		
//		clanoviBibliotekeItem.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ClanoviBibliotekeProzor cbp = new ClanoviBibliotekeProzor(biblioteka);
//				cbp.setVisible(true);
//				
//			}
//		});
		
	}

}
