package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import biblioteka.BibliotekaMain;
import biblioteka.ClanBiblioteke;
import biblioteka.Main;
import biblioteka.Pol;
import biblioteka.TipClanarine;
import net.miginfocom.swing.MigLayout;

public class ClanoviBibliotekeForma extends JFrame{

	private static final long serialVersionUID = 1568433882357121968L;
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
	private JLabel lblDatumPoslednjeUplate = new JLabel("Datum poslednje uplate");
	private JTextField txtDatumPoslednjeUplate = new JTextField(20);
	private JLabel lblUnapredUplacenoMeseci = new JLabel("Unapred uplaceno meseci");
	private JTextField txtUnapredUplacenoMeseci = new JTextField(20);
	private JLabel lblAktivan = new JLabel("Da li je clan aktivan?");
	private JCheckBox cbAktivan = new JCheckBox("Aktivan");
	private JLabel lblTipClanarine = new JLabel("Tip clanarine");
	private JComboBox<TipClanarine> comboBoxTipClanarine;
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private ClanBiblioteke clanBiblioteke;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable clanoviBibliotekeTabela;
	
	public ClanoviBibliotekeForma(BibliotekaMain biblioteka, ClanBiblioteke clanBiblioteke, DefaultTableModel tableModel, JTable clanoviBibliotekeTabela) {
		this.biblioteka = biblioteka;
		this.clanBiblioteke = clanBiblioteke;
		this.tableModel = tableModel;
		this.clanoviBibliotekeTabela = clanoviBibliotekeTabela;
		
		Collection<TipClanarine> tipoviClanarine = biblioteka.sviNeobrisaniTipoviClanarine().values();
		this.comboBoxTipClanarine = new JComboBox<TipClanarine>(tipoviClanarine.toArray(new TipClanarine[0]));
		
		if(clanBiblioteke == null) {
			setTitle("Dodavanje clana biblioteke");
		} else {
			setTitle("Izmena podataka - " + clanBiblioteke.getId());
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
		
		if(clanBiblioteke != null) {
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
		add(lblBrojClanskeKarte);
		add(txtBrojClanskeKarte);
		add(lblDatumPoslednjeUplate);
		add(txtDatumPoslednjeUplate);
		add(lblUnapredUplacenoMeseci);
		add(txtUnapredUplacenoMeseci);
		add(lblAktivan);
		add(cbAktivan);
		add(lblTipClanarine);
		add(comboBoxTipClanarine);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
		grupa.add(radiobuttonMuski);
		grupa.add(radiobuttonZenski);
		radiobuttonMuski.setSelected(true);

		txtDatumPoslednjeUplate.setEditable(false);
		txtUnapredUplacenoMeseci.setEditable(false);
		cbAktivan.setEnabled(false);
		
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
					String brojClanskeKarte = txtBrojClanskeKarte.getText().trim();
					LocalDate datumPoslednjeUplate = LocalDate.parse("1111-11-11");
					int brojMeseciClanarine = 0;
					boolean jeAktivan = false;
					if(cbAktivan.isSelected()) {
						jeAktivan = true;
					}
					
					HashMap<Integer, TipClanarine> tipoviClanarine = biblioteka.sviNeobrisaniTipoviClanarine();
					TipClanarine tipClanarine = (TipClanarine)comboBoxTipClanarine.getSelectedItem();
					tipClanarine = tipoviClanarine.get(tipClanarine.getId());
					
					if(clanBiblioteke == null) {
						clanBiblioteke = new ClanBiblioteke(id, ime, prezime, jmbg, adresa, pol, false, brojClanskeKarte, datumPoslednjeUplate, brojMeseciClanarine, jeAktivan, tipClanarine);
						
						biblioteka.dodajClanaBiblioteke(clanBiblioteke);
						Object[] red = kreirajRedTabele(clanBiblioteke);
						tableModel.addRow(red);
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
						
						int red = clanoviBibliotekeTabela.getSelectedRow();
						refresh(red, clanBiblioteke);
					}
					
					biblioteka.snimiClanoveBiblioteke(Main.CLANOVI_BIBLIOTEKE_FAJL);
					clanoviBibliotekeTabela.clearSelection();
					ClanoviBibliotekeForma.this.dispose();
					ClanoviBibliotekeForma.this.setVisible(false);
				
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				ClanoviBibliotekeForma.this.dispose();
				ClanoviBibliotekeForma.this.setVisible(false);
				
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
		}
		
		return ok;
	}
	
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(clanBiblioteke.getId()));
		txtIme.setText(clanBiblioteke.getIme());
		txtPrezime.setText(clanBiblioteke.getPrezime());
		txtJMBG.setText(clanBiblioteke.getJmbg());
		txtAdresa.setText(clanBiblioteke.getAdresa());
		if (clanBiblioteke.getPol().equals(Pol.MUSKI)) {
			radiobuttonMuski.setSelected(true);
		}
		else {
			radiobuttonZenski.setSelected(true);
		}
		txtBrojClanskeKarte.setText(clanBiblioteke.getBrClanKarte());
		txtDatumPoslednjeUplate.setText(String.valueOf(clanBiblioteke.getDatumPoslednjeUplate()));
		txtUnapredUplacenoMeseci.setText(String.valueOf(clanBiblioteke.getBrojMeseciClanarine()));
		if(clanBiblioteke.isAktivan()) {
			cbAktivan.setSelected(true);
		} else {
			cbAktivan.setSelected(false);
		}
		comboBoxTipClanarine.setSelectedItem(clanBiblioteke.getTipClanarine());
		
	}
	
	private Object[] kreirajRedTabele(ClanBiblioteke clanBiblioteke) {
		Object[] red = new Object[11];
		
		red[0] = clanBiblioteke.getId();
		red[1] = clanBiblioteke.getIme();
		red[2] = clanBiblioteke.getPrezime();
		red[3] = clanBiblioteke.getJmbg();
		red[4] = clanBiblioteke.getAdresa();
		red[5] = clanBiblioteke.getPol();
		red[6] = clanBiblioteke.getBrClanKarte();
		red[7] = clanBiblioteke.getDatumPoslednjeUplate();
		red[8] = clanBiblioteke.getBrojMeseciClanarine();
		if (clanBiblioteke.isAktivan()) {
			red[9] = "Da";
		} else {
			red[9] = "Ne";
		}
		red[10] = clanBiblioteke.getTipClanarine().getNaziv();
		return red;
	}
	
	
	private void refresh(int red, ClanBiblioteke clanBiblioteke) {
		if(red >= 0) {
			tableModel.setValueAt(clanBiblioteke.getId(), red, 0);
			tableModel.setValueAt(clanBiblioteke.getIme(), red, 1);
			tableModel.setValueAt(clanBiblioteke.getPrezime(), red, 2);
			tableModel.setValueAt(clanBiblioteke.getJmbg(), red, 3);
			tableModel.setValueAt(clanBiblioteke.getAdresa(), red, 4);
			tableModel.setValueAt(clanBiblioteke.getPol(), red, 5);
			tableModel.setValueAt(clanBiblioteke.getBrClanKarte(), red, 6);
			tableModel.setValueAt(clanBiblioteke.getDatumPoslednjeUplate(), red, 7);
			tableModel.setValueAt(clanBiblioteke.getBrojMeseciClanarine(), red, 8);
			if(clanBiblioteke.isAktivan()) {
				tableModel.setValueAt("Da", red, 9);
			} else {
				tableModel.setValueAt("Ne", red, 9);
			}
			tableModel.setValueAt(clanBiblioteke.getTipClanarine().getNaziv(), red, 10);
		}
	}
}