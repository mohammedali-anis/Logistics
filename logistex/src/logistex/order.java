package logistex;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class order extends JFrame {
	//Atribute
	private int x;
	private boolean check1;
	private boolean check;
	private JButton purchase , back , find_path , find_product,help;
	private JTextField costumer_name,price,t_price,shipment,product_name;
	private JLabel txt_price,txt_t_price , txt_shipment ;
	private JLabel error1 = new JLabel("Enter product name to search");
	private JLabel error2 = new JLabel("Product not found");
	private JLabel error3 = new JLabel("Out of stock");
	private double sum=0;
	private DecimalFormat df2 = new DecimalFormat("#.##");
	public order(String s) throws SQLException
	
	{
		//setup
		setTitle("Logistix");
		setLayout (null);
		setVisible(true);
		setSize(500,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		check=	data_manager.get_costumer_statuse(s);
		add(error1);
		error1.setBounds(290,50,180,30);
		error1.setVisible(false);
		error1.setForeground(Color.red);
		add(error2);
		error2.setBounds(290,50,180,30);
		error2.setVisible(false);
		error2.setForeground(Color.red);
		add(error3);
		error3.setBounds(290,50,180,30);
		error3.setVisible(false);
		error3.setForeground(Color.red);
		
		//Button setup
		purchase		  = new JButton("Purchase");
		back 		  = new JButton("Back");
		find_path	  = new JButton("Find path");
		find_product  = new JButton("Find product");
		help 		  = new JButton("?");
		add(purchase);
		add(back);
		add(find_path);
		add(find_product);
		add(help);
		//Text field setup
		costumer_name = new JTextField(s);
		if (check)
			shipment  = new JTextField("0.00");
		else
			shipment  = new JTextField("2.99");
		price  = new JTextField("");
		t_price = new JTextField("");
		product_name = new JTextField("Product name");
		add(costumer_name);
		add(shipment);
		add(price);
		add(t_price);
		add(product_name);		
		costumer_name.setHorizontalAlignment(JTextField.CENTER);
		if(check)
			{costumer_name.setForeground(Color.blue);
			shipment.setForeground(Color.red);
			}
		//Label setup
		txt_price = new JLabel("Price is : ");
		txt_t_price = new JLabel("The total price is : ");
		txt_shipment = new JLabel("shipment price is : ");
		
		add(txt_price);
		add(txt_t_price);
		add(txt_shipment);
		
		//Text field edit status
		price.setEditable(false);
		t_price.setEditable(false);
		costumer_name.setEditable(false);
		shipment.setEditable(false);
	
		
		help.setMargin (new Insets(0,0,0,0));
		
		//Button bounds
		find_product.setBounds(350,125,120,30);
		find_path.setBounds(350,170,120,30);
		help.setBounds(465,0,20,20);
		back.setBounds(130 ,260,100,30 );
		purchase.setBounds(260,260,100,30);
	
		
		//Text feilds bounds
		costumer_name.setBounds(175,20,150,30);
		price.setBounds(130,80,100,30);
		shipment.setBounds(130,125,100,30);
		t_price.setBounds(130,170,100,30);
		product_name.setBounds(310,80,160,30);
		
		//Label bounds
		
		txt_price.setBounds	  (75,80,140,30);
		txt_shipment.setBounds(20,125,140,30);
		txt_t_price.setBounds (25,170,140,30);
		
		
		product_name.addFocusListener(new java.awt.event.FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				product_name.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		});
		
		//buttons action
			ev e = new ev();
			
		purchase.addActionListener(e);
		back.addActionListener(e);
		find_product.addActionListener(e);
		find_path.addActionListener(e);
		help.addActionListener(e);
			
		ResultSet rs =data_manager.get_product_info();
		while (rs.next())
	      {
	        sum +=Double.parseDouble( ""+rs.getInt("quantity"))
	        		*Double.parseDouble(""+rs.getInt("price"));
	         
	         
	      }	
		price.setText(""+sum);
		if(!check)
			sum+=2.99;
		
		t_price.setText(""+df2.format(sum));
		
		
	}public class ev implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			error2.setVisible(false);
			error3.setVisible(false);
			if(e.getSource()== back)
			{
				costumer n = new costumer();
				dispose();
			}
			
			else if(e.getSource() == find_product)
			{
				try {
					x= data_manager.get_product_quantity(product_name.getText());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				error1.setVisible(false);
				
				if(product_name.getText().equals("")||product_name.getText().equals("Product name"))
				{
					error1.setVisible(true);
			    	 product_name.setBorder(BorderFactory.createLineBorder(Color.red, 1));
				}
				else if(x==0)
				{
					error3.setVisible(true);
				}
				
				else {
				String s = product_name.getText();
				
				try {
				check1 = data_manager.check_if_product_exists(s);
				
				if(check1) {
					find_product w = new find_product(s,costumer_name.getText());
					dispose();
				}
				
				else {
					error2.setVisible(true);
					
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
 				}
			}
			
			else if(e.getSource() == find_path)
			{
				error1.setVisible(false);
		    	 product_name.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
				String ss="";
		    	 try {
					ResultSet rs = data_manager.get_product_ordr();
					String[] s=new String[100];int x=0;
					
					while (rs.next())
				      {
				        s[x]= rs.getString("product_name");
				        System.out.println(s[x]);
				        x++;

				        
				      }	
					for(int j =0 ; j < x ; j++) {
						
						ss+=data_manager.get_product_placment_o_o(s[j]);
						System.out.print(data_manager.get_product_placment_o_o(s[j]));
						ss+="\n";
						//System.out.println(ss);
						System.out.println(s[j]);
					}
					
					
		    	 } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
					
		    	 JOptionPane.showMessageDialog(null, ss);
		    }
			
			else if(e.getSource()==purchase)
			{
				
				error1.setVisible(false);
		    	 product_name.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		    	 int q ,ve;
		 		 String n;
		    	 int w=0;
		    	 try {
		    	 ResultSet rs =data_manager.get_product_info();
		 		while (rs.next())
		 	      {
		 				
		 	        	q =	rs.getInt("quantity");
		 	        	ve=	rs.getInt("VE");
		 	         	n = rs.getString("product_name"); 
		 	         	q=data_manager.get_product_quantity(n)-q;
		 	         	
		 	         	//TO DO get final quantity 
		 	         data_manager.buy_product(n, q,ve);
		 	     
		 	      }	
		 			
		 		data_manager.place_order(costumer_name.getText(),w );
				JOptionPane.showMessageDialog(null, "Purchase has been done");
		    	 } catch (SQLException e1) {
					e1.printStackTrace();
				}
		    	 //heee
			}
			else if(e.getSource()==help)
			{
				JOptionPane.showMessageDialog(
						null, "Premuim costumers have blue name\n"
								+ "Shipment price for premium costumer is 0\n"
								+ "Enter the product that you want to buy in the text field\n"
								+ "The price field shows of all product in cart"
								+ "The total price field shows the price of products + shipment"
								+ "Find path is to find the path for the products in the storage unit //this should be on the employee side of the page"
								+ "\nPurchase\n is to compleat the purchase");
			}
			
			
				
				}
		}
	
	}