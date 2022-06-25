package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import biblioteka.BibliotekaMain;
import net.miginfocom.swing.MigLayout;

public class PrviProzor extends JFrame{
	
	private static final long serialVersionUID = 4476556528477065154L;
	private JLabel lblNaslov = new JLabel("Dobrodosli u biblioteku");
	private JButton btnZaposleni = new JButton("Uloguj se kao zaposleni");
	private JButton btnRegistracija = new JButton("Registracija za clanove");
	private BibliotekaMain biblioteka;
	
	public PrviProzor(BibliotekaMain biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Biblioteka");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		ImageIcon image = new ImageIcon("src/slike/library-logo.png");
		setIconImage(image.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		initGUI();
		initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("center, wrap, gapy 5");
		setLayout(mig);
		add(lblNaslov);
		lblNaslov.setPreferredSize(new Dimension(200,40));
//		add(new JLabel());
		add(btnZaposleni);
		btnZaposleni.setPreferredSize(new Dimension(300,40));
		add(Box.createVerticalGlue());
		add(btnRegistracija);
		btnRegistracija.setPreferredSize(new Dimension(300,40));
		
		lblNaslov.setForeground(new Color(255,255,255));
		lblNaslov.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		lblNaslov.setBorder(BorderFactory.createCompoundBorder(
		        lblNaslov.getBorder(), 
		        BorderFactory.createEmptyBorder(0, 30, 10, 0)));
	}
	
	public void initActions() {
		btnZaposleni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login login = new login(biblioteka);
				login.setVisible(true);
				
			}
		});
		btnRegistracija.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Registracija registracija = new Registracija(biblioteka, null);
				registracija.setVisible(true);
				
			}
		});
	}
	

}
