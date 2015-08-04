import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class OrderWindow {

	private JFrame orderFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	
	//initialising and design of the main menu GUI
	private void designGUI(){
		orderFrame = new JFrame("Order Menu");
		orderFrame.setSize(400, 300);
		orderFrame.setLayout(new GridLayout(3,1));
		
		headerLabel = new JLabel ("",JLabel.CENTER);
		statusLabel = new JLabel ("",JLabel.CENTER);
		statusLabel.setSize(350, 100);
		
		orderFrame.addWindowListener(new WindowAdapter(){
			public void WindowClose(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		orderFrame.add(headerLabel);
		orderFrame.add(controlPanel);
		orderFrame.add(statusLabel);
		orderFrame.setVisible(true);

	}
	
	private void createButtons(){
		headerLabel.setText("NB Garden - Warehouse App");
		
		JButton orderButton = new JButton("Order List");
		JButton searchOrder = new JButton("Search Order");
		
		orderButton.setActionCommand("Order List");
		searchOrder.setActionCommand("Search Order");
		
		orderButton.addActionListener(new ButtonClick());
		searchOrder.addActionListener(new ButtonClick());
		
		controlPanel.add(orderButton);
		controlPanel.add(searchOrder);
		orderFrame.setVisible(true);
	}
	
	private class ButtonClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String command = ae.getActionCommand();
			switch (command){
			case "Order List":
				OrderList();
				break;
			}
		}
	}
	
	public void OrderList(){
		WarehouseOrder test = new WarehouseOrder(0, null, null, null);
		test.OrderList();
	}
}
