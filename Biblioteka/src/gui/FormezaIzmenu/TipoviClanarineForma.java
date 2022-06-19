package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import biblioteka.BibliotekaMain;
import biblioteka.Main;
import biblioteka.TipClanarine;
import net.miginfocom.swing.MigLayout;

public class TipoviClanarineForma extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7088521694380126021L;
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblCena = new JLabel("Cena");
	private JTextField txtCena = new JTextField(20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private TipClanarine tipClanarine;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable tipoviClanarineTabela;
	
	
	public TipoviClanarineForma(BibliotekaMain biblioteka, TipClanarine tipClanarine, DefaultTableModel tableModel, JTable tipoviClanarineTabela) {
		this.biblioteka = biblioteka;
		this.tipClanarine = tipClanarine;
		this.tableModel = tableModel;
		this.tipoviClanarineTabela = tipoviClanarineTabela;
		
		if(tipClanarine == null) {
			setTitle("Dodavanje tipa clanarine");
		} else {
			setTitle("Izmena podataka - " + tipClanarine.getId());
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
		
		if(tipClanarine != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblNaziv);
		add(txtNaziv);
		add(lblCena);
		add(txtCena);
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
					String naziv = txtNaziv.getText().trim();
					double cena = Double.parseDouble(txtCena.getText().trim());
					
					if(tipClanarine == null) {
						tipClanarine = new TipClanarine(id, naziv, cena, false);
						biblioteka.dodajTipClanarine(tipClanarine);
						Object[] red = kreirajRedTabele(tipClanarine);
						tableModel.addRow(red);
					}else {
						tipClanarine.setNaziv(naziv);
						tipClanarine.setCena(cena);
						int red = tipoviClanarineTabela.getSelectedRow();
						refresh(red, tipClanarine);
					}
					biblioteka.snimiTipClanarine(Main.TIPOVI_CLANARINE_FAJL);
					tipoviClanarineTabela.clearSelection();
					TipoviClanarineForma.this.dispose();
					TipoviClanarineForma.this.setVisible(false);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoviClanarineForma.this.dispose();
				TipoviClanarineForma.this.setVisible(false);
			}
		});
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Popravite greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(tipClanarine == null) {
			int id = Integer.parseInt(txtID.getText().trim());
			TipClanarine pronadjen = biblioteka.pronadjiTipClanarine(id);
			if(pronadjen != null) {
				poruka += "- Tip Clanarine sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Morate uneti naziv\n";
			ok = false;
		}
		try {
			Double.parseDouble(txtCena.getText().trim());
		}catch(NumberFormatException e) {
			poruka += "- Cena mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neipravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(tipClanarine.getId()));
		txtNaziv.setText(String.valueOf(tipClanarine.getNaziv()));
		txtCena.setText(String.valueOf(tipClanarine.getCena()));
	}
	
	private Object[] kreirajRedTabele(TipClanarine tipClanarine) {
		Object[] red = new Object[8];
		
		red[0] = tipClanarine.getId();
		red[1] = tipClanarine.getNaziv();
		red[2] = tipClanarine.getCena();
		return red;
	}
	
	private void refresh(int red, TipClanarine tipClanarine) {
		if(red >= 0) {
			tableModel.setValueAt(tipClanarine.getId(), red, 0);
			tableModel.setValueAt(tipClanarine.getNaziv(), red, 1);
			tableModel.setValueAt(tipClanarine.getCena(), red, 2);
		}
}

}
