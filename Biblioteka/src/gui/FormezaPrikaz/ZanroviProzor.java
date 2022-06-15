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
import biblioteka.ZanrKnjige;
import gui.FormezaIzmenu.ZanroviForma;

public class ZanroviProzor extends JFrame{

	
	private static final long serialVersionUID = -8311766028442857932L;
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable zanroviTabela;
	private BibliotekaMain biblioteka;
	
	public ZanroviProzor(BibliotekaMain biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Zanrovi");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/slike/library-logo.png");
		setIconImage(image.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		InitGUI();
		InitActions();
	}
	
	private void InitGUI() {
		ImageIcon addImage = new ImageIcon("src/slike/add.gif");
		btnAdd.setIcon(addImage);
		ImageIcon editImage = new ImageIcon("src/slike/edit.gif");
		btnEdit.setIcon(editImage);
		ImageIcon delImage = new ImageIcon("src/slike/delete.gif");
		btnDelete.setIcon(delImage);
		
		mainToolBar.add(btnAdd);
		mainToolBar.add(btnEdit);
		mainToolBar.add(btnDelete);
		
		add(mainToolBar, BorderLayout.NORTH);
		
		HashMap<Integer, ZanrKnjige> neobrisaniZanrovi = biblioteka.sviNeobrisaniZanrovi();
		String[] zaglavlja = new String[] {"Id", "Oznaka", "Opis"};
		Object[][] sadrzaj = new Object[neobrisaniZanrovi.size()][zaglavlja.length];
		
		int red = 0;
		for(ZanrKnjige zanr : neobrisaniZanrovi.values()) {
			sadrzaj[red][0] = zanr.getId();
			sadrzaj[red][1] = zanr.getOznaka();
			sadrzaj[red][2] = zanr.getOpis();
			red++;
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		zanroviTabela = new JTable(tableModel);
		
		zanroviTabela.setRowSelectionAllowed(true);
		zanroviTabela.setColumnSelectionAllowed(false);
		zanroviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		zanroviTabela.setDefaultEditor(Object.class, null);
		zanroviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(zanroviTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	private void InitActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					ZanroviForma zf = new ZanroviForma(biblioteka, null, tableModel, zanroviTabela);
					zf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = zanroviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odabrati red u tabeli za izmenu", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int zanrID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					ZanrKnjige zanr = biblioteka.pronadjiZanr(zanrID);
					ZanroviForma zf = new ZanroviForma(biblioteka, zanr, tableModel, zanroviTabela);
					zf.setVisible(true);
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = zanroviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odaberite red u tabeli za brisanje", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int zanrID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					ZanrKnjige zanr = biblioteka.pronadjiZanr(zanrID);
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete izabrani zanr?", zanrID + "- Potvrda brisanja?", JOptionPane.YES_NO_CANCEL_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						zanr.setObrisan(true);
						tableModel.removeRow(red);
						biblioteka.snimiZanrove(Main.ZANROVI_FAJL);
					}
				}
				
			}
		});
	}
	
}
