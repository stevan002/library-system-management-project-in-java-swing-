package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import biblioteka.BibliotekaMain;
import biblioteka.Bibliotekar;
import biblioteka.Main;
import biblioteka.Pol;
import net.miginfocom.swing.MigLayout;

public class BibliotekariForma extends JFrame{

	private static final long serialVersionUID = -704115082473288954L;
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JRadioButton radiobuttonMuski = new JRadioButton("MUSKI");
	private JRadioButton radiobuttonZenski = new JRadioButton("ZENSKI");
	private ButtonGroup grupa = new ButtonGroup();
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pwfLozinka = new JPasswordField(20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Bibliotekar bibliotekar;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable bibliotekariTabela;
	
	public BibliotekariForma(BibliotekaMain biblioteka, Bibliotekar bibliotekar, DefaultTableModel tableModel, JTable bibliotekariTabela) {
		this.biblioteka = biblioteka;
		this.bibliotekar = bibliotekar;
		this.tableModel = tableModel;
		this.bibliotekariTabela = bibliotekariTabela;
		
		if(bibliotekar == null) {
			setTitle("Dodavanje bibliotekara");
		} else {
			setTitle("Izmena podataka - " + bibliotekar.getId());
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		if(bibliotekar != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblAdresa);
		add(txtAdresa);
		add(lblPol);
		add(radiobuttonMuski, "split 2");
		add(radiobuttonZenski);
		add(lblPlata);
		add(txtPlata);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pwfLozinka);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
		grupa.add(radiobuttonMuski);
		grupa.add(radiobuttonZenski);
		
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(validacija()) {
					int id = Integer.parseInt(txtID.getText().trim());		
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJMBG.getText().trim();
					String adresa = txtAdresa.getText().trim();
					Pol pol = Pol.MUSKI;
					if (radiobuttonZenski.isSelected()) {
						pol = Pol.ZENSKI;
					}
					
					double plata = Double.parseDouble(txtPlata.getText().trim());
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = String.valueOf(pwfLozinka.getPassword());
					
					if(bibliotekar == null) {
						bibliotekar = new Bibliotekar(id, ime, prezime, jmbg, adresa, pol, false, korisnickoIme, lozinka, plata);
						
						biblioteka.dodajBibliotekara(bibliotekar);
						Object[] red = kreirajRedTabele(bibliotekar);
						tableModel.addRow(red);
					} else {
						bibliotekar.setIme(ime);
						bibliotekar.setPrezime(prezime);
						bibliotekar.setJmbg(jmbg);
						bibliotekar.setAdresa(adresa);
						bibliotekar.setPol(pol);
						bibliotekar.setPlata(plata);
						bibliotekar.setKorisnickoIme(korisnickoIme);
						bibliotekar.setKorisnickaSifra(lozinka);
						
						int red = bibliotekariTabela.getSelectedRow();
						refresh(red, bibliotekar);
					}
					
					biblioteka.snimiBibliotekare(Main.BIBLIOTEKARI_FAJL);
					bibliotekariTabela.clearSelection();
					BibliotekariForma.this.dispose();
					BibliotekariForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bibliotekariTabela.clearSelection();
				BibliotekariForma.this.dispose();
				BibliotekariForma.this.setVisible(false);
				
			}
		});
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		} else if(bibliotekar == null) {
			int id = Integer.parseInt(txtID.getText().trim());
			Bibliotekar pronadjen = biblioteka.pronadjiBibliotekara(id);
			if(pronadjen != null) {
				poruka += "- Bibliotekar sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Morate uneti ime\n";
			ok = false;
		}
		
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Morate uneti prezime\n";
			ok = false;
		}
		
		if(txtJMBG.getText().trim().equals("")) {
			poruka += "- Morate uneti JMBG\n";
			ok = false;
		}
		
		else if(txtJMBG.getText().trim().length() != 13) {
			poruka += "- JMBG mora imati 13 cifara\n";
			ok = false;
		}
		
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Morate uneti adresu\n";
			ok = false;
		}
		
		try {
			Double.parseDouble(txtPlata.getText().trim());
		}
		catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj\n";
			ok = false;
		}
		
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Morate uneti korisnicko ime\n";
			ok = false;
		}
		
		if(pwfLozinka.getPassword().length == 0) {
			poruka += "- Morate uneti lozinku\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Ne odgovaraju podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(bibliotekar.getId()));
		txtIme.setText(bibliotekar.getIme());
		txtPrezime.setText(bibliotekar.getPrezime());
		txtJMBG.setText(bibliotekar.getJmbg());
		txtAdresa.setText(bibliotekar.getAdresa());
		if (bibliotekar.getPol().equals(Pol.MUSKI)) {
			radiobuttonMuski.setSelected(true);
		}
		else {
			radiobuttonZenski.setSelected(true);
		}
		txtPlata.setText(String.valueOf(bibliotekar.getPlata()));
		txtKorisnickoIme.setText(bibliotekar.getKorisnickoIme());
		pwfLozinka.setText(String.valueOf(bibliotekar.getKorisnickaSifra()));
		
	}
	
	private Object[] kreirajRedTabele(Bibliotekar bibliotekar) {
		Object[] red = new Object[11];
		
		red[0] = bibliotekar.getId();
		red[1] = bibliotekar.getIme();
		red[2] = bibliotekar.getPrezime();
		red[3] = bibliotekar.getJmbg();
		red[4] = bibliotekar.getAdresa();
		red[5] = bibliotekar.getPol();
		red[6] = bibliotekar.getPlata();
		red[7] = bibliotekar.getKorisnickoIme();
		red[8] = bibliotekar.getKorisnickaSifra();
		return red;
	}
	
	private void refresh(int red, Bibliotekar bibliotekar) {
		if(red >= 0) {
			tableModel.setValueAt(bibliotekar.getId(), red, 0);
			tableModel.setValueAt(bibliotekar.getIme(), red, 1);
			tableModel.setValueAt(bibliotekar.getPrezime(), red, 2);
			tableModel.setValueAt(bibliotekar.getJmbg(), red, 3);
			tableModel.setValueAt(bibliotekar.getAdresa(), red, 4);
			tableModel.setValueAt(bibliotekar.getPol(), red, 5);
			tableModel.setValueAt(bibliotekar.getPlata(), red, 6);
			tableModel.setValueAt(bibliotekar.getKorisnickoIme(), red, 7);
			tableModel.setValueAt(bibliotekar.getKorisnickaSifra(), red, 8);
		}
	}
	
}
