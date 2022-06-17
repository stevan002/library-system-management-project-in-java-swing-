package gui.FormezaPrikaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import biblioteka.Administrator;
import biblioteka.BibliotekaMain;
import biblioteka.Main;
import gui.FormezaIzmenu.AdministratoriForma;

public class AdministratoriProzor extends JFrame {

	private static final long serialVersionUID = -3486447916370656539L;
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit  = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable administratoriTabela;
	private BibliotekaMain biblioteka;
	
	public AdministratoriProzor(BibliotekaMain biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Administratori");
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/slike/library-logo.png");
		setIconImage(image.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		initGUI();
		initActions();
		}
	private void initGUI() {
		ImageIcon addImage = new ImageIcon("src/slike/add.gif");
		btnAdd.setIcon(addImage);
		ImageIcon editImage = new ImageIcon("src/slike/edit.gif");
		btnEdit.setIcon(editImage);
		ImageIcon deleteImage = new ImageIcon("src/slike/delete.gif");
		btnDelete.setIcon(deleteImage);
		
		mainToolBar.add(btnAdd);
		mainToolBar.add(btnEdit);
		mainToolBar.add(btnDelete);
		
		add(mainToolBar, BorderLayout.NORTH);
		HashMap<Integer, Administrator> neobrisaniAdministratori = biblioteka.sviNeobrisaniAdministratori();
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Plata", "Korisnicko Ime", "Lozinka"};
		Object [][] sadrzaj = new Object[neobrisaniAdministratori.size()][zaglavlja.length];
		
		int red = 0;
		for(Administrator administrator : neobrisaniAdministratori.values()) {
			sadrzaj [red][0] = administrator.getId();
			sadrzaj [red][1] = administrator.getIme();
			sadrzaj [red][2] = administrator.getPrezime();
			sadrzaj [red][3] = administrator.getJmbg();
			sadrzaj [red][4] = administrator.getAdresa();
			sadrzaj [red][5] = administrator.getPol();
			sadrzaj [red][6] = administrator.getPlata();
			sadrzaj [red][7] = administrator.getKorisnickoIme();
			sadrzaj [red][8] = administrator.getKorisnickaSifra();
			
			red++;
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		administratoriTabela = new JTable(tableModel);
		administratoriTabela.setRowSelectionAllowed(true);
		administratoriTabela.setColumnSelectionAllowed(false);
		administratoriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		administratoriTabela.setDefaultEditor(Object.class, null);
		administratoriTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(administratoriTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdministratoriForma af = new AdministratoriForma(biblioteka, null, tableModel, administratoriTabela);
				af.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = administratoriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli koji zelite da izmenite.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int administratorID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					Administrator administrator = biblioteka.pronadjiAdministratora(administratorID);
					
					AdministratoriForma af = new AdministratoriForma(biblioteka, administrator, tableModel, administratoriTabela);
					af.setVisible(true);
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = administratoriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli koji zelite izbrisati.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int administratorID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					Administrator administrator = biblioteka.pronadjiAdministratora(administratorID);
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete administratora?", administratorID + " - Potvrda brisanje?", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						administrator.setObrisana(true);
						tableModel.removeRow(red);
						biblioteka.snimiAdministratore(Main.ADMINISTRATORI_FAJL);
					}
				}
				
			}
		});
	}

}
