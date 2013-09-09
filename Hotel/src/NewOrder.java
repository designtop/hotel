import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NewOrder {
	private JPanel pn;
	private JFrame fr;
	
	private JComboBox comboPersons;
	private JComboBox comboRoomType;
	private JComboBox comboDays;
	
	private JLabel labelName;
	private JLabel labelPersons;
	private JLabel labelRoomType;
	private JLabel labelDays;
	
	private JTextField tfieldName;
	
	private JButton buttonSubmit;
	private JButton buttonBack;
	
	String roomtype []={"Superior","STD", "family room", "business"};
	String persons []={"1","2","3","4","5","6"};
	String days []={"1","2","3","4","5","6","7","8","9","10"};
	
	public NewOrder(Point p) {
		fr = new JFrame("New Order");
        pn = new JPanel();
        pn.setLayout(null);
        fr.setSize(600, 600);
        fr.setLocation(p);
        
        fr.setJMenuBar(new MyMenuBar());
        
        labelName = new JLabel("Имя");
        labelPersons = new JLabel("Мест");
        labelRoomType = new JLabel("Класс");
        labelDays = new JLabel("Дней");
        
        comboPersons = new JComboBox(persons);
        comboPersons.setSelectedIndex(2);
        comboRoomType = new JComboBox(roomtype);
        comboRoomType.setSelectedIndex(2);
        comboDays = new JComboBox(days);
        comboDays.setSelectedIndex(2);
        
        tfieldName = new JTextField("", 25);
        
        buttonSubmit = new JButton("Submit");
        buttonSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DB.addOrder(new Orders (tfieldName.getText(), Logout.getUsername(), (String)comboPersons.getSelectedItem(), (String)comboRoomType.getSelectedItem(), (String)comboDays.getSelectedItem(), "pending"));
					JOptionPane.showMessageDialog(pn, "Заказ успешно размещен. Ожидайте ответа администратора.","ok",JOptionPane.INFORMATION_MESSAGE);	
					fr.dispose();
					new Customer(fr.getLocation());	
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pn, "Error in addOrder " + e1,"error",JOptionPane.ERROR_MESSAGE);
				}

			}
		});
        
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fr.dispose();
				new Customer(fr.getLocation());				
			}
		});
        
        pn.add(labelName);
        labelName.setBounds(10,10,70,20);
        pn.add(tfieldName);
        tfieldName.setBounds(80,10,150,20);
        pn.add(labelPersons);
        labelPersons.setBounds(10,40,70,20);
        pn.add(comboPersons);
        comboPersons.setBounds(80,40,150,20);
        pn.add(labelRoomType);
        labelRoomType.setBounds(10,70,70,20);
        pn.add(comboRoomType);
        comboRoomType.setBounds(80,70,150,20);
        pn.add(labelDays);
        labelDays.setBounds(10,100,70,20);
        pn.add(comboDays);
        comboDays.setBounds(80,100,150,20);
                
        buttonSubmit.setBounds(130,150,150,60);
        pn.add(buttonSubmit);
        buttonBack.setBounds(290,150,150,60);
        pn.add(buttonBack);
        
        fr.add(pn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
	}
}
