import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class EditUsers{

	private JPanel pn;
	private JFrame fr;
	private JButton buttonDel;
	private JButton buttonAdd;
	private JButton buttonBack;
	private JTextField tfieldUsername;
	private JTextField tfieldPassword;
	
	private MouseAdapter mouse;
	
	private MyTable table;
	
	private JScrollPane scroll;
	
	public EditUsers(Point p) {
		   
        fr = new JFrame("Edit Users");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        fr.setJMenuBar(new MyMenuBar());
        fr.setLocation(p);
              
        buttonAdd = new JButton("ADD USER");
        buttonDel = new JButton("DELETE USER");
        buttonBack = new JButton("BACK");
        buttonAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfieldUsername.getText().isEmpty()||tfieldUsername.getText().contains(" ")){
					JOptionPane.showMessageDialog(pn, "username field cannot be empty or contain spaces","error",JOptionPane.ERROR_MESSAGE);
				}else{
				try {
					DB.addUser(new Users (tfieldUsername.getText(), tfieldPassword.getText()));
					updateTable();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, e1,"error",JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		});
        
        
buttonDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DB.deleteUser(new Users (tfieldUsername.getText(), tfieldPassword.getText()));
					updateTable();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, e1,"error",JOptionPane.ERROR_MESSAGE);			
				}
				
			}
		});
        
buttonBack.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fr.dispose();
		new Admin(fr.getLocation());
	}
});   
        
        tfieldUsername = new JTextField("USERNAME", 50);
        tfieldPassword = new JTextField("PASSWORD", 50);
        
        pn.add(buttonAdd);
        buttonAdd.setBounds(10,10,130,20);
        pn.add(buttonDel);
        buttonDel.setBounds(150,10,130,20);
        pn.add(buttonBack);
        buttonBack.setBounds(290,10,130,20);
        pn.add(tfieldUsername);
        tfieldUsername.setBounds(10,40,130,20);
        pn.add(tfieldPassword);
        tfieldPassword.setBounds(10,70,130,20);
        
        try {
			DB.connectDb(StartFrame.getPath(), WorkDB.NAMEDB, StartFrame.getLogin(), StartFrame.getPass());
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
        table = new MyTable(DB.query("SELECT * FROM users"));
        scroll = new JScrollPane(table);
        scroll.setBounds(10, 100, 420, 150);
        pn.add(scroll);
        
        mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	tfieldUsername.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            	
            }
        };
        table.addMouseListener(mouse);
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
public void updateTable(){
	pn.remove(scroll);
	try {
		DB.connectDb(StartFrame.getPath(), WorkDB.NAMEDB, StartFrame.getLogin(), StartFrame.getPass());
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    table = new MyTable(DB.query("SELECT * FROM users"));
    table.addMouseListener(mouse);
    scroll = new JScrollPane(table);
    scroll.setBounds(10, 100, 420, 150);
    pn.add(scroll);
    pn.updateUI();

}
	

}
