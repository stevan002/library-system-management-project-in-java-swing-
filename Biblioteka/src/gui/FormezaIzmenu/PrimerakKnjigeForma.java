package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import biblioteka.Jezik;
import biblioteka.Knjiga;
import biblioteka.Main;
import biblioteka.PrimerakKnjige;
import biblioteka.TipPoveza;
import net.miginfocom.swing.MigLayout;

public class PrimerakKnjigeForma extends JFrame{

	private static final long serialVersionUID = 6040268380249334354L;
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblBrojStrana = new JLabel("Broj strana");
	private JTextField txtBrojStrana = new JTextField(20);
	private JLabel lblGodinaStampanja = new JLabel("Godina stampanja");
	private JTextField txtGodinaStampanja = new JTextField(20);
	
	private JLabel lblIznajmljena = new JLabel("Iznajmljena");
	private JCheckBox cbIznajmljena = new JCheckBox("Iznajmljena");
	
	private JLabel lblKnjiga = new JLabel("Knjiga");
	private JComboBox<Knjiga> comboBoxKnjiga;
	
	private JLabel lblTipPoveza = new JLabel("Tip poveza");
	private JRadioButton mekiPovezButton = new JRadioButton("MEK");
	private JRadioButton tvrdiPovezButton = new JRadioButton("TVRD");
	private ButtonGroup grupa = new ButtonGroup();
	
	private JLabel lblJezikStampanja = new JLabel("Jezik stampanja");
	
