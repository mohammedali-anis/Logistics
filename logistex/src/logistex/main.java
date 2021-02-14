package logistex;



import java.awt.FlowLayout;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

public class main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		data_manager.conn();
	//	Scanner input = new Scanner(System.in);
//		int VE=input.nextInt();
//		Half_high s=new Half_high();
//		s.get_shelf(VE,VE,VE);
//		main_fram mf =new main_fram(); 
		//pallets p=new pallets();
		//p.get_pallet("dd", 50);
		main_frame q = new main_frame();
	
//		product w = new product();
//		private String[] shelf_array = w.get_shelf_array();
//		private int[] quantity_array = w.get_quantity_array();
//			
//		product w = new product();
//
//		
//		String[] shelf_array=new String[200];
//		 int[] quantity_array=new int[200];
//
//		  shelf_array = w.get_shelf_array();
//		  quantity_array = w.get_quantity_array();
//		 w.find_product_placment("article");
//		 for(int i=0;i<4 ;)
//		 {	
//			 System.out.println(shelf_array[i]);
//			 if(quantity_array[i+1] != -1)
//			{
//			 i++;
			
			
			
//	}
//	}



}}
/*
class main_fram extends JFrame{
	public main_fram() {
		
		JTextField mt=new JTextField(30);
		JLabel title =new JLabel("Logistx");
		JButton mb =new JButton("OK");
		add(mt);
		add(mb);
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}*/
