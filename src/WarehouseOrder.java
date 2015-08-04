import java.util.ArrayList;

public class WarehouseOrder {
	private int ordID;
	private String datePlaced;
	private String timePlaced;
	private orderStatus orderStatus;
	
	public enum orderStatus{
		PICKED, PACKED, DISPATCHREADY, DISPATCHED;
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
	
	public WarehouseOrder(int ordID, String datePlaced, String timePlaced, orderStatus orderStatus){
		this.ordID=ordID;
		this.datePlaced = datePlaced;
		this.timePlaced = timePlaced;
		this.orderStatus = orderStatus;
	}
	
	public String toString(){
		return "Order ID: " + this.getID() + ", Date Placed: " + this.getDate() + ", Time Placed: " + this.getTime() + ", Order Status: " + this.getStatus();
	}
	
	
	public void OrderList() {
		ArrayList<WarehouseOrder> orderList= new ArrayList<WarehouseOrder>();
		WarehouseOrder ord1 = new WarehouseOrder(1, "1/1/2015", "13:35", orderStatus.PICKED);
		orderList.add(ord1);
		orderList.add(new WarehouseOrder(2, "2/2/2015", "13:34", orderStatus.DISPATCHREADY));
		System.out.println(orderList);
	}
	
	public static void main (String [] args){
	
	}

}
