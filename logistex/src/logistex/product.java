package logistex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class product {
	private int product_code=-1;
	private String product_name;
	private int quantity;
	private int VE;
	private int frequency;
	private int price;
	private int[] quantity_array=new int[200];
	private String[] shelf_array=new String[200];
	
	//TODO: vector
	//private ArrayList<String> stroring_place = new ArrayList<String>(); 
	public String storing_place[]=new String[100];

	public product(String name) throws SQLException {
		this.product_name=name;
//	this.check_if_exist(this.product_name);
	}
	public product() {
		
	}
	public int get_product_code() {
		return this.product_code;
	}
	public String get_product_name() {
		return this.product_name;
	}
	public int get_product_wight() {
		return this.VE;
	}
	public int get_product_quantity() {
		return this.quantity;
	}
	public int get_product_price() {
		return this.price;
	}
	
	// product_placment
	//92
	public void get_product_info(String name) throws SQLException {
		ResultSet rs= data_manager.get_product(name);
		while (rs.next())
	      {
	         this.product_code = rs.getInt("product_code");
	         this. quantity= rs.getInt("quantity");
	         this.VE= rs.getInt("VE");
	         this.frequency= rs.getInt("frequency");
	         this.price=rs.getInt("price");
	      
	      }	
	}
	public int find_product_placment(String name) throws SQLException {
		 this.get_product_info(name);
		 boolean b;
		 int x=0;
		 if(this.frequency==1) {
			 	ResultSet rs=data_manager.get_pallet_p(name);
				String E="",R="",P="";
				
				while (rs.next())
			      {
			         this.quantity_array[x] = rs.getInt("quantity");
			         E= rs.getString("E");
			         R= rs.getString("R");
			         P= rs.getString("P");
			         x++;
			      }
				E="E"+E+"R"+R+"P"+P;
				this.shelf_array[0]=E;
		 }
		 else {
			 	ResultSet rs=data_manager.get_product_placment(name);
				
				while (rs.next())
			      {
			         this.quantity_array[x] = rs.getInt("quantity");
			         this. shelf_array[x]= rs.getString("shelf");
			         x++;
			      }
		 }

		return x+1;
	}
	public String[] get_shelf_array() {
		return this.shelf_array;
	}
	public int[] get_quantity_array() {
		return this.quantity_array;
	}
	
	public boolean check_name(String name) throws SQLException
	{
		ResultSet rs=data_manager.get_product(name);
		while (rs.next())
	      {
	         this.product_code = rs.getInt("product_code");
	         this. quantity= rs.getInt("quantity");
	         this.VE= rs.getInt("VE");
	         this.frequency= rs.getInt("frequency");
	         this.price=rs.getInt("price");
	      
	      }	
		if (this.product_code == -1)
				return false;
		
		return true;
	}
	//main check if exists
	public void check_if_exist(String name,int quantity , int VE , int frequency , int price) throws SQLException {
		ResultSet rs=data_manager.get_product(this.product_name);
		while (rs.next())
	      {
	         this.product_code = rs.getInt("product_code");
	         this. quantity= rs.getInt("quantity");
	         this.VE= rs.getInt("VE");
	         this.frequency= rs.getInt("frequency");
	         this.price=rs.getInt("price");
	      }
	//	System.out.println(this.product_code);
		if(product_code==-1) {
			Scanner input = new Scanner(System.in);
			//System.out.print("Enter quantity :");
			this.quantity=quantity;
			//System.out.print("Enter VE :");
			this.VE=VE;
			//System.out.print("Enter frequancy :");
			this.frequency=frequency;
			//System.out.print("Enter price :");
			this.price=price;
			data_manager.add_product(name,this.quantity,this.VE,this.frequency,this.price);
			boolean b;
			
			if(this.frequency==1) {
				b=true;
			}
			else {
				b=false;
			}
			storing str =new storing(this.VE,name,this.quantity,b);

		}
		else {
			Scanner input = new Scanner(System.in);
			//System.out.print("Enter quantity :");
			//int q=input.nextInt();
			this.quantity+=quantity;
			data_manager.update_quantity(this.quantity,name);
		}	
		//System.out.print(product_code);
	}
	
	//OverLoad check if exists
	public void check_if_exist(String name,int quantity) throws SQLException {
		ResultSet rs=data_manager.get_product(this.product_name);
		while (rs.next())
	      {
	         this.product_code = rs.getInt("product_code");
	         this. quantity= rs.getInt("quantity");
	         this.VE= rs.getInt("VE");
	         this.frequency= rs.getInt("frequency");
	         this.price=rs.getInt("price");
	      }
	//	System.out.println(this.product_code);
		if(this.product_code==-1) {
			Scanner input = new Scanner(System.in);
			//System.out.print("Enter quantity :");
			//this.quantity=input.nextInt();
			//System.out.print("Enter VE :");
			//this.VE=input.nextInt();
			//System.out.print("Enter frequancy :");
		//	this.frequency=input.nextInt();
			//System.out.print("Enter price :");
			this.price=input.nextInt();
			data_manager.add_product(name,this.quantity,this.VE,this.frequency,this.price);
//			boolean b;
//			
//			if(this.frequency==1) {
//				b=true;
//			}
//			else {
//				b=false;
//			}
//			storing str =new storing(this.VE,name,this.quantity,b);

		}
		else {
			Scanner input = new Scanner(System.in);
			//System.out.print("Enter quantity :");
			int q=quantity;
			this.quantity+=q;
			data_manager.update_quantity(this.quantity,name);
			
			
			boolean b;
			
			if(this.frequency==1) {
				b=true;
			}
			else {
				b=false;
			}
			
			storing str =new storing(this.VE,name,this.quantity,b);
			
		}	
		//System.out.print(product_code);
	}
	//end overload
	
	
	public void product_info(String product_name,boolean demand) throws SQLException {
		ResultSet rs=data_manager.get_product(this.product_name);
		while (rs.next())
	      {
	         this.product_code = rs.getInt("product_code");
	         this. quantity= rs.getInt("quantity");
	         this.VE= rs.getInt("VE");
	         this.frequency= rs.getInt("frequency");
	         this.price=rs.getInt("price");
	      }
		if(demand==true) {
			rs=data_manager.get_pallet_p(product_name);
			int e=0,r=0,p=0,id;
			while (rs.next())
		      {
		         this.quantity= rs.getInt("quantity");
		         e= rs.getInt("E");
		         r= rs.getInt("R");
		         p= rs.getInt("P");
		         id= rs.getInt("id");
		         this.product_name= rs.getString("product_name");
		         //System.out.print(this.product_name);
		      }
			this.storing_place[0]=e+r+p+"";
		}
		
		else {
			
		}
		
	}
}
