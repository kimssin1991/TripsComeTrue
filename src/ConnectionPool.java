
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
	static Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "four";
	String pass = "4444";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	private ConnectionPool() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
		
	}
	public static Connection getConnection() throws Exception{
		if (con == null) {
			new ConnectionPool();
		}
		return con;
	}
}
