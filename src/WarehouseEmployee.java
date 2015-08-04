import java.util.ArrayList;

public class WarehouseEmployee {
	private int empID;
	private String empName;
		
	public int getID(){
		return empID;
	}
	
	public void setID(int eID){
		empID=eID;
	}
	
	public String getName(){
		return empName;
	}
	
	public void setName(String eName){
		empName=eName;
	}
	
	public WarehouseEmployee(int empID,String empName){
		this.empID = empID;
		this.empName = empName;
	}
	
	
	public static void main(String[] args) {
		ArrayList<WarehouseEmployee> empList= new ArrayList<WarehouseEmployee>();
		WarehouseEmployee e1 = new WarehouseEmployee(1,"Hans");
		empList.add(e1);
		WarehouseEmployee e2 = new WarehouseEmployee(2,"Steve");
		empList.add(e2);
		
		for (int i=0; i<empList.size();i++){
			System.out.println(empList.get(i));
		}

	}

}
