import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class JDBC {
	
	
	//declaring database driver and the URL of the server of the database
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
					boolean workedOn = rs.getBoolean("WorkedOn?");
					
					if(orderStatusChoice.equals("WAITINGFORPROCESS")){
						orderStatus=WarehouseOrder.orderStatus.WAITINGFORPROCESS;
					}
					else if (orderStatusChoice.equals("PICKED")){
						orderStatus=WarehouseOrder.orderStatus.PICKED;
					}
					else if (orderStatusChoice.equals("PACKED")){
						orderStatus=WarehouseOrder.orderStatus.PACKED;
					}
					else if (orderStatusChoice.equals("DISPATCHREADY")){
						orderStatus=WarehouseOrder.orderStatus.DISPATCHREADY;
					}
					else{
						orderStatus=WarehouseOrder.orderStatus.DISPATCHED;
					}
					
					orderList.add(new WarehouseOrder(orderId, datePlaced, timePlaced, orderStatus, workedOn));
					
					String order = "ID: " + Integer.toString(orderId) + ", Order Date: "+ datePlaced + ", Order Status: " + orderStatus + ", Worked On?: "+ workedOn;
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
		
	public ArrayList<String> readOrderLine(){

		Connection conn = null;
		Statement stmt = null;
		
		int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID of the order you wish to see"));
		String sql2 = "SELECT * FROM orderline WHERE orderline.Orders_OrderID=" + orderID;
		String sql3 = "SELECT * FROM Orders WHERE orderline.Orders_OrderID="+orderID;
		String sql4 = "SELECT * FROM products, orderline WHERE Orders_OrderID ="+orderID+" AND orderline.Products_ProductID = products.ProductID";
				
		ArrayList<WarehouseProduct> productList = new ArrayList<WarehouseProduct>();
		ArrayList<String> productString = new ArrayList<String>(0);
		ArrayList<WarehouseOrderLine> orderLineList = new ArrayList<WarehouseOrderLine>();
		ArrayList<String>olList = new ArrayList<String>(0);
		
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
			
				//String productSQL = "SELECT productPrice FROM Products WHERE orderline.Products_ProductID = Products.productID";
				
				orderLineList.add(new WarehouseOrderLine(orderId, productId, quantity));
				System.out.println("Order ID: " + orderId + ", Product ID: " + productId + ", Quantity: " + quantity);
				String orderLine = "Order ID: " + Integer.toString(orderId) + ", Product ID: "+ Integer.toString(productId) + ", Quantity: " + Integer.toString(quantity);
				olList.add(orderLine);
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
		
		try{
			Class.forName("JDBC");
			System.out.println("Loading order catalogue...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
			ResultSet productRS =stmt.executeQuery(sql4);
			
			while(productRS.next()){
				int productId = productRS.getInt("ProductID");
				String prodName = productRS.getString("ProductName");
				double  productPrice = productRS.getDouble("ProductPrice");
				int stock = productRS.getInt("StockLevel");
				double  height = productRS.getInt("Height");
				double weight = productRS.getInt("Weight");
				double width= productRS.getInt("Width");
				double depth = productRS.getInt("Depth");
				boolean porous = productRS.getBoolean("Porous");
			
				productList.add(new WarehouseProduct(productId, prodName, productPrice, stock, height, width, weight, depth, porous));
			
				String product = "ID: " + Integer.toString(productId) + ", Product Name: "+ prodName + ", Stock: " + Integer.toString(stock)+ ", Porous Wared?: "+porous;
				productString.add(product);
				System.out.println("Product Name: "+prodName + ", Price: "+productPrice);
			}
			productRS.close();
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
		return olList;
	}
	
	public ArrayList<String> readPurchaseOrder(){
		Connection conn = null;
		Statement stmt = null;
		
		int poID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID of the order you wish to see"));
		String sql2 = "SELECT * FROM poline WHERE poline.PurchaseOrderID=" + poID;
		String sql3 = "SELECT * FROM purchaseorder WHERE poline.Orders_OrderID="+poID;
		String sql4 = "SELECT * FROM products, poline WHERE PurchaseOrderID ="+poID+" AND poline.ProductID = products.ProductID";
				
		ArrayList<PurchaseOrderLine> poLineList= new ArrayList<PurchaseOrderLine>();	
		ArrayList<String> poLineString = new ArrayList<String>(0);
		ArrayList<WarehouseProduct> productList = new ArrayList<WarehouseProduct>();
		ArrayList<String> productString = new ArrayList<String>(0);
		
		try{
			Class.forName("JDBC");
			System.out.println("Loading order catalogue...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			
			while (rs.next()) {
				int purchaseId = rs.getInt("PurchaseOrderID");
				int productId = rs.getInt("ProductID");
				int quantity = rs.getInt("Quantity");
				
				poLineList.add(new PurchaseOrderLine(purchaseId, productId, quantity));
				System.out.println("Order ID: " + purchaseId + ", Product ID: " + productId + ", Quantity: " + quantity);
				String poLine = "Order ID: " + Integer.toString(purchaseId) + ", Product ID: "+ Integer.toString(productId) + ", Quantity: " + Integer.toString(quantity);
				poLineString.add(poLine);
				
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
		
		try{
			Class.forName("JDBC");
			System.out.println("Loading order catalogue...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
			ResultSet productRS =stmt.executeQuery(sql4);
			
			while(productRS.next()){
				int productId = productRS.getInt("ProductID");
				String prodName = productRS.getString("ProductName");
				double  productPrice = productRS.getDouble("ProductPrice");
				int stock = productRS.getInt("StockLevel");
				double  height = productRS.getInt("Height");
				double weight = productRS.getInt("Weight");
				double width= productRS.getInt("Width");
				double depth = productRS.getInt("Depth");
				boolean porous = productRS.getBoolean("Porous");
														
				productList.add(new WarehouseProduct(productId, prodName, productPrice, stock, height, width, weight, depth, porous));
			
				String product = "ID: " + Integer.toString(productId) + ", Product Name: "+ prodName + ", Stock: " + Integer.toString(stock)+ ", Porous Wared?: "+porous;
				productString.add(product);
				System.out.println("Product Name: "+prodName + ", Price: "+productPrice);
			}
			productRS.close();
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
		return poLineString;
		
	}
	
	public void editStatus(){
		Connection conn = null;
		Statement stmt = null;
		int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID of the order you wish to change the status of"));
		String status ="";
		String sql2 = "SELECT * FROM orders WHERE orders.orderID=" + orderID;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to move the order with order ID "+orderID+" to the next stage?");
		if(dialogResult==JOptionPane.NO_OPTION){
			return;
		}
		else{
			try{
				Class.forName("JDBC");
				System.out.println("Connecting to NB Gardens database...");
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);
				
				while (rs.next()){
					status = rs.getString("orderStatus");
					System.out.println(status);
					
					if(status.equals("WAITINGFORPROCESS")){
						status="PICKED";
					}
					else if (status.equals("PICKED")){
						status="PACKED";
					}
					else if (status.equals("PACKED")){
						status="DISPATCHREADY";
					}
					else if (status.equals("DISPATCHREADY")){
						status="DISPATCHED";
						productStockUpdate();
					}
					else{
						System.out.println("Order has been dispatched!");
						return;
					}
					
				}
				rs.close();
				
				String updateStatus = "UPDATE orders SET orderStatus ='"+status+"'WHERE OrderID ='"+ orderID+"';";
				stmt.executeUpdate(updateStatus);

				System.out.println("IT IS DONE!!!");
				return;
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
	
	public void addPurchaseOrder(){
		Connection conn = null;
		Statement stmt = null;
		
		String sql4 = "SELECT PurchaseOrderID FROM PurchaseOrder";
		
		String Supplier= JOptionPane.showInputDialog("Please enter the supplier name of the purchase order");
		ArrayList poArray = new ArrayList();
		
		try{
			Class.forName("JDBC");
			System.out.println("Connecting to NB Gardens database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
	
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql4);	
			while (rs.next()){
				int poID = rs.getInt("PurchaseOrderID");
				
				poArray.add(poID);
			}
			
			int PurchaseOrderID = poArray.size()+1;
			
			System.out.println(PurchaseOrderID);
			stmt.executeUpdate("INSERT INTO `PurchaseOrder`(PurchaseOrderID,Supplier) VALUE('"+PurchaseOrderID+"','"+Supplier+"')");
			JOptionPane.showMessageDialog(null, "The ID for this purchase order is PurchaseOrderID:" + PurchaseOrderID);
			System.out.println("IT IS DONE");
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

	public void productStockUpdate(){
		Connection conn = null;
		Statement stmt = null;
		
	}
	
	public void addToPO(){

		Connection conn = null;
		Statement stmt = null;
		
		int PurchaseOrderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the purchase order ID you wish to add products to"));
		String ProductID = ""; 
			
		
		while(!ProductID.equals("[quit]")){
			ProductID =JOptionPane.showInputDialog("Please enter the productID of the product you wish to acquire from this purchase order (enter [quit] if you wish to stop adding products");
			if (ProductID .equals("[quit]")){
				return;
			}
			else{
				
			
			int Quantity= Integer.parseInt(JOptionPane.showInputDialog("Please enter the quantity of the product you wish to acquire from this purchase order"));
			String sql2 = "SELECT * FROM POLine WHERE POLine.PuchaseOrderID=" + PurchaseOrderID;
			
			try{
				Class.forName("JDBC");
				System.out.println("Connecting to NB Gardens database...");
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				
				stmt.executeUpdate("INSERT INTO `POLine`(PurchaseOrderID,ProductID,Quantity) VALUE ('"+PurchaseOrderID+"','"+ProductID+"','"+Quantity+"')");
				System.out.println("IT IS DONE!!!");
				return;
				
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
	}
}

