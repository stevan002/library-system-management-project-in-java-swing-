package gui.FormezaIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import biblioteka.BibliotekaMain;
import biblioteka.ClanBiblioteke;
import biblioteka.Main;
import net.miginfocom.swing.MigLayout;

public class BibliotekaForma extends JFrame{

	private static final long serialVersionUID = -6888999118859387209L;
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblTelefon = new JLabel("Telefon");
	private JTextField txtTelefon = new JTextField(20);
	private JLabel lblRadnoVreme = new JLabel("Radno vreme");
	private JTextField txtRadnoVreme = new JTextField(20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private BibliotekaMain bibliotekaMain;
	private Biblioteka biblioteka;
	
	public BibliotekaForma(BibliotekaMain bibliotekaApp, Biblioteka biblioteka) {
		this.bibliotekaMain = bibliotekaApp;
		this.biblioteka = biblioteka;
		
		setTitle("Izmena podataka biblioteke");
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
		
		txtID.setEnabled(false);
		popuniPolja();
		
		add(lblID);
		add(txtID);
		add(lblNaziv);
		add(txtNaziv);
		add(lblAdresa);
		add(txtAdresa);
		add(lblTelefon);
		add(txtTelefon);
		add(lblRadnoVreme);
		add(txtRadnoVreme);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
	}
	
	public void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					int id = Integer.parseInt(txtID.getText().trim());
					String naziv = txtNaziv.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String telefon = txtTelefon.getText().trim();
					String radnoVreme = txtRadnoVreme.getText().trim();
						biblioteka.setNaziv(naziv);
						biblioteka.setAdresa(adresa);
						biblioteka.setTelefon(telefon);
						biblioteka.setRadnoVreme(radnoVreme);
					
					bibliotekaMain.snimiBiblioteku(Main.BIBLIOTEKA_FAJL);
					BibliotekaForma.this.dispose();
					BibliotekaForma.this.setVisible(false);
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekaForma.this.dispose();
				BibliotekaForma.this.setVisible(false);
				
			}
		});
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		
		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Morate uneti naziv\n";
			ok = false;
		}
		
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Morate uneti adresu\n";
			ok = false;
		}
		
		if(txtTelefon.getText().trim().equals("")) {
			poruka += "- Morate uneti telefon\n";
			ok = false;
		}
		
		if(txtRadnoVreme.getText().trim().equals("")) {
			poruka += "- Morate uneti radno vreme\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(biblioteka.getId()));
		txtNaziv.setText(biblioteka.getNaziv());
		txtAdresa.setText(biblioteka.getAdresa());
		txtTelefon.setText(biblioteka.getTelefon());
		txtRadnoVreme.setText(biblioteka.getRadnoVreme());
		
	}
	

}
