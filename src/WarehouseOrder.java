import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WarehouseOrder{
	private int ordID;
	private String datePlaced;
	private String timePlaced;
	private orderStatus orderStatus;
	
	
	
	//get-set methods for attributes
	public enum orderStatus{
		WAITINGFORPROCESS,PICKED, PACKED, DISPATCHREADY, DISPATCHED;
	}
	
	public orderStatus getStatus(){
		return orderStatus;
	}
	
	public void setStatus(orderStatus oStatus){
		this.orderStatus = oStatus;
	}
	
	public int getID(){
		return ordID;
	}
	
	public void setID(int oID){
		ordID=oID;
	}
	
	public String getDate(){
		return datePlaced;
	}
	
	public void setDate(String orderDate){
		datePlaced=orderDate;
	}
	
	public String getTime(){
		return timePlaced;
	}
	
	public void setTime(String orderTime){
		timePlaced=orderTime;
	}
	
	//order constructor
	public WarehouseOrder(int ordID, String datePlaced, String timePlaced, orderStatus orderStatus){
		this.ordID=ordID;
		this.datePlaced = datePlaced;
		this.timePlaced = timePlaced;
		this.orderStatus = orderStatus;
	}
	
	//for displaying order in console (test)
	public String toString(){
		return "Order ID: " + this.getID() + ", Date Placed: " + this.getDate() + ", Time Placed: " + this.getTime() + ", Order Status: " + this.getStatus();
	}
	
	//array list where orders are stored
	ArrayList<WarehouseOrder> orderList= new ArrayList<WarehouseOrder>();
	
	//Listing the orders in the array
	public void OrderList() {
		orderList.add(new WarehouseOrder(1, "1/1/2015", "13:35", orderStatus.PICKED));
		orderList.add(new WarehouseOrder(2, "2/2/2015", "13:34", orderStatus.DISPATCHREADY));
		System.out.println(orderList);
	}
	
	
	//adding an order to the array
	public void addOrder() throws IOException{
		//import a reader to read user input
		BufferedReader input = new BufferedReader(new InputStreamReader (System.in));
		
		//instantiating an order and the array
		WarehouseOrder order = new WarehouseOrder(ordID, datePlaced, timePlaced, orderStatus);
	
		
		//adding it to the array
		System.out.println("Enter Order ID");
		ordID = Integer.parseInt(input.readLine());
		
		System.out.println("Enter the date placed");
		datePlaced=input.readLine();

		System.out.println("Enter time placed");
		timePlaced=input.readLine();
		
		orderStatus= orderStatus.WAITINGFORPROCESS;

		orderList.add(order);
	}
	
	public void searchOrder() throws IOException{
		boolean found = false;
		int position = 0;
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter the id of the order you are searching for");
		position = Integer.parseInt(input.readLine());
		

		for (int i=0; i<orderList.size(); i++){
			if (this.orderList.get(i).getID()==position){
				found = true;
				position = i;
				break;
			}
		}
		if(found){
			System.out.println("Found!");
			System.out.println(this.orderList.get(position));
		}
		else{
			System.out.println("Order not found!");
		}
	}

}
	
	
