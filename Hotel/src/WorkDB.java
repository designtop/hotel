import java.sql.SQLException;


public class WorkDB {
	
	public final static String NAMEDB = "Hotel";
	
	public static void installDB(String path, String login, String password) throws SQLException{
		DB db = new DB (path, "",
				login, password);
		db.update("CREATE DATABASE "+NAMEDB);
		db.close();
		 db = new DB (path, NAMEDB,
				login, password);
		 
		db.update("CREATE TABLE orders(id MEDIUMINT NOT NULL AUTO_INCREMENT," +
				" name VARCHAR(30)," +
				" username VARCHAR(30)," +
				" persons VARCHAR(30)," +
				" roomtype VARCHAR(30)," +
				" days VARCHAR(30)," +
				" status VARCHAR(30), " +
				" PRIMARY KEY(id))");
		
		db.update("CREATE TABLE users(username VARCHAR(30) PRIMARY KEY," +
				" password VARCHAR(30))");
		db.close();
		
	}
	public static void deleteDB(String path, String login, String password) throws SQLException{
		DB db = new DB (path, "",
				login, password);
		db.update("DROP DATABASE "+NAMEDB);
		db.close();
	}
}
