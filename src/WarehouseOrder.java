import java.util.ArrayList;

public class WarehouseOrder{
	private int ordID;
	private String datePlaced;
	private String timePlaced;
	private orderStatus orderStatus;
	public ArrayList<WarehouseOrder> orderList= new ArrayList<WarehouseOrder>();
	
	
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
	//convert the contents of an object into a string
	public String toString(){
		return "Order ID: " + this.getID() + ", Date Placed: " + this.getDate() + ", Time Placed: " + this.getTime() + ", Order Status: " + this.getStatus() +"\n";
	}
	
}
	
	
