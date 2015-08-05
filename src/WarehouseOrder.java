import java.util.ArrayList;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

import javax.swing.JList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

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
	
	//Listing the orders in the array
	public void OrderList() {
		//dummy orders
		orderList.add(new WarehouseOrder(1, "1/1/2015", "13:35", orderStatus.PICKED));
		orderList.add(new WarehouseOrder(2, "2/2/2015", "13:34", orderStatus.DISPATCHREADY));
		System.out.println(orderList);
		JList list = new JList(orderList.toArray());
		
	}
		
	//adding an order to the array
	public void addOrder() throws IOException{

		//import a reader to read user input
		//boolean newOrder = false; //boolean to determine an order has been entered
		BufferedReader input = new BufferedReader(new InputStreamReader (System.in));
			
		
		ordID = ordID+1;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date datePlaced = new Date();
	    String dateString = dateFormat.format(datePlaced);

		LocalTime timePlaced = LocalTime.now();
		System.out.println(timePlaced);
		String timeString = timePlaced.toString();
		
		orderStatus= orderStatus.WAITINGFORPROCESS;
		
		orderList.add(new WarehouseOrder(ordID, dateString,timeString,orderStatus));
		System.out.println(orderList);
		return;
		
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
	
	
