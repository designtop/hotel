import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Customer {
	private JPanel pn;
	private JFrame fr;
	
	private JButton buttonNewOrder;
	private JButton buttonPastOrders;
	
	public Customer(Point p) {
		fr = new JFrame("Customer Panel");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        fr.setLocation(p);
        fr.setJMenuBar(new MyMenuBar());
        
        buttonNewOrder = new JButton("New Order");
        buttonNewOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				fr.dispose();
				new NewOrder(fr.getLocation());				
			}
		});
        buttonPastOrders = new JButton("Past Orders");
        buttonPastOrders.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fr.dispose();
				new PastOrders(fr.getLocation());				
			}
		});
                
        buttonNewOrder.setBounds(130,10,150,60);
        pn.add(buttonNewOrder);
        buttonPastOrders.setBounds(290,10,150,60);
        pn.add(buttonPastOrders);
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
	}
}
