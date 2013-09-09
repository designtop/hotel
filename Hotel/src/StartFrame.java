import java.awt.Button;
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


public class StartFrame {
	private JPanel pn;
	private JFrame fr;	
	
	private JLabel labelPath;
	private JLabel labelLogin;
	private JLabel labelPass;
	
	private JButton buttonInstallDb;
	private JButton buttonDeleteDb;
	private JButton connectDb;
	
	private JTextField tfieldPath;
	private JTextField tfieldLogin;
	private JPasswordField tfieldPass;
	
	private static String path;
	private static String login;
	private static String pass;
		
	public StartFrame() {
		fr = new JFrame("StartFrame");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        
        fr.setJMenuBar(new MyMenuBar());
        
        labelPath = new JLabel("Path");
        labelLogin = new JLabel("Login");
        labelPass = new JLabel("Password");
        
        buttonInstallDb = new JButton("Install DB");
        buttonInstallDb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					WorkDB.installDB(tfieldPath.getText(), tfieldLogin.getText(), tfieldPass.getText());
					JOptionPane.showMessageDialog(pn, "ok","installed",JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, "something bad","error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        buttonDeleteDb = new JButton("Delete DB");
        buttonDeleteDb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					WorkDB.deleteDB(tfieldPath.getText(), tfieldLogin.getText(), tfieldPass.getText());
					JOptionPane.showMessageDialog(pn, "ok","deleted",JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, "something bad","error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        
        connectDb = new JButton("Connect DB");
        connectDb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				path=tfieldPath.getText();
				login=tfieldLogin.getText();
			    pass=tfieldPass.getText();
			    
			try {
				DB.connectDb(tfieldPath.getText(), WorkDB.NAMEDB, tfieldLogin.getText(), tfieldPass.getText());
				fr.dispose();
				new Logout(fr.getLocation());
			} catch (SQLException ex) {
				
				JOptionPane.showMessageDialog(pn, "error in db connection","error",JOptionPane.ERROR_MESSAGE);
			}
											
			}
		});
        
        tfieldPath = new JTextField("jdbc:mysql://localhost/", 25);
        tfieldLogin = new JTextField("root", 25);
        tfieldPass = new JPasswordField("1111", 25);
        
        pn.add(labelPath);
        labelPath.setBounds(10,10,70,20);
        pn.add(tfieldPath);
        tfieldPath.setBounds(80,10,150,20);
        pn.add(labelLogin);
        labelLogin.setBounds(10,40,70,20);
        pn.add(tfieldLogin);
        tfieldLogin.setBounds(80,40,150,20);
        pn.add(labelPass);
        labelPass.setBounds(10,70,70,20);
        pn.add(tfieldPass);
        tfieldPass.setBounds(80,70,150,20);
        pn.add(buttonInstallDb);
        buttonInstallDb.setBounds(10,100,100,20);
        pn.add(buttonDeleteDb);
        buttonDeleteDb.setBounds(120,100,100,20);
        pn.add(connectDb);
        connectDb.setBounds(230,100,100,20);
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
	}

	public static String getPath() {
		return path;
	}

	public static String getLogin() {
		return login;
	}

	public static String getPass() {
		return pass;
	}
	
}

//pn.setLayout(null);

//label.setBounds(10,10,10,100);
