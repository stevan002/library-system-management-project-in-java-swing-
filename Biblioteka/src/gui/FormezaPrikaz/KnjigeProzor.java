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
import biblioteka.Knjiga;
import biblioteka.Main;
import gui.FormezaIzmenu.KnjigaForma;

public class KnjigeProzor extends JFrame{
	
	private static final long serialVersionUID = 5056951407864561268L;
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable knjigeTabela;
	private BibliotekaMain biblioteka;
	
	public KnjigeProzor(BibliotekaMain biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Knjige");
		setSize(700, 400);
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
		
		HashMap<Integer, Knjiga> neobrisaneKnjige = biblioteka.sveNeobrisaneKnjige();
		
		String[] zaglavlja = new String [] {"ID", "Naslov", "Originalni Naslov", "Pisac", "Godina objavljivanja", "Opis", "Zanr", "Jezik originala"};
		Object [][] sadrzaj = new Object[neobrisaneKnjige.size()][zaglavlja.length];
		
		int red = 0;
		for(Knjiga knjiga : neobrisaneKnjige.values()) {
			sadrzaj[red][0] = knjiga.getId();
			sadrzaj[red][1] = knjiga.getNaslovKnjige();
			sadrzaj[red][2] = knjiga.getOriginalniNaslovKnjige();
			sadrzaj[red][3] = knjiga.getPisac();
			sadrzaj[red][4] = knjiga.getGodinaPublikacije();
			sadrzaj[red][5] = knjiga.getOpis();
			sadrzaj[red][6] = knjiga.getZanr().getOznaka();
			sadrzaj[red][7] = knjiga.getJezikOriginala();
			
			red++;
		}
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		knjigeTabela = new JTable(tableModel);
		
		knjigeTabela.setRowSelectionAllowed(true);
		knjigeTabela.setColumnSelectionAllowed(false);
		knjigeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjigeTabela.setDefaultEditor(Object.class, null);
		knjigeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(knjigeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				KnjigaForma kf = new KnjigaForma(biblioteka, null, knjigeTabela, tableModel);
				kf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int red = knjigeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odabrati red koji zelite izmeniti.", "Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					int knjigeID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					Knjiga knjiga = biblioteka.pronadjiKnjigu(knjigeID);
					
					KnjigaForma kf = new KnjigaForma(biblioteka, knjiga, knjigeTabela, tableModel);
					kf.setVisible(true);
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int red = knjigeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Molimo odabrati red koji zelite obrisati.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int knjigeID = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					Knjiga knjiga = biblioteka.pronadjiKnjigu(knjigeID);
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite obrisati knjigu?", knjigeID + "- Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						knjiga.setObrisana(true);
						tableModel.removeRow(red);
						biblioteka.snimiKnjige(Main.KNJIGE_FAJL);
					}
				}
				
			}
		});
		
	}
}
