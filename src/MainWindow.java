import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;



public class MainWindow extends JFrame {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private JList list;
		
	//initialising and design of the main menu GUI
	private void designGUI(){
		mainFrame = new JFrame("Main Menu");
		mainFrame.setSize(550, 400);
		mainFrame.setLayout(new GridLayout(3,1));
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		headerLabel = new JLabel ("",JLabel.CENTER);
		list = new JList<String>(listModel);
		add (new JScrollPane(list));
		list.setVisibleRowCount(4);
				
		mainFrame.addWindowListener(new WindowAdapter(){
			public void WindowClose(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(list);
		mainFrame.add(list);
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
	
	private void createButtons(){
		headerLabel.setText("NB Garden - Warehouse App");
		JButton poListButton = new JButton("View Purchase Orders made");
		JButton orderMenuButton = new JButton("View Items in an Order");
		JButton orderListButton = new JButton("Order List");
		JButton productListButton = new JButton("Product List");
		JButton addPOButton = new JButton("Add New Purchase Order");
		JButton editPOButton = new JButton("Add items to existing Purchase Order");
		JButton changeStatus = new JButton("Change Order Status");
				
		poListButton.setActionCommand("Purchase Order List");
		orderMenuButton.setActionCommand("View Items in order");
		orderListButton.setActionCommand("Order List");
		productListButton.setActionCommand("Product List");
		addPOButton.setActionCommand("Add New Purchase Order");
		editPOButton.setActionCommand("Edit Purchase Order");
		changeStatus.setActionCommand("Change Order Status");
				
		poListButton.addActionListener(new ButtonClick());
		orderMenuButton.addActionListener(new ButtonClick());
		orderListButton.addActionListener(new ButtonClick());
		productListButton.addActionListener(new ButtonClick());
		addPOButton.addActionListener(new ButtonClick());
		editPOButton.addActionListener(new ButtonClick());
		changeStatus.addActionListener(new ButtonClick());
		
		controlPanel.add(orderListButton);
		controlPanel.add(productListButton);
		controlPanel.add(orderMenuButton);
		controlPanel.add(poListButton);
		controlPanel.add(addPOButton);
		controlPanel.add(editPOButton);
		controlPanel.add(changeStatus);
		
		poListButton.setAlignmentX(LEFT_ALIGNMENT);
		orderMenuButton.setAlignmentX(LEFT_ALIGNMENT);
		orderListButton.setAlignmentX(LEFT_ALIGNMENT);
		productListButton.setAlignmentX(LEFT_ALIGNMENT);
		
		mainFrame.setVisible(true);
	}
	
	private class ButtonClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String command = ae.getActionCommand();
			switch (command){
				
				//Displaying all purchase order lines
				case "Purchase Order List":
					DisplayPurchaseOrderList();
					break;
					
				//View items on an entered order
				case "View Items in order":
					DisplayOrderLine();
					break;
					
				//List all orders on the database
				case "Order List": 
					DisplayOrderList();
					break;
					
				//List all products on the database
				case "Product List":
					DisplayProductList();
					break;
				
				//Add a new purchase order
				case "Add New Purchase Order":
					addPurchaseOrder();
					break;
				
				//Add items to an existing purchase order
				case "Edit Purchase Order":
					addPOProducts();
					break;
				
				//Change the status of an order
				case "Change Order Status":
					changeStatus();
					break;
			}
		}
	}
	
	
	public void DisplayOrderList(){
		JDBC jdbc = new JDBC();
		jdbc.readOrders();
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		for (String orderString: jdbc.readOrders()){
			listModel.addElement(orderString);
		}
	}
	
	public void DisplayProductList(){
		JDBC jdbc = new JDBC();
		jdbc.readProducts();
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		for (String productString: jdbc.readProducts()){
			listModel.addElement(productString);
		}
			
	}
	
	public void DisplayOrderLine(){
		JDBC jdbc = new JDBC();	
		jdbc.readOrderLine();
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		for (String olList: jdbc.readOrderLine()){
			listModel.addElement(olList);
		}		
	}
	
	public void DisplayPurchaseOrderList(){
		JDBC jdbc = new JDBC();
		jdbc.readPurchaseOrder();
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		for (String productString: jdbc.readPurchaseOrder()){
			listModel.addElement(productString);
		}		
	}
	
	public void addPurchaseOrder(){
		JDBC jdbc = new JDBC();
		jdbc.addPurchaseOrder();
		String option = JOptionPane.showInputDialog("Would you like to add products to a purchase order? (y/n)");
		if (option.equals("y")){
			addPOProducts();
		}
		else{
			return;
		}
	}
	
	public void addPOProducts(){
		JDBC jdbc = new JDBC();
		jdbc.addToPO();
	}
	
	public void changeStatus(){
		JDBC jdbc = new JDBC();
		jdbc.editStatus();
	}
	
	public MainWindow() {
		designGUI();
		createButtons();
	}

}
