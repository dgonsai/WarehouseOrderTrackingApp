import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JOptionPane;

//some of the methods contain system print lines for testing. these are not imperative to the program, but still helpful for testing, so they were left in
public class JDBC {
	//declaring database driver and the URL of the server of the database
	static final String JDBC_DRIVER="com.mysql.JDBC.Driver";
	static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/mydb";
	
	//User-name and password of the database
	static final String USER = "root";
	static final String PASS = "netbuilder";
	
	//all of the read functions (orders, purchaseorders, orderline etc. follow the same template with slight changes to the sql and variables
	//comments left on readProducts() should give a guidance as to how the other read functions work
	
	//reading the products from the database
	public ArrayList<String> readProducts(){
		Connection conn = null;
		Statement stmt = null;
		String sql2 = "SELECT * FROM Products";
		
		//array list for holding the string of a product (for displaying in the jlist on the mainframe)
		//the other arraylist is to contain all of the records returned from the ResultSet - ensures they fit into the product constructor
		
		ArrayList<String> productString = new ArrayList<String>(0);
		ArrayList<WarehouseProduct> productList= new ArrayList<WarehouseProduct>();	
		
		try{
			Class.forName("JDBC");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			//creating a connection to the database with the variables declared previously
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2); //running the sql to select all fields from the product table
			
			//storing all of the values found in the product table in variables that will be used to add to thea array
			//loop continues while the result set still has found records in it
			while (rs.next()) {							
				//storing all values in variables
				int productId = rs.getInt("ProductID");
				String prodName = rs.getString("ProductName");
				double  productPrice = rs.getDouble("ProductPrice");
				int stock = rs.getInt("StockLevel");
				double  height = rs.getInt("Height");
				double weight = rs.getInt("Weight");
				double width= rs.getInt("Width");
				double depth = rs.getInt("Depth");
				boolean porous = rs.getBoolean("Porous");
				int xLoc = rs.getInt("XLocation");
				int yLoc = rs.getInt("YLocation");
														
				//adding found products to the product arraylist (bound to constructor) then converting certain values to strings for display purposes
				//string is added to its own seperate arraylist - used for the jlist display
				productList.add(new WarehouseProduct(productId, prodName, productPrice, stock, height, width, weight, depth, porous, xLoc, yLoc));
				String product = "ID: " + Integer.toString(productId) + ", Product Name: "+ prodName + ", Stock: " + Integer.toString(stock)+ ", Porous Wared?: "+porous;
				productString.add(product);
			}
			rs.close();
		}
		//catching exceptions/errors if the program fails to connect to the database
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
				boolean workedOn = rs.getBoolean("WorkedOn");
				
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
				String order = "Order ID: " + Integer.toString(orderId) + ", Order Date: "+ datePlaced + ", Order Status: " + orderStatus + ", Worked On: "+ workedOn;
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
	
