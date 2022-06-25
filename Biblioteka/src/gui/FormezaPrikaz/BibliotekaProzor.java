package gui.FormezaPrikaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import biblioteka.Biblioteka;
import biblioteka.BibliotekaMain;
import gui.FormezaIzmenu.BibliotekaForma;
import net.miginfocom.swing.MigLayout;

public class BibliotekaProzor extends JFrame{

	private static final long serialVersionUID = -5857043342207226797L;
	private JLabel lblID = new JLabel("ID: ");
	private JLabel txtID = new JLabel();
	private JLabel lblNaziv = new JLabel("Naziv: ");
	private JLabel txtNaziv = new JLabel();
	private JLabel lblAdresa = new JLabel("Adresa: ");
	private JLabel txtAdresa = new JLabel();
	private JLabel lblTelefon = new JLabel("Telefon: ");
	private JLabel txtTelefon = new JLabel();
	private JLabel lblRadnoVreme = new JLabel("Radno vreme: ");
	private JLabel txtRadnoVreme = new JLabel();
	private JButton btnEdit = new JButton("Izmeni podatke");
	
	private BibliotekaMain bibliotekaMain;
	Biblioteka biblioteka;
	
	public BibliotekaProzor(BibliotekaMain bibliotekaMain, Biblioteka biblioteka) {
		this.bibliotekaMain = bibliotekaMain;
		this.biblioteka = biblioteka;
		setTitle("Podaci o biblioteci");
		setSize(250, 200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/slike/library-logo.png");
		setIconImage(image.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		popuniPolja();
		add(lblID);
		add(txtID);
		add(lblNaziv);
		add(txtNaziv);
		add(lblAdresa);
		add(txtAdresa);
		add(lblTelefon);
		add(txtTelefon);
		add(lblRadnoVreme);
		add(txtRadnoVreme);
		add(new JLabel());
		add(btnEdit);
		
	}
	
	private void initActions() {
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Biblioteka biblioteka = bibliotekaMain.pronadjiBiblioteku(1);
				BibliotekaForma bf = new BibliotekaForma(bibliotekaMain, biblioteka);
				bf.setVisible(true);
				
			}
		});
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(biblioteka.getId()));
		txtNaziv.setText(biblioteka.getNaziv());
		txtAdresa.setText(biblioteka.getAdresa());
		txtTelefon.setText(biblioteka.getTelefon());
		txtRadnoVreme.setText(biblioteka.getRadnoVreme());
	}
}
