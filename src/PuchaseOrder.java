
public class PuchaseOrder {
	private int purchaseOrderID;
	private String supplierName;
	private int quantity;
	
	public int getPurchaseID(){
		return purchaseOrderID;
	}
	
	public void setPurchaseID(int purchaseID){
		purchaseOrderID = purchaseID;
	}
	
	public String getSupplier(){
		return supplierName;
	}
	
	public void setSupplier(String supplier){
		supplierName = supplier;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quant){
		quantity = quant;
	}
	
	public PuchaseOrder(int PurchaseOrderID, String supplierName, int quantity){
		this.purchaseOrderID = PurchaseOrderID;
		this.supplierName = supplierName;
		this.quantity = quantity;
	}
}