	public ArrayList<String> readPurchaseOrder(){
		Connection conn = null;
		Statement stmt = null;
		String sql2 = "SELECT * FROM PurchaseOrder";
		
		ArrayList<PurchaseOrder> poList= new ArrayList<PurchaseOrder>();
		ArrayList<String> poString = new ArrayList<String>(0);
		
		try{
			Class.forName("JDBC");
			System.out.println("Loading order catalogue...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int poId = rs.getInt("PurchaseOrderID");
				String supplierName = rs.getString("Supplier");
				String delivered = rs.getString("Delivered");
								
				poList.add(new PurchaseOrder(poId, supplierName, delivered));
				String purchase = "Purchase ID: " + Integer.toString(poId) + ", Supplier Name : "+ supplierName + ", Delivered?: " + delivered;
				poString.add(purchase);
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
		 return poString;	
	}
	
	public ArrayList<String> readOrderLine(){

		Connection conn = null;
		Statement stmt = null;
		
		int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID of the order you wish to see"));
		String sql2 = "SELECT * FROM orderline WHERE orderline.Orders_OrderID=" + orderID;			
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
				int xLoc = productRS.getInt("XLocation");
				int yLoc = productRS.getInt("YLocation");
			
				productList.add(new WarehouseProduct(productId, prodName, productPrice, stock, height, width, weight, depth, porous, xLoc, yLoc));
			
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
	
	public ArrayList<String> readPurchaseOrderLine(){
		Connection conn = null;
		Statement stmt = null;
		
		int poID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the purchase order ID of the order you wish to see"));
		String sql2 = "SELECT * FROM poline WHERE poline.PurchaseOrderID=" + poID;
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
				int xLoc = productRS.getInt("XLocation");
				int yLoc = productRS.getInt("YLocation");
				
				productList.add(new WarehouseProduct(productId, prodName, productPrice, stock, height, width, weight, depth, porous, xLoc, yLoc));
			
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
	
	//editing the status of an order (picking, packing etc.)
	public void editStatus(){
		Connection conn = null;
		Statement stmt = null;
		Statement stmt2 = null; //two statement variables used to run two update sql statements in the same method without having to close the connection
		int newLevel=0; //initialising some of the variables
		int prodId =0;
		int orderlineProductID = 0;
		
		//user inputs an order they wish to edit the status of
		int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID of the order you wish to change the status of"));
		
		//arraylists used to update the stock levels of the correct products in an orderline
		ArrayList<Integer> newStockLevels = new ArrayList<Integer>();
		ArrayList<Integer> productID = new ArrayList<Integer>();
		ArrayList<Integer> olProductID = new ArrayList<Integer>();

		String status =""; //initialising status - all variables initialised at the start of the method to avoid errors with initialising within the loop
		String sql2 = "SELECT * FROM orders, products, orderline WHERE orders.orderID=" + orderID + " AND orderline.products_productID = products.productID AND orderline.orders_orderID = " +orderID;
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to move the order with order ID "+orderID+" to the next stage?");		
		
		if(dialogResult==JOptionPane.NO_OPTION){
			return;
		}
		else{
			try{
				Class.forName("JDBC");
				System.out.println("Connecting to NB Gardens database...");
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				stmt2 = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);
				//if the status of an order is moved to picked (from waiting for process) stock updates depending on the orderline occur
				//variable newLevel used to calculate new levels of stock. nnew stock levels of all products involved in an order stored in an array (newStockLevels)
				//product and orderline data stored in seperate array to ensure product stock levels are individually changed
				while (rs.next()){
					status = rs.getString("orderStatus");
					if(status.equals("WAITINGFORPROCESS")){
						status="PICKED";
						prodId = rs.getInt("products.ProductID");
						orderlineProductID = rs.getInt("orderline.products_ProductID");
						int stock= rs.getInt("StockLevel");
						int quantity = rs.getInt("quantity");
						newLevel = stock - quantity;
						System.out.println("Product ID: "+prodId);
						System.out.println("Stock level: "+stock);
						System.out.println("Quantity: "+ quantity);
						System.out.println("New Level: "+ newLevel);
						newStockLevels.add(newLevel);
						productID.add(prodId);
						olProductID.add(orderlineProductID);
					}
					
					else if (status.equals("PICKED")){
						status="PACKED";
					}
					else if (status.equals("PACKED")){
						status="DISPATCHREADY";
					}
					else if (status.equals("DISPATCHREADY")){
						status="DISPATCHED";
					}
					else{
						System.out.println("Order has been dispatched!");
						return;
					}
				}
				rs.close();
				//update statement to change the order status to the next stage
				System.out.println(newStockLevels.toString());
				String updateStatus = "UPDATE orders SET orderStatus ='"+status+"'WHERE OrderID ='"+ orderID+"';";
				stmt.executeUpdate(updateStatus);
				
				//loop through the newStockLevels array and updating products with the new stock levels
				for (int i = 0; i<newStockLevels.size();i++){
					stmt2.executeUpdate("UPDATE products SET StockLevel ='"+newStockLevels.get(i)+ "' WHERE ProductID ='"+productID.get(i)+"';");
				}
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
				catch (SQLException se) { 
					
				}
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
	
	//editing the status of a purchase order (confirming it as delivered)
	//very similar to editStatus() in terms of updating new stock levels
	public void editPOStatus(){
		Connection conn = null;
		Statement stmt = null;
		Statement stmt2 = null;
		int newLevel=0;
		int prodId =0;
		int poProductID = 0;
		int poID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the purhcase order ID of the order you wish to change the status of"));
		ArrayList<Integer> newStockLevels = new ArrayList<Integer>();
		ArrayList<Integer> productID = new ArrayList<Integer>();
		ArrayList<Integer> purchaseOrderProdID = new ArrayList<Integer>();

		String status ="";
		String sql2 = "SELECT * FROM PurchaseOrder, products, poline WHERE purchaseOrder.PurchaseOrderID=" + poID + " AND poline.productID = products.productID AND poline.PurchaseOrderID = " +poID;
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to declare the puchase order with purchase order ID "+poID+" to delivered?");		
		
		if(dialogResult==JOptionPane.NO_OPTION){
			return;
		}
		else{
			try{
				Class.forName("JDBC");
				System.out.println("Connecting to NB Gardens database...");
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				stmt2 = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);
				
				while (rs.next()){

					status="true";
					prodId = rs.getInt("products.ProductID");
					poProductID = rs.getInt("poline.ProductID");
					int stock= rs.getInt("StockLevel");
					int quantity = rs.getInt("quantity");
					newLevel = stock + quantity;
					System.out.println("Product ID: "+prodId);
					System.out.println("Stock level: "+stock);
					System.out.println("Quantity: "+ quantity);
					System.out.println("New Level: "+ newLevel);
					newStockLevels.add(newLevel);
					productID.add(prodId);
					purchaseOrderProdID.add(poProductID);
					}
					rs.close();
					System.out.println(newStockLevels.toString());
					String updateStatus = "UPDATE PurchaseOrder SET Delivered ='"+status+"'WHERE PurchaseOrderID ='"+ poID+"';";
					stmt.executeUpdate(updateStatus);
				
					for (int i = 0; i<newStockLevels.size();i++){
						stmt2.executeUpdate("UPDATE products SET StockLevel ='"+newStockLevels.get(i)+ "' WHERE ProductID ='"+productID.get(i)+"';");
					}
				
				
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
				catch (SQLException se) { 
					
				}
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
	
	//removing stock from the database
	//gather all records in product table and remove stock based on inputted productID and quantity
	public void removeStock(){
		Connection conn = null;
		Statement stmt = null;
		int newStockLevel=0;
		int productID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the product ID of the product you wish to remove stock from"));
		String status = "";
		String sql2 = "SELECT * FROM products WHERE products.productID="+productID;
		int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the quantity you wish to remove from this product"));
			try{
				Class.forName("JDBC");
				System.out.println("Connecting to NB Gardens database...");
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);
				
				while (rs.next()){
					int stock = rs.getInt("StockLevel");
					if(quantity>stock){
						JOptionPane.showMessageDialog(null, "The quantity entered was too high!");
						return;
					}
					else{
					newStockLevel = stock-quantity;
					}
				}
				
				rs.close();
				
				System.out.println(status);
				String updateStatus = "UPDATE products SET StockLevel ='"+newStockLevel+"'WHERE ProductID ='"+ productID+"';";
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
	
	//adding a new order into the database
	public void createOrder(){
		Connection conn = null;
		Statement stmt = null;
		
		String sql4 = "SELECT OrderID FROM orders";
		
		ArrayList<Integer> oArray = new ArrayList<Integer>();
		
		try{
			Class.forName("JDBC");
			System.out.println("Connecting to NB Gardens database...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			
			//gathering all of the records in the order table and adding the order id to an array list
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql4);	
			while (rs.next()){
				int oID = rs.getInt("OrderID");
				oArray.add(oID);
			}
			
			//the order id of a new order is the size of an array + 1 - implied auto incremental method of making orders
			int OrderID = oArray.size()+1;
			
			//created orders will have the current date and time of when the order was generated
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date= new Date();
			String datePlaced = df.format(date);
			
			DateFormat dateFormat = new SimpleDateFormat("hh:mm");
	        Date time = new Date();
	        String timePlaced=dateFormat.format(time);
	        
			String oStatus = "WAITINGFORPROCESS"; 
			String workedOn = "false";
			
			//inserting all of the order information generated into the order table in the database
			System.out.println(OrderID);
			stmt.executeUpdate("INSERT INTO `orders`(OrderID,datePlaced,timePlaced,orderStatus,WorkedOn) VALUE('"+OrderID+"','"+datePlaced+"','"+timePlaced+"','"+oStatus+"','"+workedOn+"')");
			JOptionPane.showMessageDialog(null, "The ID for this order is OrderID: " + OrderID);
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
	
	//adding products to an order that has already been placed
	//allows for products to be added 1 by 1 to an order based on user input
	public void addToOrder(){
		Connection conn = null;
		Statement stmt = null;
		
		int OrderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID you wish to add products to"));
		String ProductID = ""; 
			
		//this part of the function doesn't work correctly - entering quit does quit the function, but there isn't a loop to keep addin gproducts until quit is entered
		while(!ProductID.equals("[quit]")){
			ProductID =JOptionPane.showInputDialog("Please enter the productID of the product you wish to to this order (enter [quit] if you wish to stop adding products");
			if (ProductID.equals("[quit]")){
				return;
			}
			else{
				int Quantity= Integer.parseInt(JOptionPane.showInputDialog("Please enter the quantity of the product you wish to acquire from this purchase order"));
				//parse the string entered for the product id and quantity to add to the database (in the orderline table)
				try{
					Class.forName("JDBC");
					System.out.println("Connecting to NB Gardens database...");
					conn=DriverManager.getConnection(DB_URL,USER,PASS);
					stmt = conn.createStatement();
					
					stmt.executeUpdate("INSERT INTO `orderline`(orders_OrderID,products_ProductID,Quantity) VALUE ('"+OrderID+"','"+ProductID+"','"+Quantity+"')");
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
	
	//adding a purchaseorder to the database
	//similar in code and structure as the createOrder() method
	public void addPurchaseOrder(){
		Connection conn = null;
		Statement stmt = null;
		
		String sql4 = "SELECT PurchaseOrderID FROM PurchaseOrder";
		
		String Supplier= JOptionPane.showInputDialog("Please enter the supplier name of the purchase order");
		ArrayList<Integer> poArray = new ArrayList<Integer>();

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
	
	//editing the working status of an order (whether it is being worked on or not [true or false])
	//similar to the function to edit the status of an order without removing stock from the database
	public void editWorkStatus(){
		Connection conn = null;
		Statement stmt = null;
		
		int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID of the order you wish to change the status of"));
		String status = "";
		String sql2 = "SELECT * FROM orders WHERE orders.orderID="+orderID;
				
			try{
				Class.forName("JDBC");
				System.out.println("Connecting to NB Gardens database...");
				conn=DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);
				
				//if an order isn't being worked on, you can work on it.
				//if it is worked on, dialog box will display to inform the user of this
				while (rs.next()){
					status = rs.getString("WorkedOn");
					if (status.equals("false")){
						status = "true";
					}
					else{
						System.out.println("This order is already being worked on!");
						return;
					}
				}
				
				rs.close();
				System.out.println(status);
				//updating the orders table with an order being worked on as being true
				String updateStatus = "UPDATE orders SET WorkedOn ='"+status+"'WHERE OrderID ='"+ orderID+"';";
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
	
	//adding products to a purchase order that has already been placed
	//similar to addToOrder() method in code and structure
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

	//travelling salesman algorithm to efficiently pick orders in a database
	//nearest neighbour (NN) approach used with x and y co-ordinates of a product to calculate the optimal route
	//this is not necessarily the optimal way of solving the TSP, but is a valid approach that has been used before
	public ArrayList<String> travellingSalesperson(){
		Connection conn = null;
		Statement stmt = null;
		ArrayList <Integer> visited = new ArrayList<Integer>(); //arraylist of all visited nodes in the NN
		visited.add(0);
		ArrayList <Integer> unvisitedX  = new ArrayList<Integer>(); //unvisited x co-ordinates
		ArrayList <Integer> unvisitedY = new ArrayList<Integer>(); //unvisited y co-ordinates
		
		//string arraylist to print results of NN to the jlist
		ArrayList <String> visitList = new ArrayList<String>();
		int visitedPointer = 0; //pointer used to always point to the currently visited product in the visited array
		
		int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID of the order you wish to see"));
		String sql4 = "SELECT * FROM products, orderline WHERE Orders_OrderID ="+orderID+" AND orderline.Products_ProductID = products.ProductID";
		
		try{
			Class.forName("JDBC");
			System.out.println("Loading order catalogue...");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
			ResultSet productRS =stmt.executeQuery(sql4);
			
			while(productRS.next()){
				int xLoc = productRS.getInt("XLocation");
				int yLoc = productRS.getInt("YLocation");
				unvisitedX.add(xLoc);
				unvisitedY.add(yLoc); 
				System.out.println(unvisitedX.toString());
				System.out.println(unvisitedY.toString());
				//store all of the x and y co-ordinates found in the result set of the SQL in the unvisitedX and unvisitedY arrays respectively
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
		
		int temporaryCounter = -1;	//counter to remove items from the array lists
		
		//algorithm done while size of one of the arrays is zero
		//both arrays are the same size as each product has a pair of co-ordinates, so this should not be an issue
		
		while(unvisitedY.size()!=0){
			int closestProduct = Integer.MAX_VALUE; //max value declaration - used when comparing values in the arrays
			
			//going through all of the array items
			for(int i=0; i<unvisitedY.size();i++){
				//storing a value calculated from the absolute value of position 0 of the visited array taken away from the x/y location of a product
				int tempX = Math.abs(unvisitedX.get(i)-visited.get(visitedPointer));
				int tempY = Math.abs(unvisitedY.get(i)-visited.get(visitedPointer));
			
				//if statement to compare the closestProduct
				//if the x and y points are both smaller than the max value in the array, they are added to the visited array
				if(tempX<closestProduct&&tempY<closestProduct){
					temporaryCounter = i;
					visitList.add("Product you should get first/next is located at: " +String.valueOf(tempX) + ","+String.valueOf(tempY));
					visited.add(closestProduct);
					unvisitedX.remove(temporaryCounter); //take 1 from the array
					unvisitedY.remove(temporaryCounter); //take 1 from the array
				}
			}
			}
			return visitList;	 // return the visitList for the JList to display
			}
	}