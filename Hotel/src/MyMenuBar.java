import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class MyMenuBar extends JMenuBar{
JMenuBar mb;
JMenu file;
JMenuItem exit, about;

public MyMenuBar() {
	
	file = new JMenu("����");
	about= new JMenuItem("� ���������");
	exit= new JMenuItem("�����");
	add(file);
	file.add(exit);
	file.add(about);
exit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
	        System.exit(0);
			
		}
	});
	about.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(file, "������� ����� ���������. ������ �������. 2013","ok",JOptionPane.INFORMATION_MESSAGE);
			
		}
	});
	
}


}
