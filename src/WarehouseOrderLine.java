
public class WarehouseOrderLine {
	private int orderID;
	private int productID;
	private int quantity;
	
	public int getOrderID(){
		return orderID;
	}
	
	public void setOrderID(int oID){
		orderID = oID;
	}
	
	public int productID(){
		return productID;
	}
	
	public void setProductID(int pID){
		productID = pID;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int orderLineQuantity){
		quantity = orderLineQuantity;
	}
	
	public WarehouseOrderLine(int orderID, int productID, int quantity){
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
	}
	

}
