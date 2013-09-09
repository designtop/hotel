import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MyTable extends JTable{

	public MyTable(ResultSet rs) {
		DefaultTableModel dft = new DefaultTableModel();
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dft.addColumn(rsmd.getColumnName(i));
            }
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    v.add(rs.getString(i));
                }
                dft.addRow(v);
            }
            setModel(dft);
            setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            System.out.println("Error in MyTable " + ex);
        }
    }
}

		
	
