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


public class ReviewOrders {
	private JPanel pn;
	private JFrame fr;
	
	private JButton buttonAccept;
	private JButton buttonDecline;
	private JButton buttonBack;
	
	private MouseAdapter mouse;
	
	private JTextField tfieldId;
	
    private MyTable table;
	
	private JScrollPane scroll;
	
	public ReviewOrders(Point p) {
		fr = new JFrame("Admin Panel");
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
        table = new MyTable(DB.query("SELECT * FROM orders"));
        scroll = new JScrollPane(table);
        scroll.setBounds(50, 50, 500, 150);
        
        buttonAccept = new JButton("ACCEPT");
        buttonAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DB.changeOrderStatus(new Orders ("","","","","","accepted",Integer.parseInt(tfieldId.getText())));
					updateTable();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(pn, "Error in changeOrderStatus " + e1,"error",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, "Error in changeOrderStatus " + e1,"error",JOptionPane.ERROR_MESSAGE);
				}
							
			}
		});
        buttonDecline = new JButton("DECLINE");
        buttonDecline.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DB.changeOrderStatus(new Orders ("","","","","","declined",Integer.parseInt(tfieldId.getText())));
					updateTable();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(pn, "Error in changeOrderStatus " + e1,"error",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, "Error in changeOrderStatus " + e1,"error",JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fr.dispose();
				new Admin(fr.getLocation());				
			}
		});
        
        tfieldId = new JTextField("ID", 25);
              
        buttonAccept.setBounds(10,10,100,20);
        pn.add(buttonAccept);
        buttonDecline.setBounds(120,10,100,20);
        pn.add(buttonDecline);
        buttonBack.setBounds(230,10,100,20);
        pn.add(buttonBack);
        tfieldId.setBounds(340,10,100,20);
        pn.add(tfieldId);
        pn.add(scroll);
        
        mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	tfieldId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            	
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
			e.printStackTrace();
		}
	    table = new MyTable(DB.query("SELECT * FROM orders"));
	    table.addMouseListener(mouse);
	    scroll = new JScrollPane(table);
	    scroll.setBounds(50, 50, 500, 150);
	    pn.add(scroll);
	    pn.updateUI();

	}
}
