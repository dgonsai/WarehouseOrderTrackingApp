import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBC {
	
	
	//declaring database driver and the url of the server of the database
	static final String JDBC_DRIVER="com.mysql.JDBC.Driver";
	static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/mydb";
	
	//User-name and password of the database
	static final String USER = "root";
	static final String PASS = "netbuilder";
	
	//reading the products from the database
	public ArrayList<String> readProducts(){
		Connection conn = null;
		Statement stmt = null;
		String sql2 = "SELECT * FROM Products";
		
		ArrayList<String> productString = new ArrayList<String>(0);
		ArrayList<WarehouseProduct> productList= new ArrayList<WarehouseProduct>();	
		
		try{
			Class.forName("JDBC");
			System.out.println("Connecting to NB Gardens database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
	
			System.out.println("Loading product catalogue...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
										
				int productId = rs.getInt("ProductID");
				String prodName = rs.getString("ProductName");
				double  productPrice = rs.getDouble("ProductPrice");
				int stock = rs.getInt("StockLevel");
				double  height = rs.getInt("Height");
				double weight = rs.getInt("Weight");
				double width= rs.getInt("Width");
				double depth = rs.getInt("Depth");
				boolean porous = rs.getBoolean("Porous");
														
				productList.add(new WarehouseProduct(productId, prodName, productPrice, stock, height, width, weight, depth, porous));
							
		
				String product = "ID: " + Integer.toString(productId) + ", Product Name: "+ prodName + ", Stock: " + Integer.toString(stock)+ ", Porous Wared?: "+porous;
				productString.add(product);
								
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
		return productString;
		 
	} 
	
	public ArrayList<String> readOrders(){
		
		Connection conn = null;
		Statement stmt = null;
		String sql2 = "SELECT * FROM Orders";
		
		ArrayList<WarehouseOrder> orderList= new ArrayList<WarehouseOrder>();	
		WarehouseOrder.orderStatus orderStatus;
		ArrayList<String> orderString = new ArrayList<String>(0);
		
		try{
			Class.forName("JDBC");
			System.out.println("Loading order catalogue...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
					int orderId = rs.getInt("OrderID");
					String datePlaced = rs.getString("datePlaced");
					String timePlaced = rs.getString("timePlaced");
					String orderStatusChoice = rs.getString("orderStatus");
					
					if(orderStatusChoice=="WAITINGFORPROCESS"){
						orderStatus=WarehouseOrder.orderStatus.WAITINGFORPROCESS;
					}
					else if (orderStatusChoice=="PICKED"){
						orderStatus=WarehouseOrder.orderStatus.PICKED;
					}
					else if (orderStatusChoice=="PACKED"){
						orderStatus=WarehouseOrder.orderStatus.PACKED;
					}
					else if (orderStatusChoice=="DISPATCHREADY"){
						orderStatus=WarehouseOrder.orderStatus.DISPATCHREADY;
					}
					else{
						orderStatus=WarehouseOrder.orderStatus.DISPATCHED;
					}
					
					orderList.add(new WarehouseOrder(orderId, datePlaced, timePlaced, orderStatus));
					
					String order = "ID: " + Integer.toString(orderId) + ", Order Date: "+ datePlaced + ", Order Status: " + orderStatus;
					orderString.add(order);
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
		 return orderString;
	} 
		
	public void readOrderLine(){
		Connection conn = null;
		Statement stmt = null;
		
		//int orderNumber = list.getSelectedIndex();
		
		String sql2 = "SELECT * FROM orderline WHERE Orders_OrderID="; //+ order index
		
		try{
			Class.forName("JDBC");
			System.out.println("Loading order catalogue...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
	
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
					int orderId = rs.getInt("Orders_OrderID");
					int productId = rs.getInt("Products_ProductID");
					int quantity = rs.getInt("Quantity");
					int delivery = rs.getInt("deliveryCost");
					System.out.println("Order ID: " + orderId + ", Product ID: " + productId + ", Quantity: " + quantity + ", Delivery Cost: " + delivery);
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

