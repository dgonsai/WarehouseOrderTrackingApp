
public class WarehouseOrderLine {
	private int orderID;
	private int productID;
	private int quantity;
	private int subtotal;
	
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
	
	public int getSubtotal(){
		return subtotal;
	}
	
	public void setSubtotal(int sub){
		subtotal = sub;
	}
	
	public WarehouseOrderLine(int orderID, int productID, int quantity, int subtotal){
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	

}
