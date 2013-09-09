import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Register {
	
	private JPanel pn;
	private JFrame fr;
	
	private JLabel labelLogin;
	private JLabel labelPass;
	private JLabel labelConfirmPass;
	
	private JTextField tfieldLogin;
	private JPasswordField tfieldPass;
	private JPasswordField tfieldConfirmPass;
	
	private JButton buttonSubmit;
	private JButton buttonBack;
	
	public Register(Point p) {
		fr = new JFrame("Logout");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        fr.setLocation(p);
        
        fr.setJMenuBar(new MyMenuBar());
        
        labelLogin = new JLabel("Login");
        labelPass = new JLabel("Password");
        labelConfirmPass = new JLabel("Confirm Password");
        
        tfieldLogin = new JTextField("", 25);
        tfieldPass = new JPasswordField("", 25);
        tfieldConfirmPass = new JPasswordField("", 25);
        
        buttonSubmit = new JButton("Submit");
        buttonBack = new JButton("Back");
        
        pn.add(tfieldLogin);
        tfieldLogin.setBounds(10,10,150,20);
        pn.add(labelLogin);
        labelLogin.setBounds(170,10,70,20);
        pn.add(tfieldPass);
        tfieldPass.setBounds(10,40,150,20);
        pn.add(labelPass);
        labelPass.setBounds(170,40,70,20);
        pn.add(tfieldConfirmPass);
        tfieldConfirmPass.setBounds(10,70,150,20);
        pn.add(labelConfirmPass);
        labelConfirmPass.setBounds(170,70,150,20);
        pn.add(buttonBack);
        buttonBack.setBounds(10,100,100,20);
        buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				new Logout(fr.getLocation());
				
			}
		});
        
        pn.add(buttonSubmit);
        buttonSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfieldPass.getText().equals(tfieldConfirmPass.getText())){
					JOptionPane.showMessageDialog(pn, "check password","error",JOptionPane.ERROR_MESSAGE);
				}else if(tfieldLogin.getText().isEmpty()||tfieldLogin.getText().contains(" ")){
					JOptionPane.showMessageDialog(pn, "login field cannot be empty or contain spaces","error",JOptionPane.ERROR_MESSAGE);
				}else{
					
				try {
					DB.connectDb(StartFrame.getPath(), WorkDB.NAMEDB, StartFrame.getLogin(), StartFrame.getPass());
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(pn, "error in db connection","error",JOptionPane.ERROR_MESSAGE);
				}
				try {
					DB.addUser(new Users (tfieldLogin.getText(), tfieldPass.getText()));
					DB.close();
					JOptionPane.showMessageDialog(pn, "you can login now","ok",JOptionPane.INFORMATION_MESSAGE);
					new Logout(fr.getLocation());
					fr.dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, e1,"error",JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		});
        
        buttonSubmit.setBounds(130,100,100,20);
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        		
	}
	
	
	
	
}
