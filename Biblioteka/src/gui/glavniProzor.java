package gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import biblioteka.BibliotekaMain;
import biblioteka.Zaposleni;

public class glavniProzor extends JFrame {
	private static final long serialVersionUID = -5457898115685474680L;
	
	private JMenuBar mainMeni = new JMenuBar();
	
	private JMenu katalogMeni = new JMenu("katalog");
	private JMenuItem knjigeItem = new JMenuItem("Knjige");
	private JMenuItem primerciKnjigeItem = new JMenuItem("Knjige");
	private JMenuItem zanrItem = new JMenuItem("Knjige");
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
		
		setTitle("Zaposleni: " + prijavljeniKorisnik.getKorisnickoIme() + "[" + radnoMesto + "]");
		
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon LoginImage = new ImageIcon("src/slike/login-logo.png");
		setIconImage(LoginImage.getImage());
		getContentPage().setBackground(new Color(142, 104, 104));
		initMenu();
		initAction();
		
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
				KnjigeProzor kp = new KnjigeProzor(biblioteka);
				kp.setVisible(true);		
			}
		});
		
		zanrItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanroviProzor zp = new ZanroviProzor(biblioteka);
				zp.setVisible(true);			
			}
		});
}
	
	if(radnoMesto.equals("Bibliotekar")) {
		administratoriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdministratoriProzor ap = new AdministratoriProzor(biblioteka);
				ap.setVisible(false);
				JOptionPane.showMessageDialog(null, "Bibliotekari nemaju pristup administratorima!", "Greska", JOptionPane.ERROR_MESSAGE);
				
			}
		});
	} else {
		administratoriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdministratoriProzor ap = new AdministratoriProzor(biblioteka);
					ap.setVisible(true);				
			}
		});
	}
	
	if(radnoMesto.equals("Bibliotekar")) {
		bibliotekariItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekariProzor bp = new BibliotekariProzor(biblioteka);
				bp.setVisible(false);
				JOptionPane.showMessageDialog(null, "Bibliotekari nemaju pristup drugim bibliotekarima!", "Greska", JOptionPane.ERROR_MESSAGE);
				
			}
		});
	} else {
		bibliotekariItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekariProzor bp = new BibliotekariProzor(biblioteka);
					bp.setVisible(true);				
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
		
	clanoviBibliotekeItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ClanoviBibliotekeProzor cbp = new ClanoviBibliotekeProzor(biblioteka);
			cbp.setVisible(true);
			
		}
	});
	
	iznajmljivanjaItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			IznajmljivanjaProzor ip = new IznajmljivanjaProzor(biblioteka);
			ip.setVisible(true);
			
		}
	});
	
	tipClanarineItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			TipClanarineProzor tcp = new TipClanarineProzor(biblioteka);
			tcp.setVisible(true);
			
		}
	});
	
}

}
