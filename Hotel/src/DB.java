import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSetMetaData;


public class DB {
	private static Connection cn;
	private static Statement st;
	private static ResultSet rs;
	
	public DB(String  path, String nameDB, String login, String pass){	

				try {
					connectDb(path, nameDB, login, pass);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "error in db connection","error",JOptionPane.ERROR_MESSAGE);
				}
		
	}
	
	public static void connectDb(String  path, String nameDB, String login, String pass) throws  SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(path+nameDB,login,pass);
				try{
				st = cn.createStatement();
				} catch (SQLException e) {
					System.out.println("Error in Statement" + e);
				}
		} catch (ClassNotFoundException e) {
			System.out.println("Error in Driver" + e);
		}
	}
	
	
	public static void update (String sql) throws SQLException{
		st.executeUpdate(sql); 
	}
	public static ResultSet query(String sql){
		try {
			rs= st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Query" + e);
		}
		return rs;
	}
	public static void close(){
		try {
			st.close();
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Close" + e);
		}
	}
	public static void showResultSet(ResultSet rs){
		try{
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		for(int i=1; i<=rsmd.getColumnCount(); i++){
			System.out.print(rsmd.getColumnName(i)+" \t");
			
		}
		while(rs.next()){
			System.out.println();
			for(int i=1; i<=rsmd.getColumnCount(); i++){
				System.out.print(rs.getString(i)+" \t");
			}
		}
		}catch(SQLException ex){
			System.out.println("Error in showResultSet" +ex);
		}
	}
	
public static void addUser(Users users) throws SQLException{
        PreparedStatement ps = null;
        String sql = "INSERT INTO " + users.getClass().getSimpleName() + " (username, password) VALUES(?,?)";
        ps = (PreparedStatement) cn.prepareStatement(sql);
        ps.setString(1, users.getUsername());
        ps.setString(2, users.getPassword());
        ps.executeUpdate();
}

public static void changeUserName (Users user) {
    try {
        PreparedStatement ps = null;
        String sql = "UPDATE " + user.getClass().getSimpleName() + " SET username=? WHERE username=" + "'"+user.getUsername()+"'";
        ps = (PreparedStatement) cn.prepareStatement(sql);
        ps.setString(1, user.getPassword());
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error in changeUserName " + ex);
    }
}
public static void deleteUser(Users user) throws SQLException{
    String sql = "DELETE FROM " + user.getClass().getSimpleName() + " WHERE username=" + "'"+user.getUsername()+"'";
		update(sql);
}

public static void addOrder(Orders order) throws SQLException{

        PreparedStatement ps = null;
        String sql = "INSERT INTO " + order.getClass().getSimpleName() + " (name, username, persons, roomtype, days, status) VALUES(?,?,?,?,?,?)";
        ps = (PreparedStatement) cn.prepareStatement(sql);
        ps.setString(1, order.getName());
        ps.setString(2, order.getUsername());
        ps.setString(3, order.getPersons());
        ps.setString(4, order.getRoomtype());
        ps.setString(5, order.getDays());
        ps.setString(6, order.getStatus());
        ps.executeUpdate();
}
public static void changeOrderStatus (Orders order) throws SQLException{
 
        PreparedStatement ps = null;
        String sql = "UPDATE " + order.getClass().getSimpleName() + " SET status=? WHERE id=" + "'"+order.getId()+"'";
        ps = (PreparedStatement) cn.prepareStatement(sql);
        ps.setString(1, order.getStatus());
        ps.executeUpdate();
}

}
