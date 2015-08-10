
public class PurchaseOrderLine {
	private int purchaseOrderID;
	private int productID;
	private int quantity;
	
	
	public int getPuchaseID(){
		return purchaseOrderID;
	}
	
	public void setPurchaseID(int poID){
		purchaseOrderID = poID;
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
	

	public PurchaseOrderLine(int purchaseOrderID, int productID, int quantity){
		this.purchaseOrderID = purchaseOrderID;
		this.productID = productID;
		this.quantity = quantity;
	}
}
