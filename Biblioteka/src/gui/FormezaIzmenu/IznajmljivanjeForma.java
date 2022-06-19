package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import biblioteka.BibliotekaMain;
import biblioteka.ClanBiblioteke;
import biblioteka.Iznajmljivanje;
import biblioteka.Main;
import biblioteka.PrimerakKnjige;
import biblioteka.Zaposleni;
import net.miginfocom.swing.MigLayout;

public class IznajmljivanjeForma extends JFrame{

	private static final long serialVersionUID = -8378888668918109965L;
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblDatumIznajmljivanja = new JLabel("Datum iznajmljivanja");
	private JTextField txtDatumIznajmljivanja = new JTextField(20);
	private JLabel lblDatumVracanja = new JLabel("Datum vracanja");
	private JTextField txtDatumVracanja = new JTextField(20);
	private JLabel lblPrimerci = new JLabel("Primerci");
	private JList<PrimerakKnjige> listaPrimeraka;
	private JLabel lblZaposleni = new JLabel("Zaposleni");
	private JComboBox<Zaposleni> comboBoxZaposleni;
	private JLabel lblClan = new JLabel("Clan");
	private JComboBox<ClanBiblioteke> comboBoxClanBiblioteke;
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Iznajmljivanje iznajmljivanje;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable iznajmljivanjeTabela;
	
	
	public IznajmljivanjeForma(BibliotekaMain biblioteka, Iznajmljivanje iznajmljivanje, DefaultTableModel tableModel, JTable iznajmljivanjeTabela) {
		this.biblioteka = biblioteka;
		this.iznajmljivanje = iznajmljivanje;
		this.tableModel = tableModel;
		this.iznajmljivanjeTabela = iznajmljivanjeTabela;
		
		Collection<PrimerakKnjige> primerci = biblioteka.sviNeobrisaniPrimerciKnjige().values();
		this.listaPrimeraka = new JList<PrimerakKnjige>(primerci.toArray(new PrimerakKnjige[0]));
		listaPrimeraka.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		Collection<Zaposleni> zaposleni = biblioteka.sviNeobrisaniZaposleni().values();
		this.comboBoxZaposleni = new JComboBox<Zaposleni>(zaposleni.toArray(new Zaposleni[0]));
		
		Collection<ClanBiblioteke> clanovi = biblioteka.sviNeobrisaniClanoviBiblioteke().values();
		this.comboBoxClanBiblioteke = new JComboBox<ClanBiblioteke>(clanovi.toArray(new ClanBiblioteke[0]));
		
		if(iznajmljivanje == null) {
			setTitle("Dodavanje iznajmljivanja");
		} else {
			setTitle("Izmena podataka - " + iznajmljivanje.getId());
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
		
		if(iznajmljivanje != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblDatumIznajmljivanja);
		add(txtDatumIznajmljivanja);
		add(lblDatumVracanja);
		add(txtDatumVracanja);
		add(lblPrimerci);
		add(listaPrimeraka);
		add(lblZaposleni);
		add(comboBoxZaposleni);
		add(lblClan);
		add(comboBoxClanBiblioteke);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					int id = Integer.parseInt(txtID.getText().trim());
					LocalDate datumIznajmljivanja = LocalDate.parse(txtDatumIznajmljivanja.getText().trim());
					LocalDate datumVracanja = LocalDate.parse(txtDatumVracanja.getText().trim());
					
					HashMap<Integer, PrimerakKnjige> primerci = biblioteka.sviNeobrisaniPrimerciKnjige();
					
					List<PrimerakKnjige> selectedPrimerci = listaPrimeraka.getSelectedValuesList();
					for(PrimerakKnjige selectedPrimerak : selectedPrimerci) {
						PrimerakKnjige primerak = primerci.get(selectedPrimerak.getId());
						selectedPrimerak.setBrStrana(primerak.getBrStrana());
						selectedPrimerak.setGodinaStampanja(primerak.getGodinaStampanja());
						selectedPrimerak.setIznajmljena(primerak.getIznajmljena());
						selectedPrimerak.setJezikStampanja(primerak.getJezikStampanja());
						selectedPrimerak.setKnjiga(primerak.getKnjiga());
						selectedPrimerak.setObrisan(primerak.isObrisan());
						selectedPrimerak.setTipPoveza(primerak.getTipPoveza());
				}
					
					HashMap<Integer, Zaposleni> sviZaposleni = biblioteka.sviNeobrisaniZaposleni();
					Zaposleni zaposleni = (Zaposleni)comboBoxZaposleni.getSelectedItem();
					zaposleni = sviZaposleni.get(zaposleni.getId());
					
					HashMap<Integer, ClanBiblioteke> clanovi = biblioteka.sviNeobrisaniClanoviBiblioteke();
					ClanBiblioteke clan = (ClanBiblioteke)comboBoxClanBiblioteke.getSelectedItem();
					clan = clanovi.get(clan.getId());
					
					if(iznajmljivanje == null) {
						iznajmljivanje = new Iznajmljivanje(id, datumIznajmljivanja, datumVracanja, zaposleni, clan, false);
						iznajmljivanje.setPrimerci(selectedPrimerci);
						
						biblioteka.dodajIznajmljivanje(iznajmljivanje);
						Object[] red = kreirajRedTabele(iznajmljivanje);
						tableModel.addRow(red);
					} else {
						iznajmljivanje.setDatumIznajmljivanja(datumIznajmljivanja);
						iznajmljivanje.setDatumVracanja(datumVracanja);
						iznajmljivanje.setPrimerci(selectedPrimerci);
						iznajmljivanje.setZaposleni(zaposleni);
						iznajmljivanje.setClan(clan);
						
						int red = iznajmljivanjeTabela.getSelectedRow();
						refresh(red, iznajmljivanje);
						
					}
					
					biblioteka.snimiIznajmljivanja(Main.IZNAJMLJIVANJA_FAJL);
					iznajmljivanjeTabela.clearSelection();
					IznajmljivanjeForma.this.dispose();
					IznajmljivanjeForma.this.setVisible(false);
			}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmljivanjeForma.this.dispose();
				IznajmljivanjeForma.this.setVisible(false);
				
			}
		});
	}
		
		private boolean validacija() {
			boolean ok = true;
			String poruka = "Molimo popravite greske u unosu:\n";
			
			if(txtID.getText().trim().equals("")) {
				poruka += "- Morate uneti ID\n";
				ok = false;
			} else if(iznajmljivanje == null) {
				int id = Integer.parseInt(txtID.getText().trim());
				Iznajmljivanje pronadjen = biblioteka.pronadjiIznajmljivanje(id);
				if(pronadjen != null) {
					poruka += "- Iznajmljivanje sa unetim ID vec postoji\n";
					ok = false;
				}
			}
			
			if(txtDatumIznajmljivanja.getText().trim().equals("")) {
				poruka += "- Morate uneti datum iznajmljivanja\n";
				ok = false;
			}
			
			if(txtDatumVracanja.getText().trim().equals("")) {
				poruka += "- Morate uneti datum vracanja\n";
				ok = false;
			}
			
			if(ok == false) {
				JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
			}
			
			return ok;
		}
		
		
		private void popuniPolja() {
			txtID.setText(String.valueOf(iznajmljivanje.getId()));
			txtDatumIznajmljivanja.setText(String.valueOf(iznajmljivanje.getDatumIznajmljivanja()));
			txtDatumVracanja.setText(String.valueOf(iznajmljivanje.getDatumVracanja()));
		}
		
		private Object[] kreirajRedTabele(Iznajmljivanje iznajmljivanje) {
			Object[] red = new Object[6];
			
			red[0] = iznajmljivanje.getId();
			red[1] = iznajmljivanje.getDatumIznajmljivanja();
			red[2] = iznajmljivanje.getDatumVracanja();
			red[3] = getPrimerciString(iznajmljivanje);
			red[4] = iznajmljivanje.getZaposleni().getIme() + " " + iznajmljivanje.getZaposleni().getPrezime();
			red[5] = iznajmljivanje.getClan().getIme() + " " + iznajmljivanje.getClan().getPrezime();
			
			return red;
		}
		
		private String getPrimerciString(Iznajmljivanje iznajmljivanje) {
			String txtPrimerci = "";
			for (PrimerakKnjige primerak : iznajmljivanje.getPrimerci()) {
				txtPrimerci += primerak.getKnjiga().getNaslovKnjige() + "; ";
			}
			
			return txtPrimerci;
		}
		
		private void refresh(int red, Iznajmljivanje iznajmljivanje) {
			if(red >= 0) {
				tableModel.setValueAt(iznajmljivanje.getId(), red, 0);
				tableModel.setValueAt(iznajmljivanje.getDatumIznajmljivanja(), red, 1);
				tableModel.setValueAt(iznajmljivanje.getDatumVracanja(), red, 2);
				tableModel.setValueAt(getPrimerciString(iznajmljivanje), red, 3);
				tableModel.setValueAt(iznajmljivanje.getZaposleni().getIme() + " " + iznajmljivanje.getZaposleni().getPrezime(), red, 4);	
				tableModel.setValueAt(iznajmljivanje.getClan().getIme() + " " + iznajmljivanje.getClan().getPrezime(), red, 5);
				
			}
	}
	
	
}
