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

import biblioteka.Administrator;
import biblioteka.BibliotekaMain;
import biblioteka.Main;
import biblioteka.Pol;
import net.miginfocom.swing.MigLayout;

public class AdministratoriForma extends JFrame{
	private static final long serialVersionUID = -1427817610792925523L;
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

	private Administrator administrator;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable administratoriTabela;
	
	public AdministratoriForma(BibliotekaMain biblioteka, Administrator administrator, DefaultTableModel tableModel, JTable administratoriTabela) {
		this.biblioteka = biblioteka;
		this.administrator = administrator;
		this.tableModel = tableModel;
		this.administratoriTabela = administratoriTabela;
		
		if(administrator == null) {
			setTitle("Dodavanje administratora");
		}else {
			setTitle("Izmena podataka - " + administrator.getId());
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][][][][]20[]");
		setLayout(layout);
		
		if(administrator != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
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
					
					if(administrator == null) {
						administrator = new Administrator(id, ime, prezime, jmbg, adresa, pol, false, korisnickoIme, lozinka, plata);
						
						biblioteka.dodajAdministratora(administrator);
						Object[] red = kreirajRedTabele(administrator);
						tableModel.addRow(red);
					} else {
						administrator.setIme(ime);
						administrator.setPrezime(prezime);
						administrator.setJmbg(jmbg);
						administrator.setAdresa(adresa);
						administrator.setPol(pol);
						administrator.setPlata(plata);
						administrator.setKorisnickoIme(korisnickoIme);
						administrator.setKorisnickaSifra(lozinka);
						
						int red = administratoriTabela.getSelectedRow();
						refresh(red, administrator);
					}
					
					biblioteka.snimiAdministratore(Main.ADMINISTRATORI_FAJL);
					administratoriTabela.clearSelection();
					AdministratoriForma.this.dispose();
					AdministratoriForma.this.setVisible(false);
					
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdministratoriForma.this.dispose();
				AdministratoriForma.this.setVisible(false);
			}
		});
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popraviti greske pri unosu";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "Morate uneti ID\n";
			ok = false;
		}else if(administrator == null){
			int id = Integer.parseInt(txtID.getText().trim());
			Administrator pronadjen = biblioteka.pronadjiAdministratora(id);
			if(pronadjen != null) {
				poruka += "- Morate uneti ime\n";
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
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(administrator.getId()));
		txtIme.setText(administrator.getIme());
		txtPrezime.setText(administrator.getPrezime());
		txtJMBG.setText(administrator.getJmbg());
		txtAdresa.setText(administrator.getAdresa());
		if(administrator.getPol().equals(Pol.MUSKI)) {
			radiobuttonMuski.setSelected(true);
		}else {
			radiobuttonZenski.setSelected(true);
		}
		txtPlata.setText(String.valueOf(administrator.getPlata()));
		txtKorisnickoIme.setText(administrator.getKorisnickoIme());
		pwfLozinka.setText(String.valueOf(administrator.getKorisnickaSifra()));
	}
	
	private Object[] kreirajRedTabele(Administrator administrator) {
		Object[] red = new Object[11];
		
		red[0] = administrator.getId();
		red[1] = administrator.getIme();
		red[2] = administrator.getPrezime();
		red[3] = administrator.getJmbg();
		red[4] = administrator.getAdresa();
		red[5] = administrator.getPol();
		red[6] = administrator.getPlata();
		red[7] = administrator.getKorisnickoIme();
		red[8] = administrator.getKorisnickaSifra();
		return red;
	}
	
	private void refresh(int red, Administrator administrator) {
		if(red >= 0) {
			tableModel.setValueAt(administrator.getId(), red, 0);
			tableModel.setValueAt(administrator.getIme(), red, 1);
			tableModel.setValueAt(administrator.getPrezime(), red, 2);
			tableModel.setValueAt(administrator.getJmbg(), red, 3);
			tableModel.setValueAt(administrator.getAdresa(), red, 4);
			tableModel.setValueAt(administrator.getPol(), red, 5);
			tableModel.setValueAt(administrator.getPlata(), red, 6);
			tableModel.setValueAt(administrator.getKorisnickoIme(), red, 7);
			tableModel.setValueAt(administrator.getKorisnickaSifra(), red, 8);
		}
	}
}
