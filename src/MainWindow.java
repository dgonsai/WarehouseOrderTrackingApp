import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		mainFrame.setSize(600, 400);
		mainFrame.setLayout(new GridLayout(3,1));
		
		//list model used to display objects in arrays as visible strings
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
		
		//adding the previously declared list and JPanel on the JFrame and making it visible
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(list);
		mainFrame.add(list);
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
	
	private void createButtons(){
		
		//declaring all of the buttons on the application
		headerLabel.setText("NB Garden - Warehouse App");
		JButton poButton = new JButton("Purchase Order List");
		JButton poListButton = new JButton("View Items in a Purchase Order");
		JButton orderMenuButton = new JButton("View Items in an Order");
		JButton orderListButton = new JButton("Order List");
		JButton productListButton = new JButton("Product List");
		JButton addPOButton = new JButton("Add New Purchase Order");
		JButton editPOButton = new JButton("Add items to existing Purchase Order");
		JButton changeStatus = new JButton("Change Order Status");
		JButton travellingSalesperson = new JButton("Calculate route");
		JButton createOrder = new JButton("Create Order");
		JButton addToOrder = new JButton("Add items to existing Order");	
		
		//setting action commands for the switch-case statement - used to call the buttons
		poButton.setActionCommand("Purchase Order List");
		poListButton.setActionCommand("View Items in Purchase Order");
		orderMenuButton.setActionCommand("View Items in order");
		orderListButton.setActionCommand("Order List");
		productListButton.setActionCommand("Product List");
		addPOButton.setActionCommand("Add New Purchase Order");
		editPOButton.setActionCommand("Edit Purchase Order");
		changeStatus.setActionCommand("Change Order Status");
		travellingSalesperson.setActionCommand("Calculate route");
		createOrder.setActionCommand("Add new Order");
		addToOrder.setActionCommand("Edit Order");
		
		//adding action listeners to the buttons - when they're clicked, the button click method is called
		createOrder.addActionListener(new ButtonClick());
		poButton.addActionListener(new ButtonClick());
		poListButton.addActionListener(new ButtonClick());
		orderMenuButton.addActionListener(new ButtonClick());
		orderListButton.addActionListener(new ButtonClick());
		productListButton.addActionListener(new ButtonClick());
		addPOButton.addActionListener(new ButtonClick());
		editPOButton.addActionListener(new ButtonClick());
		changeStatus.addActionListener(new ButtonClick());
		addToOrder.addActionListener(new ButtonClick());
		travellingSalesperson.addActionListener(new ButtonClick());
		
		//adding the buttons to the JPanel
		controlPanel.add(createOrder);
		controlPanel.add(addToOrder);
		controlPanel.add(orderListButton);
		controlPanel.add(productListButton);
		controlPanel.add(poButton);
		controlPanel.add(orderMenuButton);
		controlPanel.add(poListButton);
		controlPanel.add(addPOButton);
		controlPanel.add(editPOButton);
		controlPanel.add(changeStatus);
		controlPanel.add(travellingSalesperson);
		
		//attempting to align the buttons on the panel
		poListButton.setAlignmentX(LEFT_ALIGNMENT);
		orderMenuButton.setAlignmentX(LEFT_ALIGNMENT);
		orderListButton.setAlignmentX(LEFT_ALIGNMENT);
		productListButton.setAlignmentX(LEFT_ALIGNMENT);
		
		mainFrame.setVisible(true);
	}
	
	private class ButtonClick implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent ae){
			//the command string will get an action command based on what is clicked on the JPanel
			String command = ae.getActionCommand();
			switch (command){
				
				//Displaying all purchase order lines
				case "Add new Order":
					CreateOrder();
					break;
				
				case "View Items in Purchase Order":
					DisplayPurchaseOrderList();
					break;
				
				case "Purchase Order List":
					DisplayPurchaseOrder();
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
					
				case "Edit Order":
					AddToOrder();
					break;
				
				//Change the status of an order
				case "Change Order Status":
					changeStatus();
					break;
				
				case "Calculate route":
					travellingSalesperson();
					break;
			}
		}
	}
	public void CreateOrder(){
		JDBC jdbc = new JDBC();
		jdbc.createOrder();
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "Add products to this order?");
		if(dialogResult==JOptionPane.NO_OPTION){
			return;
		}
		else{
			AddToOrder();
		}
	}
	
	public void AddToOrder(){
		JDBC jdbc = new JDBC();
		jdbc.addToOrder();
	}
	
	public void DisplayOrderList(){
		//Initialising the database connection and running the method to read orders
		JDBC jdbc = new JDBC();
		jdbc.readOrders();
		
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		
		for (String orderString: jdbc.readOrders()){
			listModel.addElement(orderString);
		}
		
		list.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				list = (JList)event.getSource();
				if (event.getClickCount()==2){
					 int index = list.locationToIndex(event.getPoint());
				     Object item = listModel.getElementAt(index);;
				     list.ensureIndexIsVisible(index);
				     int dialogResult = JOptionPane.showConfirmDialog(null, "Work on this order?");
						if(dialogResult==JOptionPane.YES_OPTION){
							editWorkStatus();
						}
						else{
							return;
						}
				}
			}	
		});
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
	
	public void DisplayPurchaseOrder(){
		JDBC jdbc = new JDBC();
		jdbc.readPurchaseOrder();
		
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		
		for (String poString: jdbc.readPurchaseOrder()){
			listModel.addElement(poString);
		}
	}
	
	public void DisplayPurchaseOrderList(){
		JDBC jdbc = new JDBC();
		jdbc.readPurchaseOrderLine();
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();	
		listModel.clear();
		for (String productString: jdbc.readPurchaseOrderLine()){
			listModel.addElement(productString);
		}		
	}
	
	public void addPurchaseOrder(){
		JDBC jdbc = new JDBC();
		jdbc.addPurchaseOrder();
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "Add products to this purchase order?");
		if(dialogResult==JOptionPane.NO_OPTION){
			return;
		}
		else{
			addPOProducts();
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
	
	public void editWorkStatus(){
		JDBC jdbc = new JDBC();
		
		jdbc.editWorkStatus();
	}
	
	public void travellingSalesperson(){
		JDBC jdbc = new JDBC();
		jdbc.travellingSalesperson();
	}
	
	public MainWindow() {
		designGUI();
		createButtons();
	}

}