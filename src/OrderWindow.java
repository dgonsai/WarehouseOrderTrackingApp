import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OrderWindow extends JFrame {
		private JFrame mainFrame;
		private JLabel headerLabel;
		private JPanel controlPanel;
		private JList list;
		private JLabel orderIDtag, productIDtag, quantityTag, productTag, totalTag;
		private JTextArea orderID, productID, quantity, total, product;
		
		//initialising and design of the main menu GUI
		private void designGUI(){
			mainFrame = new JFrame("Order Menu");
			mainFrame.setSize(500, 800);
			mainFrame.setLayout(new GridLayout(4,3));
			controlPanel = new JPanel();
			controlPanel.setLayout(new FlowLayout());
			headerLabel = new JLabel ("",JLabel.CENTER);
			orderIDtag = new JLabel("Order ID: ");
			productIDtag = new JLabel("Product ID: ");
			quantityTag = new JLabel("Quantity ");
			productTag = new JLabel("Products: ");
			totalTag = new JLabel("Total ");		
			
			orderID = new JTextArea("");
			productID = new JTextArea("");
			quantity = new JTextArea("");
			
			GroupLayout layout = new GroupLayout(controlPanel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			
		
			
			

			mainFrame.addWindowListener(new WindowAdapter(){
				public void WindowClose(WindowEvent windowEvent){
					System.exit(0);
				}
			});
			
			
			mainFrame.add(headerLabel);
			mainFrame.add(controlPanel);
			mainFrame.setVisible(true);

		}
		
		private void createButtons(){
			headerLabel.setText("NB Gardens - Orders");
			
			JButton saveOrderButton = new JButton("Save Order");				
			saveOrderButton.setActionCommand("Save Order");				
			saveOrderButton.addActionListener(new ButtonClick());			
			controlPanel.add(saveOrderButton);
			
			JButton changeStatusButton = new JButton("Change status to next stage");
			changeStatusButton.setActionCommand("Change Status");
			changeStatusButton.addActionListener(new ButtonClick());
			controlPanel.add(changeStatusButton);
			
			saveOrderButton.setAlignmentX(LEFT_ALIGNMENT);
			changeStatusButton.setAlignmentX(LEFT_ALIGNMENT);
	
						
			mainFrame.setVisible(true);
		}
		
		private class ButtonClick implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent ae){
				String command = ae.getActionCommand();
				switch (command){
					
					case "Save Order":
						//enter code for updating order in database here
					case "Change Status":
						//enter code for updating status of an order here
						break;
					
				}
			}
		}

	public OrderWindow() {
		designGUI();
		createButtons();
	}
}
