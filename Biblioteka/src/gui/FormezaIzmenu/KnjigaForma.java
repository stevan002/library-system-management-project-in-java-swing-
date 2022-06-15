package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import biblioteka.BibliotekaMain;
import biblioteka.Jezik;
import biblioteka.Knjiga;
import biblioteka.Main;
import biblioteka.ZanrKnjige;
import net.miginfocom.swing.MigLayout;

public class KnjigaForma extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8301771566608785868L;
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblNaslov = new JLabel("Naslov");
	private JTextField txtNaslov = new JTextField(20);
	private JLabel lblOriginalniNaslov = new JLabel("Originalni Naslov");
	private JTextField txtOriginalniNaslov = new JTextField(20);
	private JLabel lblPisac = new JLabel("Pisac");
	private JTextField txtPisac = new JTextField(20);
	private JLabel lblGodinaPublikacije = new JLabel("Godina publikacije");
	private JTextField txtGodinaPublikacije = new JTextField(20);
	private JLabel lblOpis = new JLabel("Opis");
	private final JTextArea txtAreaOpis = new JTextArea(10,20);
	private JLabel lblZanr = new JLabel("Zanr");
	private JComboBox<ZanrKnjige> comboBoxZanr;
	private JLabel lblJezikOriginala = new JLabel("Jezik Originala");
	private JComboBox<Jezik> comboBoxJezikOriginala = new JComboBox<Jezik>(Jezik.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private BibliotekaMain biblioteka;
	private Knjiga knjiga;
	private JTable knjigaTabela;
	private DefaultTableModel tableModel;
	
	
	public KnjigaForma(BibliotekaMain biblioteka, Knjiga knjiga, JTable knjigaTabela, DefaultTableModel tableModel) {
		this.biblioteka = biblioteka;
		this.knjiga = knjiga;
		this.knjigaTabela = knjigaTabela;
		this.tableModel = tableModel;
		
		Collection<ZanrKnjige> zanrovi = biblioteka.sviNeobrisaniZanrovi().values();
		this.comboBoxZanr = new JComboBox<ZanrKnjige>(zanrovi.toArray(new ZanrKnjige[0]));
		
		if(knjiga == null) {
			setTitle("Dodavanje knjige");
		} else {
			setTitle("Izmena podataka - " + knjiga.getId());
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
		
		if(knjiga != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblNaslov);
		add(txtNaslov);
		add(lblOriginalniNaslov);
		add(txtOriginalniNaslov);
		add(lblPisac);
		add(txtPisac);
		add(lblGodinaPublikacije);
		add(txtGodinaPublikacije);
		add(lblOpis);
		add(txtAreaOpis);
		add(lblZanr);
		add(comboBoxZanr);
		add(lblJezikOriginala);
		add(comboBoxJezikOriginala);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
		txtAreaOpis.setLineWrap(true);
		txtAreaOpis.setWrapStyleWord(true);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(validacija()) {
					int id = Integer.parseInt(txtID.getText().trim());
					String naslov = txtNaslov.getText().trim();
					String originalniNaslov = txtOriginalniNaslov.getText().trim();
					String pisac = txtPisac.getText().trim();
					int godinaPublikacije = Integer.parseInt(txtGodinaPublikacije.getText().trim());
					String opis = txtAreaOpis.getText().trim();
					HashMap<Integer, ZanrKnjige> zanrovi = biblioteka.sviNeobrisaniZanrovi();
					ZanrKnjige zanr = (ZanrKnjige)comboBoxZanr.getSelectedItem();
					zanr = zanrovi.get(zanr.getId());
					Jezik jezikOriginala = (Jezik)comboBoxJezikOriginala.getSelectedItem();
					
					if(knjiga == null) {
						knjiga = new Knjiga(id, naslov, originalniNaslov, pisac, godinaPublikacije, opis, zanr, jezikOriginala, false);
						biblioteka.dodajKnjigu(knjiga);
						
						Object[] red = kreirajRedTabele(knjiga);
						tableModel.addRow(red);
					}
					else {
						knjiga.setNaslovKnjige(naslov);
						knjiga.setOriginalniNaslovKnjige(originalniNaslov);
						knjiga.setPisac(pisac);
						knjiga.setGodinaPublikacije(godinaPublikacije);
						knjiga.setOpis(opis);
						knjiga.setZanr(zanr);
						knjiga.setJezikOriginala(jezikOriginala);
						
						int red = knjigaTabela.getSelectedRow();
						refresh(red, knjiga);
						
					}
					biblioteka.snimiKnjige(Main.KNJIGE_FAJL);
					knjigaTabela.clearSelection();
					KnjigaForma.this.dispose();
					KnjigaForma.this.setVisible(false);
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				KnjigaForma.this.dispose();
				KnjigaForma.this.setVisible(false);
				
			}
		});
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}
		else if(knjiga == null){
			int id = Integer.parseInt(txtID.getText().trim());
			Knjiga pronadjena = biblioteka.pronadjiKnjigu(id);
			if(pronadjena != null) {
				poruka += "- Knjiga sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtNaslov.getText().trim().equals("")) {
			poruka += "- Morate uneti naslov knjige\n";
			ok = false;
		}
		if(txtOriginalniNaslov.getText().trim().equals("")) {
			poruka += "- Morate uneti originalni naslov\n";
			ok = false;
		}
		
		if(txtPisac.getText().trim().equals("")) {
			poruka += "- Morate uneti pisca\n";
			ok = false;
		}
		
		if(txtGodinaPublikacije.getText().equals("")) {
			poruka += "- Morate uneti godinu objavljivanja\n";
			ok = false;
		} else {
			try {
				Integer.parseInt(txtGodinaPublikacije.getText().trim());
			} catch (NumberFormatException e) {
				poruka += "- Godina objavljivanja mora biti broj\n";
				ok = false;
			}
		}
		if(txtAreaOpis.getText().trim().equals("")) {
			poruka += "- Morate uneti opis\n";
			ok = false;
		}
		
		if(comboBoxZanr.getSelectedItem().equals("")) {
			poruka += "- Morate izabrati zanr\n";
			ok = false;
		}
		
		if(comboBoxJezikOriginala.getSelectedItem().equals("")) {
			poruka += "- Morate izabrati jezik originala\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(knjiga.getId()));
		txtNaslov.setText(String.valueOf(knjiga.getNaslovKnjige()));
		txtOriginalniNaslov.setText(String.valueOf(knjiga.getOriginalniNaslovKnjige()));
		txtPisac.setText(String.valueOf(knjiga.getPisac()));
		txtGodinaPublikacije.setText(String.valueOf(knjiga.getGodinaPublikacije()));
		txtAreaOpis.setText(knjiga.getOpis());
		comboBoxZanr.setSelectedItem(knjiga.getZanr());
		comboBoxJezikOriginala.setSelectedItem(knjiga.getJezikOriginala());
	}
	
	private Object[] kreirajRedTabele(Knjiga knjiga) {
		Object[] red = new Object[8];
		
		red[0] = knjiga.getId();
		red[1] = knjiga.getNaslovKnjige();
		red[2] = knjiga.getOriginalniNaslovKnjige();
		red[3] = knjiga.getPisac();
		red[4] = knjiga.getGodinaPublikacije();
		red[5] = knjiga.getOpis();
		red[6] = knjiga.getZanr().getOznaka();
		red[7] = knjiga.getJezikOriginala();
		
		return red;
	}
	
	private void refresh(int red, Knjiga knjiga) {
		if (red >= 0) {
			tableModel.setValueAt(knjiga.getId(), red, 0);
			tableModel.setValueAt(knjiga.getNaslovKnjige(), red, 1);
			tableModel.setValueAt(knjiga.getOriginalniNaslovKnjige(), red, 2);
			tableModel.setValueAt(knjiga.getPisac(), red, 3);
			tableModel.setValueAt(knjiga.getGodinaPublikacije(), red, 4);
			tableModel.setValueAt(knjiga.getOpis(), red, 5);
			tableModel.setValueAt(knjiga.getZanr().getOznaka(), red, 6);
			tableModel.setValueAt(knjiga.getJezikOriginala(), red, 7);
		}
	}
}
	