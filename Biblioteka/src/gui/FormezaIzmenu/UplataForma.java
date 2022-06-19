package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import biblioteka.BibliotekaMain;
import biblioteka.ClanBiblioteke;
import biblioteka.Main;
import net.miginfocom.swing.MigLayout;

public class UplataForma extends JFrame{

	private static final long serialVersionUID = -6607499317326257901L;
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblDatumPoslednjeUplate = new JLabel("Datum poslednje uplate");
	private JTextField txtDatumPoslednjeUplate = new JTextField(20);
	private JLabel lblUnapredUplacenoMeseci = new JLabel("Broj meseci za uplatu");
	private JTextField txtUnapredUplacenoMeseci = new JTextField(20);
	
	private JButton btnUplati = new JButton("Uplati");
	private JButton btnCancel = new JButton("Ponisti");
	
	private ClanBiblioteke clanBiblioteke;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable clanoviBibliotekeTabela;
	
	public UplataForma(BibliotekaMain biblioteka, ClanBiblioteke clanBiblioteke, DefaultTableModel tableModel, JTable clanoviBibliotekeTabela) {
		this.biblioteka = biblioteka;
		this.clanBiblioteke = clanBiblioteke;
		this.tableModel = tableModel;
		this.clanoviBibliotekeTabela = clanoviBibliotekeTabela;
		
			setTitle("Uplata clanarine - " + clanBiblioteke.getId());
		
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
			txtIme.setEditable(false);
			txtPrezime.setEditable(false);
			txtDatumPoslednjeUplate.setEditable(false);
			
		}
		
		add(lblID);
		add(txtID);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblDatumPoslednjeUplate);
		add(txtDatumPoslednjeUplate);
		add(lblUnapredUplacenoMeseci);
		add(txtUnapredUplacenoMeseci);
		add(new JLabel());
		add(btnUplati, "split 2");
		add(btnCancel);
		
	}
	
	private void initActions() {
		btnUplati.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(validacija()) {
					int id = Integer.parseInt(txtID.getText().trim());		
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					LocalDate datumPoslednjeUplate = LocalDate.now();
					int brojMeseciClanarine = Integer.parseInt(txtUnapredUplacenoMeseci.getText().trim());
				
		
					clanBiblioteke.setIme(ime);
					clanBiblioteke.setPrezime(prezime);
					clanBiblioteke.setDatumPoslednjeUplate(datumPoslednjeUplate);
					clanBiblioteke.setBrojMeseciClanarine(brojMeseciClanarine);
					clanBiblioteke.setAktivan(true);
						
					int red = clanoviBibliotekeTabela.getSelectedRow();
					refresh(red, clanBiblioteke);
				
					biblioteka.snimiClanoveBiblioteke(Main.CLANOVI_BIBLIOTEKE_FAJL);
					clanoviBibliotekeTabela.clearSelection();
					UplataForma.this.dispose();
					UplataForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UplataForma.this.dispose();
				UplataForma.this.setVisible(false);
				
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
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(clanBiblioteke.getId()));
		txtIme.setText(clanBiblioteke.getIme());
		txtPrezime.setText(clanBiblioteke.getPrezime());
		txtDatumPoslednjeUplate.setText(String.valueOf(clanBiblioteke.getDatumPoslednjeUplate()));
		txtUnapredUplacenoMeseci.setText(String.valueOf(clanBiblioteke.getBrojMeseciClanarine()));
		
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
			tableModel.setValueAt("Da", red, 9);
			tableModel.setValueAt(clanBiblioteke.getTipClanarine().getNaziv(), red, 10);
		}
	}
	
}
