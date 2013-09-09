import java.awt.Button;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Admin {

	private JPanel pn;
	private JFrame fr;
	
	private JButton buttonEditUsers;
	private JButton buttonOrders;
	
	public Admin(Point p) {
		fr = new JFrame("Logout");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        fr.setLocation(p);
        fr.setJMenuBar(new MyMenuBar());
        
        buttonEditUsers = new JButton("Edit Users");
        buttonEditUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				new EditUsers(fr.getLocation());				
			}
		});
        
        buttonOrders = new JButton("Orders");
        buttonOrders.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				new ReviewOrders(fr.getLocation());				
			}
		});
        
        buttonEditUsers.setBounds(130,10,150,60);
        pn.add(buttonEditUsers);
        buttonOrders.setBounds(290,10,150,60);
        pn.add(buttonOrders);
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
	}
	
	

}
