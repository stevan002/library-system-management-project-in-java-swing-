package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import biblioteka.BibliotekaMain;
import biblioteka.Zaposleni;
import net.miginfocom.swing.MigLayout;

public class login extends JFrame {
	private JLabel lblGreeting = new JLabel("Molim vas prijavite se.");
	private JLabel lblUsername = new JLabel("Korisnicko ime:");
	private JTextField korisnickoIme = new JTextField(20);
	private JLabel lblPassword = new JLabel("Lozinka:");
	private JPasswordField lozinka = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private BibliotekaMain biblioteka;
	
	public login(BibliotekaMain biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Prijava");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		ImageIcon LibaryImage = new ImageIcon("src/slike/library-logo.png");
		setIconImage(LibaryImage.getImage());
		getContentPane().setBackground(new Color(102, 153, 0));
		initGUI();
		//initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblGreeting, "span 2");
		add(lblUsername);
		add(korisnickoIme);
		add(lblPassword);
		add(lozinka);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		lblGreeting.setForeground(new Color(255,255,255));
		lblGreeting.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblUsername.setForeground(new Color(255,255,255));
		lblUsername.setFont(new Font("Sans Serif", Font.BOLD, 13));
		lblPassword.setForeground(new Color(255,255,255));
		lblPassword.setFont(new Font("Sans Serif", Font.BOLD, 13));
		
		korisnickoIme.setText("StevanStankovic");
		lozinka.setText("stevan123");
		getRootPane().setDefaultButton(btnOk);
	}
	
	
}