	private JComboBox<Jezik> comboBoxJezikStampanja = new JComboBox<Jezik>(Jezik.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PrimerakKnjige primerakKnjige;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable primerciKnjigeTabela;
	
	
	public PrimerakKnjigeForma(BibliotekaMain biblioteka, PrimerakKnjige primerakKnjige, DefaultTableModel tableModel, JTable primerciKnjigeTabela) {
		this.biblioteka = biblioteka;
		this.primerakKnjige = primerakKnjige;
		this.tableModel = tableModel;
		this.primerciKnjigeTabela = primerciKnjigeTabela;
		
		Collection<Knjiga> knjige = biblioteka.sveNeobrisaneKnjige().values();
		this.comboBoxKnjiga = new JComboBox<Knjiga>(knjige.toArray(new Knjiga[0]));
		if(primerakKnjige == null) {
			setTitle("Dodavanje primerka knjige");
		} else {
			setTitle("Izmena podataka - " + primerakKnjige.getId());
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
		
		if(primerakKnjige != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblBrojStrana);
		add(txtBrojStrana);
		add(lblGodinaStampanja);
		add(txtGodinaStampanja);
		add(lblIznajmljena);
		add(cbIznajmljena);
		add(lblKnjiga);
		add(comboBoxKnjiga);
		add(lblTipPoveza);
		add(mekiPovezButton, "split 2");
		add(tvrdiPovezButton);
		add(lblJezikStampanja);
		add(comboBoxJezikStampanja);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
		grupa.add(mekiPovezButton);
		grupa.add(tvrdiPovezButton);
		
	}
private void initActions() {
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					int id = Integer.parseInt(txtID.getText().trim());
					int brojStrana = Integer.parseInt(txtBrojStrana.getText().trim());
					int godinaStampanja = Integer.parseInt(txtGodinaStampanja.getText().trim());
					boolean iznajmljena = false;
					if (cbIznajmljena.isSelected()) {
						iznajmljena = true;
					}
					
					HashMap<Integer, Knjiga> knjige = biblioteka.sveNeobrisaneKnjige();
					
					Knjiga knjiga = (Knjiga)comboBoxKnjiga.getSelectedItem();
					knjiga = knjige.get(knjiga.getId());
					
					TipPoveza tipPoveza;
					
					if (mekiPovezButton.isSelected()) {
						tipPoveza = TipPoveza.valueOf(mekiPovezButton.getText().trim());
					}
					
					else {
						tipPoveza = TipPoveza.valueOf(tvrdiPovezButton.getText().trim());
					}
					
					Jezik jezikStampanja = (Jezik)comboBoxJezikStampanja.getSelectedItem();
					
					if(primerakKnjige == null) {
						primerakKnjige = new PrimerakKnjige(id, brojStrana, godinaStampanja, iznajmljena, knjiga, tipPoveza, jezikStampanja, false);
						
						biblioteka.dodajPrimerakKnjige(primerakKnjige);
						knjiga.getSviPrimerci().add(primerakKnjige);
					
						
						Object[] red = kreirajRedTabele(primerakKnjige);
						tableModel.addRow(red);
					} else {
						primerakKnjige.setBrStrana(brojStrana);
						primerakKnjige.setGodinaStampanja(godinaStampanja);
						primerakKnjige.setIznajmljena(iznajmljena);
						primerakKnjige.setKnjiga(knjiga);
						if(mekiPovezButton.isSelected()) {
							mekiPovezButton.setSelected(true);
						} else {
							tvrdiPovezButton.setSelected(true);
						}
						primerakKnjige.setTipPoveza(tipPoveza);
						primerakKnjige.setJezikStampanja(jezikStampanja);
						int red = primerciKnjigeTabela.getSelectedRow();
						refresh(red, primerakKnjige);
					}
					
					biblioteka.snimiPrimerkeKnjige(Main.PRIMERCI_KNJIGE_FAJL);
					primerciKnjigeTabela.clearSelection();
					PrimerakKnjigeForma.this.dispose();
					PrimerakKnjigeForma.this.setVisible(false);
					
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerakKnjigeForma.this.dispose();
				PrimerakKnjigeForma.this.setVisible(false);
				
			}
		});
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
	
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		} else if(primerakKnjige == null) {
			int id = Integer.parseInt(txtID.getText().trim());
			PrimerakKnjige pronadjen = biblioteka.pronadjiPrimerak(id);
			if(pronadjen != null) {
				poruka += "- Primerak knjige sa unetim ID vec postoji\n";
				ok = false;
			}
		}
	
		try {
			Integer.parseInt(txtBrojStrana.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Broj strana mora biti broj\n";
			ok = false;
		}
	
		try {
			Integer.parseInt(txtGodinaStampanja.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Godina stampanja mora biti broj\n";
			ok = false;
		}
	
		if(comboBoxKnjiga.getSelectedItem().equals("")) {
			poruka += "- Morate izabrati naslov knjige\n";
			ok = false;
		}
	
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
	
		return ok;
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(primerakKnjige.getId()));
		txtBrojStrana.setText(String.valueOf(primerakKnjige.getBrStrana()));
		txtGodinaStampanja.setText(String.valueOf(primerakKnjige.getGodinaStampanja()));
		if(primerakKnjige.getIznajmljena()) {
			cbIznajmljena.setSelected(true);
		} else {
			cbIznajmljena.setSelected(false);
		}
		comboBoxKnjiga.setSelectedItem(primerakKnjige.getKnjiga());
		if (primerakKnjige.getTipPoveza().equals(TipPoveza.MEK)) {
			mekiPovezButton.setSelected(true);
		} else {
			tvrdiPovezButton.setSelected(true);
		}
		comboBoxJezikStampanja.setSelectedItem(primerakKnjige.getJezikStampanja());
	}
	
	private Object[] kreirajRedTabele(PrimerakKnjige primerakKnjige) {
		Object[] red = new Object[7];
		
		red[0] = primerakKnjige.getId();
		red[1] = primerakKnjige.getBrStrana();
		red[2] = primerakKnjige.getGodinaStampanja();
		if(primerakKnjige.getIznajmljena()) {
			red[3] = "Da";
		} else {
			red[3] = "Ne";
		}
		
		red[4] = primerakKnjige.getKnjiga().getNaslovKnjige();
		red[5] = primerakKnjige.getTipPoveza();
		red[6] = primerakKnjige.getJezikStampanja();
		return red;
	}
	
	private void refresh(int red, PrimerakKnjige primerakKnjige) {
		if(red >= 0) {
			tableModel.setValueAt(primerakKnjige.getId(), red, 0);
			tableModel.setValueAt(primerakKnjige.getBrStrana(), red, 1);
			tableModel.setValueAt(primerakKnjige.getGodinaStampanja(), red, 2);
			if(primerakKnjige.getIznajmljena()) {
				tableModel.setValueAt("Da", red, 3);
			} else {
				tableModel.setValueAt("Ne", red, 3);
			}
			tableModel.setValueAt(primerakKnjige.getKnjiga().getNaslovKnjige(), red, 4);
			tableModel.setValueAt(primerakKnjige.getTipPoveza(), red, 5);
			tableModel.setValueAt(primerakKnjige.getJezikStampanja(), red, 6);
		}
	}
}
