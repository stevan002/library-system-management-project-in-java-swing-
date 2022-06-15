package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextField txtkorisnickoIme = new JTextField(20);
	private JLabel lblPassword = new JLabel("Lozinka:");
	private JPasswordField txtlozinka = new JPasswordField(20);
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
		initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblGreeting, "span 2");
		add(lblUsername);
		add(txtkorisnickoIme);
		add(lblPassword);
		add(txtlozinka);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		lblGreeting.setForeground(new Color(255,255,255));
		lblGreeting.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblUsername.setForeground(new Color(255,255,255));
		lblUsername.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblPassword.setForeground(new Color(255,255,255));
		lblPassword.setFont(new Font("Helvetica", Font.BOLD, 13));
		
		txtkorisnickoIme.setText("super");
		txtlozinka.setText("mario111");
		getRootPane().setDefaultButton(btnOk);
	}
	
	public void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtkorisnickoIme.getText().trim();
				String lozinka = new String(txtlozinka.getPassword()).trim();
				
				if(korisnickoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(null, "Unesi sve podatke za prijavu", "Greska", JOptionPane.WARNING_MESSAGE);
				} else {
					Zaposleni prijavljeni = biblioteka.login(korisnickoIme, lozinka);
				if(prijavljeni == null) {
					JOptionPane.showMessageDialog(null, "Neispravni podaci za prijavu", "Greska", JOptionPane.ERROR_MESSAGE);
				} else {
					login.this.dispose();
					login.this.setVisible(false);
					
					glavniProzor glavniProzor = new glavniProzor(biblioteka, prijavljeni);
					glavniProzor.setVisible(true);
				}
			}
		}
	
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.this.dispose();
				login.this.setVisible(false);
				
			}
		});
	}
}
