package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import biblioteka.BibliotekaMain;
import biblioteka.Main;
import biblioteka.ZanrKnjige;
import net.miginfocom.swing.MigLayout;

public class ZanroviForma extends JFrame{

	private static final long serialVersionUID = -8323863065490784362L;
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblOznaka = new JLabel("Oznaka");
	private JTextField txtOznaka = new JTextField(20);
	private JLabel lblOpis = new JLabel("Opis");
	private final JTextArea txtAreaOpis = new JTextArea(10,20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private ZanrKnjige zanr;
	private BibliotekaMain biblioteka;
	private DefaultTableModel tableModel;
	private JTable zanroviTabela;
	
	public ZanroviForma(BibliotekaMain biblioteka, ZanrKnjige zanr, DefaultTableModel tableModel, JTable zanroviTabela) {
		this.biblioteka = biblioteka;
		this.zanr = zanr;
		this.tableModel = tableModel;
		this.zanroviTabela = zanroviTabela;
		
		if(zanr == null) {
			setTitle("Dodavanje zanra");
		}else {
			setTitle("Izmena podataka - " + zanr.getId());
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
		
		if(zanr != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		add(lblID);
		add(txtID);
		add(lblOznaka);
		add(txtOznaka);
		add(lblOpis);
		add(txtAreaOpis);
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
				if(validacija()) {
					int id = Integer.parseInt(txtID.getText().trim());
					String oznaka = txtOznaka.getText().trim();
					String opis = txtAreaOpis.getText().trim();
					
					if(zanr == null) {
						zanr = new ZanrKnjige(id, oznaka, opis, false);
						
						biblioteka.dodajZanr(zanr);
						Object[] red = kreirajRedTabele(zanr);
						tableModel.addRow(red);
					}else {
						zanr.setOznaka(oznaka);
						zanr.setOpis(opis);
						int red = zanroviTabela.getSelectedRow();
						refresh(red, zanr);
					}
					
					biblioteka.snimiZanrove(Main.ZANROVI_FAJL);
					zanroviTabela.clearSelection();
					ZanroviForma.this.dispose();
					ZanroviForma.this.setVisible(false);
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanroviForma.this.dispose();
				ZanroviForma.this.setVisible(false);
				
			}
		});
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu: \n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(zanr == null){
			int id = Integer.parseInt(txtID.getText().trim());
			ZanrKnjige pronadjen = biblioteka.pronadjiZanr(id);
			if(pronadjen != null) {
				poruka += "- Zanr sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtOznaka.getText().trim().equals("")) {
			poruka += "- Morate uneti oznaku\n";
			ok = false;
		}
		
		if(txtAreaOpis.getText().trim().equals("")) {
			poruka += "- Morate uneti opis\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(zanr.getId()));
		txtOznaka.setText(zanr.getOznaka());
		txtAreaOpis.setText(zanr.getOpis());
	}
	
	private Object[] kreirajRedTabele(ZanrKnjige zanr) {
		Object[] red = new Object[3];
		
		red[0] = zanr.getId();
		red[1] = zanr.getOznaka();
		red[2] = zanr.getOpis();
		
		return red;
	}
	
	private void refresh(int red, ZanrKnjige zanr) {
		if(red >= 0) {
			tableModel.setValueAt(zanr.getId(), red, 0);
			tableModel.setValueAt(zanr.getOznaka(), red, 1);
			tableModel.setValueAt(zanr.getOpis(), red, 2);
		}
	}
}
