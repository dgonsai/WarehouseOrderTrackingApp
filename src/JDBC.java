import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	
	static final String JDBC_DRIVER="com.mysql.JDBC.Driver";
	static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/nbgardens";
	
	static final String USER = "root";
	static final String PASS = "netbuilder";
	
	public void readProducts(){
		Connection conn = null;
		Statement stmt = null;
		String sql2 = "SELECT ProductID, ProductName, ProductPrice, StockLevel, Height, Weight, Width, Depth, Porous FROM Products";
		
		try{
			Class.forName("JDBC");
			System.out.println("Connecting to NB Gardens database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
	
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
					int productId = rs.getInt("ProductID");
					String prodName = rs.getString("ProductName");
					int productPrice = rs.getInt("ProductPrice");
					int stock = rs.getInt("StockLevel");
					int height = rs.getInt("Height");
					int weight = rs.getInt("Weight");
					int width= rs.getInt("Width");
					int depth = rs.getInt("Depth");
					String porous = rs.getString("Porous");
					System.out.println("Product ID: " + productId + ", Product name: " + prodName);
				}
				rs.close();

			}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
			  if (stmt != null)
			  conn.close();
			} 
			catch (SQLException se) { }
				try {
					if (conn != null)
						conn.close();
				} 
				catch (SQLException se) {
					se.printStackTrace();
					System.out.println("Goodbye!");
				}
		  }
		 
	} 

	public void readOrders(){
		Connection conn = null;
		Statement stmt = null;
		String sql2 = "SELECT OrderID, datePlaced, timePlaced, orderStatus FROM Orders";
		
		try{
			Class.forName("JDBC");
			System.out.println("Connecting to NB Gardens database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
	
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
					int orderId = rs.getInt("OrderID");
					String datePlaced = rs.getString("datePlaced");
					String timePlaced = rs.getString("timePlaced");
					String orderStatus = rs.getString("orderStatus");
					System.out.println("Order ID: " + orderId + ", Order Status: " + orderStatus + " (Date/Time Placed: " + datePlaced + "/" + timePlaced);
				}
				rs.close();

			}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
			  if (stmt != null)
			  conn.close();
			} 
			catch (SQLException se) { }
				try {
					if (conn != null)
						conn.close();
				} 
				catch (SQLException se) {
					se.printStackTrace();
					System.out.println("Goodbye!");
				}
		  }
		 
	} 
}

