package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import biblioteka.BibliotekaMain;
import biblioteka.ClanBiblioteke;
import biblioteka.Main;
import biblioteka.Pol;
import biblioteka.TipClanarine;
import net.miginfocom.swing.MigLayout;

public class Registracija extends JFrame{

	private static final long serialVersionUID = -5469244757196123605L;
	
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
	private JLabel lblBrojClanskeKarte = new JLabel("Broj clanske karte");
	private JTextField txtBrojClanskeKarte = new JTextField(20);
	private JLabel lblTipClanarine = new JLabel("Tip clanarine");
	private JComboBox<TipClanarine> comboBoxTipClanarine;
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private BibliotekaMain biblioteka;
	private ClanBiblioteke clanBiblioteke;
	
	public Registracija(BibliotekaMain biblioteka, ClanBiblioteke clanBiblioteke) {
		this.biblioteka = biblioteka;
		this.clanBiblioteke = clanBiblioteke;
		
		Collection<TipClanarine> tipoviClanarine = biblioteka.sviNeobrisaniTipoviClanarine().values();
		this.comboBoxTipClanarine = new JComboBox<TipClanarine>(tipoviClanarine.toArray(new TipClanarine[0]));
		
		setTitle("Registracija");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		ImageIcon image = new ImageIcon("src/slike/library-logo.png");
		setIconImage(image.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		initGUI();
		initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
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
		add(lblBrojClanskeKarte);
		add(txtBrojClanskeKarte);
		add(lblTipClanarine);
		add(comboBoxTipClanarine);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		grupa.add(radiobuttonMuski);
		grupa.add(radiobuttonZenski);
		radiobuttonMuski.setSelected(true);
		
	}
	
	public void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
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
					String brojClanskeKarte = txtBrojClanskeKarte.getText().trim();
					LocalDate datumPoslednjeUplate = LocalDate.parse("1111-11-11");
					int brojMeseciClanarine = 0;
					boolean jeAktivan = false;
					
					HashMap<Integer, TipClanarine> tipoviClanarine = biblioteka.sviNeobrisaniTipoviClanarine();
					TipClanarine tipClanarine = (TipClanarine)comboBoxTipClanarine.getSelectedItem();
					tipClanarine = tipoviClanarine.get(tipClanarine.getId());
					
					if(clanBiblioteke == null) {
						clanBiblioteke = new ClanBiblioteke(id, ime, prezime, jmbg, adresa, pol, false, brojClanskeKarte, datumPoslednjeUplate, brojMeseciClanarine, jeAktivan, tipClanarine);
						
						biblioteka.dodajClanaBiblioteke(clanBiblioteke);
					} else {
						clanBiblioteke.setIme(ime);
						clanBiblioteke.setPrezime(prezime);
						clanBiblioteke.setJmbg(jmbg);
						clanBiblioteke.setAdresa(adresa);
						clanBiblioteke.setPol(pol);
						clanBiblioteke.setBrClanKarte(brojClanskeKarte);
						clanBiblioteke.setDatumPoslednjeUplate(datumPoslednjeUplate);
						clanBiblioteke.setBrojMeseciClanarine(brojMeseciClanarine);
						clanBiblioteke.setAktivan(jeAktivan);
						clanBiblioteke.setTipClanarine(tipClanarine);
					}
					
					biblioteka.snimiClanoveBiblioteke(Main.CLANOVI_BIBLIOTEKE_FAJL);
					Registracija.this.dispose();
					Registracija.this.setVisible(false);
				
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Registracija.this.dispose();
				Registracija.this.setVisible(false);
				
			}
		});
	}
	
	private boolean validacija() {
		
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		} else if(clanBiblioteke == null) {
			int id = Integer.parseInt(txtID.getText().trim());
			ClanBiblioteke pronadjen = biblioteka.pronadjiClanaBiblioteke(id);
			if(pronadjen != null) {
				poruka += "- Clan sa unetim ID vec postoji\n";
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
			poruka += "- JMBG mora sadrzati 13 cifara\n";
			ok = false;
		}
		
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Morate uneti adresu\n";
			ok = false;
		}
		
		if(txtBrojClanskeKarte.getText().trim().equals("")) {
			poruka += "- Morate uneti broj clanske karte\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Uspesna registracija", "Uspesna registracija", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return ok;
	}

}
