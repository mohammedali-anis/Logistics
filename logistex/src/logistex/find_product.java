package logistex;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.StringUtils;

import logistex.find_shelf.ev;

public class find_product extends JFrame{
	private JButton cancel,purchase,help;
	private JLabel txt_quantity , txt_price,shelf_num,error1 ;
	private JTextField  price , quantity ,product_name;
	private  int i =0 ;
	private  int counter;
	private String costumer_name;
	private int VE = 0;
		public find_product(String s,String costumer_name) throws SQLException {
		 
		
			this.costumer_name = costumer_name;
		
		
		//Setup
		setTitle("Logistix");
		setLayout (null);
		setVisible(true);
		setSize(300,250);
		setLocationRelativeTo(null);
		
		
		error1 = new JLabel("Quantity is invaled");
		error1.setVisible(false);
		error1.setForeground(Color.red);
		error1.setBounds(100,45,100,30);
		
		//Button setup
		cancel 	 = new JButton ("Cancel");
		purchase = new JButton ("Purchase");
		help 	 = new JButton ("?");
		
		add(help);
		add(cancel);
		add(purchase);
		add(error1);
		help.setMargin(new Insets(0,0,0,0));
	
		//Text fields setup
		price = new JTextField("");
		quantity	  = new JTextField("");
		product_name  = new JTextField(s);
		
		product_name.setEditable(false);
		
		price.setEditable(false);
		
		add(price);
		add(quantity);
		add(product_name);
		
		//Labels setup
		txt_price = new JLabel("Price : ");
		txt_quantity	  = new JLabel("Quantity : ");
		shelf_num 		  = new JLabel("First");
		add(txt_price);
		add(txt_quantity);
		add(shelf_num);
		
		//Buttons bounds
		help.setBounds(265,0,20,20);
		cancel.setBounds(45,175,100,30);
		purchase.setBounds(155,175,100,30);
		
		//Text field bounds
		price.setBounds(135,75,100,30);
		quantity.setBounds(135,115,100,30);
		product_name.setBounds(100,15,100,30);
		
		//Label bounds
		txt_price.setBounds(60,75,100,30);
		txt_quantity.setBounds(42,115,100,30);
		shelf_num.setBounds(263,170,100,40);
		//Button action
		ev e = new ev();
		cancel.addActionListener(e);
		purchase.addActionListener(e);
		help.addActionListener(e);
		
		
		
		product p = new product();
		p.get_product_info(product_name.getText());
		p.get_product_wight();
		price.setText(""+p.get_product_price())	;
		
		
		
		
	
	}
	 public class ev implements ActionListener  {

		 public void actionPerformed(ActionEvent e){
			 
			 
		
			 
			
			 if(e.getSource()==help)
				 JOptionPane.showMessageDialog
				 (null,"Enter the quntity in the quantity text field\n"
				 		+ "The price will show at the price field\n"
				 		+ "Click \"add to cart\" to add to cart ");
			
			 else if(e.getSource() == cancel)
			 {
				try {
					order w = new order(costumer_name);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 dispose();
			 }
			 else if(e.getSource()== purchase)
			 {
				 int order_quantity=Integer.parseInt(quantity.getText());
				 int main_quantity=0;
				 try {
					main_quantity=data_manager.get_product_quantity(product_name.getText());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 
				 if(quantity.getText().equals("")||!StringUtils.isStrictlyNumeric(quantity.getText()))
				 {
					 quantity.setBorder(BorderFactory.createLineBorder(Color.red, 1)); 
					 error1.setVisible(true);
				 }
				 else if(order_quantity>main_quantity)
				 {
					 JOptionPane.showMessageDialog(null, "Only "+main_quantity+" unit left of this product");
				 }
				 else {
				try {
					data_manager.add_ordr_product(product_name.getText(), Integer.parseInt(quantity.getText()) ,Integer.parseInt(price.getText()), VE);
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					order w = new order(costumer_name);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null, "Product has been added");
				 }	
					
			}
			
		 }
		 
	 
	 
	 }
}
