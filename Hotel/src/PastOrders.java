import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PastOrders {
	private JPanel pn;
	private JFrame fr;
	
	private JButton buttonBack;

    private MyTable table;
	
	private JScrollPane scroll;
	
	public PastOrders(Point p) {
		fr = new JFrame("Customer Panel");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        fr.setLocation(p);
        fr.setJMenuBar(new MyMenuBar());
        
        try {
			DB.connectDb(StartFrame.getPath(), WorkDB.NAMEDB, StartFrame.getLogin(), StartFrame.getPass());
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        table = new MyTable(DB.query("SELECT * FROM orders WHERE username = '"+Logout.getUsername()+"'"));
        scroll = new JScrollPane(table);
        scroll.setBounds(50, 200, 500, 150);
        
        buttonBack = new JButton("BACK");
        buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				new Customer(fr.getLocation());				
			}
		});
        
      
        
        buttonBack.setBounds(225,10,150,60);
        pn.add(buttonBack);
        pn.add(scroll);
 
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
	}
}
