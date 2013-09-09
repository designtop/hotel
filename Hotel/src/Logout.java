import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Logout {
	
	private JPanel pn;
	private JFrame fr;	
	
	private JComboBox comboChoose;
	
	private JLabel labelChoose;
	private JLabel labelLogin;
	private JLabel labelPass;
	private JLabel labelInfo;
	
	private JTextField tfieldLogin;
	private JPasswordField tfieldPass;
	
	private JButton buttonLogin;
	private JButton buttonReg;
	
	private static String username;
	
	String items []={"Customer","Admin"};
	
	public Logout(Point p) {
		
		fr = new JFrame("Logout");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        fr.setLocation(p);
        
        fr.setJMenuBar(new MyMenuBar());
        
        labelChoose = new JLabel("Choose");
        labelLogin = new JLabel("Login");
        labelPass = new JLabel("Password");
        labelInfo = new JLabel("Здесь нужно выбрать войти как клиент или как администратор");
        
        comboChoose = new JComboBox(items);
        comboChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				String cmbType = (String) jcmbType.getSelectedItem();
				if(cmbType.equals("Admin")){
				tfieldLogin.setText(cmbType);
				tfieldPass.setText(cmbType);
				}else{
					tfieldLogin.setText("");
					tfieldPass.setText("");
				}
			}
		});
        
        
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				username = tfieldLogin.getText();
				if(tfieldLogin.getText().equals("Admin")&&tfieldPass.getText().equals("Admin")){
					fr.dispose();
					new Admin(fr.getLocation());
				}else{
				try {
					DB.connectDb(StartFrame.getPath(), WorkDB.NAMEDB, StartFrame.getLogin(), StartFrame.getPass());
				} catch (Throwable e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ResultSet rs = DB.query("SELECT * FROM users WHERE username = '"+tfieldLogin.getText()+"'");
				
				
				try {
					rs.next();
					if(tfieldPass.getText().equals(rs.getString(2))){
						fr.dispose();
						new Customer(fr.getLocation());
					}else{
						JOptionPane.showMessageDialog(pn, "invalid username or password","error",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(pn, "something bad","error in check pass",JOptionPane.ERROR_MESSAGE);
				}
				
				}
			}
		});
        
        buttonReg = new JButton("Register");
        buttonReg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				new Register(fr.getLocation());
				
			}
		});
        
        tfieldLogin = new JTextField("", 25);
        tfieldPass = new JPasswordField("", 25);
        
        pn.add(labelChoose);
        labelChoose.setBounds(10,10,70,20);
        pn.add(comboChoose);
        comboChoose.setBounds(80,10,150,20);
        pn.add(labelLogin);
        labelLogin.setBounds(10,40,70,20);
        pn.add(tfieldLogin);
        tfieldLogin.setBounds(80,40,150,20);
        pn.add(labelPass);
        labelPass.setBounds(10,70,70,20);
        pn.add(tfieldPass);
        tfieldPass.setBounds(80,70,150,20);
        pn.add(buttonLogin);
        buttonLogin.setBounds(10,100,100,20);
        pn.add(buttonReg);
        buttonReg.setBounds(130,100,100,20);
        pn.add(labelInfo);
        labelInfo.setBounds(10,500,400,20);
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        
	}

	public static String getUsername() {
		return username;
	}
	
	
}
