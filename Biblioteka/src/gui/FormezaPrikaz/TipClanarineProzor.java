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

import biblioteka.BibliotekaMain;
import biblioteka.Main;
import biblioteka.TipClanarine;
import gui.FormezaIzmenu.TipoviClanarineForma;

public class TipClanarineProzor extends JFrame{
	
	private static final long serialVersionUID = -4995472973520709731L;
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable tipClanarineTabela;
	private BibliotekaMain biblioteka;
	
	public TipClanarineProzor(BibliotekaMain biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Tipovi clanarine");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/slike/library-logo.png");
		setIconImage(image.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		initGUI();
		initActions();
	}
	
	public void initGUI() {
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
		
		HashMap <Integer, TipClanarine> neobrisaniTipoviClanarine = biblioteka.sviNeobrisaniTipoviClanarine();
		
		String[] zaglavlja = new String[] {"ID", "Naziv", "Cena"};
		Object[][] sadrzaj = new Object[neobrisaniTipoviClanarine.size()][zaglavlja.length];
		
		int red = 0;
		for(TipClanarine tipClanarine : neobrisaniTipoviClanarine.values()) {
			sadrzaj[red][0] = tipClanarine.getId();
			sadrzaj[red][1] = tipClanarine.getNaziv();
			sadrzaj[red][2] = tipClanarine.getCena();
		
			red++;
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		tipClanarineTabela = new JTable(tableModel);
		
		tipClanarineTabela.setRowSelectionAllowed(false);
		tipClanarineTabela.setColumnSelectionAllowed(false);
		tipClanarineTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tipClanarineTabela.setDefaultEditor(Object.class, null);
		tipClanarineTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(tipClanarineTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoviClanarineForma tcf = new TipoviClanarineForma(biblioteka, null, tableModel, tipClanarineTabela);
				tcf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tipClanarineTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli koji zelite.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int TipClanarineID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					TipClanarine tipClanarine = biblioteka.pronadjiTipClanarine(TipClanarineID);
					
					TipoviClanarineForma tcf = new TipoviClanarineForma(biblioteka, tipClanarine, tableModel, tipClanarineTabela);
					tcf.setVisible(true);
				}
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tipClanarineTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli koji zelite da obrisete.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int tipClanarineID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					TipClanarine tipClanarine = biblioteka.pronadjiTipClanarine(tipClanarineID);
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete odabrani tip clanarine?", tipClanarineID + "- Potvrda brisanja?", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						tipClanarine.setObrisan(true);
						tableModel.removeRow(red);
						biblioteka.snimiTipClanarine(Main.TIPOVI_CLANARINE_FAJL);
					}
				
				}
			}
		});
	}

}
