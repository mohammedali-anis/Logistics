package logistex;
	
	import java.awt.*;
	import java.awt.event.*  ;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.jdbc.StringUtils;
//import gui.First_frame.ev;
public class Employee extends JFrame  {

	


		
		//Atributes
		private static int counter = 0;
		private JButton back,add_product,find_info,help,find_shelf;
		private JTextField name,ID,find_quantity,add_quantity ,price,VE;
		private JLabel error1,txt_quantity,error2,error3,finding_shelf;
		

		public Employee() {
					//Setup
					setTitle("Logistix");
					setLayout (null);
					setVisible(true);
					setSize(450,350);
					setLocationRelativeTo(null);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
					//Error
					
					error1 =new JLabel("please enter a name ");
					error1.setBounds(115,10,220,30);
					error1.setVisible(false);
					error1.setForeground(Color.red);
					add(error1);
					
					error2 =new JLabel("enter a valid quantity");
					error2.setBounds(275,72 ,180,40 );
					error2.setVisible(false);
					error2.setForeground(Color.red);
					add(error2);
				
					error3 =new JLabel("Out of stock ");
					error3.setBounds(115,10,220,30);
					error3.setVisible(false);
					error3.setForeground(Color.red);
					add(error3);
					

					// Label
					finding_shelf = new JLabel("Setting shelf..");
					txt_quantity = new JLabel("Enter quantity : ");
					add(txt_quantity);
					add(finding_shelf);
					finding_shelf.setBounds(180,165, 100,40);
					txt_quantity.setBounds(60,75,100,30);
					finding_shelf.setForeground(Color.blue);
					finding_shelf.setVisible(false);
					//buttons
					add_product = new JButton("Add product");
					find_info   = new JButton("Find info");
					help	    = new JButton("?");
					find_shelf  = new JButton("Find shelf");
					back		= new JButton("Back");
					add (add_product);
				 	add (find_info);
					add (help);
					add (find_shelf);
					add (back);
					//Button Margin
					add_product.setMargin(new Insets(0,0,0,0));
					find_info.setMargin  (new Insets(0,0,0,0));
					help.setMargin		 (new Insets(0,0,0,0));
					find_shelf.setMargin (new Insets(0,0,0,0));
					back.setMargin       (new Insets(0,0,0,0));
					//Button bounds
					add_product.setBounds(175,125, 100,40); 
					find_info.setBounds(175,200,100,40); 
					help.setBounds(405, 0, 30, 30);
					find_shelf.setBounds(187,260,76, 30);
					back.setBounds(0,0,50,30);
					//Text fields
					name	 = new JTextField ("Product Name", 30 );
					ID		 = new JTextField ("ID", 30 );
					find_quantity=new JTextField ("find_quantity", 30 );
					price	 = new JTextField ("Price", 30 );
					VE 		 = new JTextField ("VE", 30 );
					add_quantity= new JTextField("");
					add(name);
					add(ID);
					add(find_quantity);
					add(price);
					add(VE);
					add_quantity.setBounds(160,75,100,30);					
					add(add_quantity);
					
					//Text bounds
					name.setBounds   (115,35,220,30);
					ID.setBounds      (25,260,50,30);
					find_quantity.setBounds(105,260,50,30);
					price.setBounds	  (295,260,50,30);
					VE.setBounds	  (375,260,50,30);
					
					//Edit status
					name.setEditable		 (true );
					ID.setEditable           (false);
					find_quantity.setEditable(false);
					price.setEditable		 (false);
					VE.setEditable			 (false);
					
					//Button Actions	
					ev e = new ev();
					add_product.addActionListener(e);
					find_info.addActionListener(e);
					help.addActionListener(e);
					find_shelf.addActionListener(e);
					back.addActionListener(e);
					
					fl f = new fl();
				
					name.addFocusListener(f); 
					add_quantity.addFocusListener(f);
			
		
						
		}
		
		public class fl implements FocusListener{
			
			@Override
			public void focusGained(FocusEvent e) {
				if(e.getSource()==add_quantity)
					add_quantity.setText("");
				else if (e.getSource()==name)
					name.setText("");
			}

			@Override
				public void focusLost(FocusEvent e) {
			 
			}}
		
		public class ev implements ActionListener  {
			 
