package gui.FormezaPrikaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

import biblioteka.BibliotekaMain;
import biblioteka.ClanBiblioteke;
import biblioteka.Main;
import gui.FormezaIzmenu.ClanoviBibliotekeForma;
import gui.FormezaIzmenu.UplataForma;

public class ClanoviBibliotekeProzor extends JFrame{

	private static final long serialVersionUID = 3354760938561509847L;
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private JButton btnPay = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable clanoviBibliotekeTabela;
	
	private BibliotekaMain biblioteka;
	
	public ClanoviBibliotekeProzor(BibliotekaMain biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Clanovi biblioteke");
		setSize(1000, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon LibraryImage = new ImageIcon("src/slike/library-logo.png");
		setIconImage(LibraryImage.getImage());
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
		ImageIcon payImage = new ImageIcon("src/slike/pay-2.png");
		btnPay.setIcon(payImage);
		
		mainToolBar.add(btnAdd);
		mainToolBar.add(btnEdit);
		mainToolBar.add(btnDelete);
		mainToolBar.add(btnPay);
		
		add(mainToolBar, BorderLayout.NORTH);
		
		HashMap<Integer, ClanBiblioteke> neobrisaniClanoviBiblioteke = biblioteka.sviNeobrisaniClanoviBiblioteke();
		
		String[] zaglavlja = new String [] {"ID", "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Broj clanske karte", "Datum poslednje uplate", "Unapred uplaceno meseci", "Aktivan", "Tip clanarine"};
		Object [][] sadrzaj = new Object[neobrisaniClanoviBiblioteke.size()][zaglavlja.length];
		
		int red = 0;
		for(ClanBiblioteke clanBiblioteke : neobrisaniClanoviBiblioteke.values()) {			
			sadrzaj[red][0] = clanBiblioteke.getId();
			sadrzaj[red][1] = clanBiblioteke.getIme();
			sadrzaj[red][2] = clanBiblioteke.getPrezime();
			sadrzaj[red][3] = clanBiblioteke.getJmbg();
			sadrzaj[red][4] = clanBiblioteke.getAdresa();
			sadrzaj[red][5] = clanBiblioteke.getPol();
			sadrzaj[red][6] = clanBiblioteke.getBrClanKarte();
			if(clanBiblioteke.getDatumPoslednjeUplate().equals(LocalDate.parse("2021-12-12"))) {
				sadrzaj[red][7] = "/";
			} else {
				sadrzaj[red][7] = clanBiblioteke.getDatumPoslednjeUplate();
			}
			sadrzaj[red][8] = clanBiblioteke.getBrojMeseciClanarine();
			if (clanBiblioteke.isAktivan()) {
				sadrzaj[red][9] = "Da";
			} else {
				sadrzaj[red][9] = "Ne";
			}
			sadrzaj[red][10] = clanBiblioteke.getTipClanarine().getNaziv();
			
			red++;
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		clanoviBibliotekeTabela = new JTable(tableModel);
		
		clanoviBibliotekeTabela.getColumnModel().getColumn(0).setPreferredWidth(5);
		clanoviBibliotekeTabela.getColumnModel().getColumn(1).setPreferredWidth(20);
		clanoviBibliotekeTabela.getColumnModel().getColumn(2).setPreferredWidth(30);
		clanoviBibliotekeTabela.getColumnModel().getColumn(3).setPreferredWidth(60);
		clanoviBibliotekeTabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		clanoviBibliotekeTabela.getColumnModel().getColumn(5).setPreferredWidth(15);
		clanoviBibliotekeTabela.getColumnModel().getColumn(7).setPreferredWidth(100);
		clanoviBibliotekeTabela.getColumnModel().getColumn(8).setPreferredWidth(110);
		clanoviBibliotekeTabela.getColumnModel().getColumn(9).setPreferredWidth(15);
		clanoviBibliotekeTabela.getColumnModel().getColumn(10).setPreferredWidth(130);
		
		clanoviBibliotekeTabela.setRowSelectionAllowed(true);
		clanoviBibliotekeTabela.setColumnSelectionAllowed(false);
		clanoviBibliotekeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clanoviBibliotekeTabela.setDefaultEditor(Object.class, null);
		clanoviBibliotekeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(clanoviBibliotekeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanoviBibliotekeForma cbf = new ClanoviBibliotekeForma(biblioteka, null, tableModel, clanoviBibliotekeTabela);
				cbf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = clanoviBibliotekeTabela.getSelectedRow();
				if(red == -1) {
					
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli koji zelite da izmenite.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int clanID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					ClanBiblioteke clanBiblioteke = biblioteka.pronadjiClanaBiblioteke(clanID);
					

					ClanoviBibliotekeForma cbf = new ClanoviBibliotekeForma(biblioteka, clanBiblioteke, tableModel, clanoviBibliotekeTabela);
					cbf.setVisible(true);
					
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = clanoviBibliotekeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli koji zelite da obrisete.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int clanID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					ClanBiblioteke clanBiblioteke = biblioteka.pronadjiClanaBiblioteke(clanID);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete clana biblioteke?", 
							clanID + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						clanBiblioteke.setObrisana(true);
						tableModel.removeRow(red);
						biblioteka.snimiClanoveBiblioteke(Main.CLANOVI_BIBLIOTEKE_FAJL);
					}
				}
			}
		});
		
		btnPay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = clanoviBibliotekeTabela.getSelectedRow();
				if(red == -1) {
					
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli kojem zelite da uplatite clanarinu.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int clanID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					ClanBiblioteke clanBiblioteke = biblioteka.pronadjiClanaBiblioteke(clanID);
					

					UplataForma ucf = new UplataForma(biblioteka, clanBiblioteke, tableModel, clanoviBibliotekeTabela);
					ucf.setVisible(true);
					
				}
			}
		});
	}

}