			 public void actionPerformed(ActionEvent e){
				error3.setVisible(false);
				 if(e.getSource()==find_info)
						 {int product_code=0;
					 int quantity1=0;
					 int VE1=0;
					 int frequency1=0;
					 int price1=0;
					 try {
						ResultSet rs= data_manager.get_product(name.getText());
						while (rs.next())
					      {
					         product_code = rs.getInt("product_code");
					          quantity1= rs.getInt("quantity");
					         VE1= rs.getInt("VE");
					         frequency1= rs.getInt("frequency");
					         price1=rs.getInt("price");
					      
					      }	
					} catch (SQLException e1) {
						
					
						e1.printStackTrace();
					}
					 ID.setText(""+product_code);
					 price.setText(""+price1);
					 VE.setText(""+VE1);
					 find_quantity.setText(""+quantity1);
					 
						
				//gets info from data bsae quantity VE price ID
				}
				 else if(e.getSource()==back)
				 {
					 main_frame n = new main_frame();
					 dispose();
				 }
				 else if (e.getSource()==add_product)
					 {
					 if(name.getText().equals("") ||name.getText().equals("Product Name"))
					 { 
						 if(!StringUtils.isStrictlyNumeric(add_quantity.getText()))
					 	 {
					 		 error2.setVisible(true);
					 		 add_quantity.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					 	 }
						 else
						 {
							 error2.setVisible(false);
							 add_quantity.setBorder(BorderFactory.createLineBorder(Color.gray, 1));	 
						 }
						
						 error1.setVisible(true);
						 name.setBorder(BorderFactory.createLineBorder(Color.red, 1));
						 if(error1.isVisible())
							 counter++;
						 else
							 counter = 0;
						 
						 if ( counter == 3)
						 {
							 
							 help.setBorder(BorderFactory.createLineBorder(Color.yellow, 4));
						 }
					 }
					 else if(!StringUtils.isStrictlyNumeric(add_quantity.getText()))
					 {
					 		 error1.setVisible(false);
					 		 name.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
						 	 error2.setVisible(true);
					 		 add_quantity.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					 }
					 else 
					 {
						 
						 error1.setVisible(false);
						 String s = name.getText();
						 String q_s = add_quantity.getText();
						 int q = Integer.parseInt(q_s);
						 //heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee 
						 product w = new product();
						 	
						 try {
							if(!w.check_name(s))
							{
								 add_quantity.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
							 	 error2.setVisible(false);
								 name.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
								 add_new_product n =new add_new_product(s,q_s);
								 name.setText("");
							}
							else {finding_shelf.setVisible(true);
							JOptionPane.showMessageDialog(null,"setting product shelf \nThis may take few seconds,press ok to start");
								w.check_if_exist(s, q);
								//finding_shelf.setVisible(false);
								JOptionPane.showMessageDialog(null, "Product has been added");
							}
						} 
						 
						 catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						 
						 //heeeeeeeeeeeeeee
					 }finding_shelf.setVisible(false);
					 }
				 
				 else if(e.getSource()==help) {

					 help.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
					 
					 //help.setBorderPainted(false);
					 JOptionPane.showMessageDialog(null, 
							 "You can add, check info or find the shelf of a product \n"
					 		+ "Enter product name and quantity of the product and press \"Add product\" to be added\n"
					 		+ "When you use \"Find info\" its info will show in the fields bellow\n"
					 		+ "to check on what shelfs the product is stored use \"Find shelf\"\n"
					 		+ "- To add product Dont leave \"product name\" or \"quantity\" fields empty\n"
					 		+ "- Enter only numbers for the quantity field\n"
					 		+ "- Dont leave \"product name\" empty in case of \"find shelf/info\"");
				 }
				 else if(e.getSource()==find_shelf) {
					 int q=0;
					 try {
						q=data_manager.get_product_quantity(name.getText());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					 
					 if(name.getText().equals("")||name.getText().equals("Product Name"))
					 {
							 
						 error1.setVisible(true);
							
							 name.setBorder(BorderFactory.createLineBorder(Color.red, 1));
					
					 }
					 else if(q==0)
					 {
						 error1.setVisible(false);
						 error3.setVisible(true);
					 }
				 
					 else {

					 try {
						find_shelf w = new find_shelf(name.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
			 }
		
		
	
}}}
