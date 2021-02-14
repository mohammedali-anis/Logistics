package logistex;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
	public class data_manager {
	private static int product_list[] =new int[100];
	private static String shelf_list [] =new String[100];
	
	public static java.sql.Connection con;
	
	
	public static void conn() {
		
		/////////st.executeUpdate(query);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logistx","root","");
			//System.out.print("ok");

		}
		catch(ClassNotFoundException ex) {
			System.out.print("hh");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ResultSet get_product(String name) throws SQLException {
		String query = "select * from products where product_name='"+name+"' ";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		//st.close();
		return rs;

	}
	
	public static int get_product_quantity(String name) throws SQLException {
		String query = "select quantity from products where product_name='"+name+"' ";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int quantity=0;
		while (rs.next())
	      {
	         quantity = rs.getInt("quantity");
	      
	      }
		return quantity;
	}
	
	public static boolean check_if_product_exists(String name) throws SQLException {
		String query = "select product_code from products where product_name='"+name+"' ";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int product_code=-1;
		while (rs.next())
	      {
	         product_code = rs.getInt("product_code");
	      
	      }
		if(product_code==-1) {
			return false;
		}
		return true;
	}
	
	public static ResultSet grt_all_products() throws SQLException {
		String query = "select * from products ";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		return rs;
	}
	
	
	public static void add_product(String name,int quantity,int VE,int frequency,int price) throws SQLException {
		//String query = "insert into products (product_name,VE,frequency,quantity) values `"+name+"`,`"+VE+"`,`"+frequency+"`,`"+quantity+"`";
		String query = "insert into products (product_name,VE,frequency,quantity,price) values('" +name+ "', '" +VE+  
                "', '" +frequency+ "', '" +quantity+ "' ,'"+price+"'  )"; 
		java.sql.Statement st = con.createStatement();
		st.executeUpdate(query);
	}
	
	public static void update_quantity(int quantity,String name) throws SQLException {
		String query = "UPDATE products set quantity= '" + quantity +  
                "' WHERE product_name = '" +name+ "'";
		java.sql.Statement st = con.createStatement();
		st.executeUpdate(query);
		
	}
	
	public static ResultSet get_z(int VE,int i) throws SQLException {
		String query = "select VE from s_m_z where shelf_name='f"+i+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		//st.close();
		return rs;

	}
	
	public static void update_z(int VE,int i,int r_z,String product_name,int quantity) throws SQLException {
			
		String query = "UPDATE s_m_z set VE= '" + VE +  
				"' WHERE shelf_name ='f" +i+ "' AND z='"+r_z+"'";
		java.sql.Statement st = con.createStatement();
		st.executeUpdate(query);
		
		query ="insert into log (product_name,shelf,quantity) values('" +product_name+ "', 'f" +i+  
		        "','" +quantity+ "'  )";
				st = con.createStatement();
				st.executeUpdate(query);
			
	}
	
	public static ResultSet get_h_h_s(int VE) throws SQLException {
		String query = "select E,R,D from f_h_h_s where VE>='"+VE+"' limit 1";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		//st.close();
		return rs;

	}
	
	public static ResultSet get_XY_h_h(int E,int R,int D) throws SQLException {
		String query = "select VE from h_h_s where E='"+E+"' AND R='"+R+"' AND D='"+D+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		//st.close();
		return rs;

	}
	public static void update_XY(int VE[], int E, int R, int D,int max,String product_name,int quantity) throws SQLException {
//		for(int i=1;i<97;i++) {
//			str+="'"+i+"'='"+VE[i]+"'";
//		}
		//int sum=0;
		String query="";
		for(int i=1;i<97;i++) {
			query = "UPDATE h_h_s set VE= '" + VE[i] +  
					"' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+D+"' AND XY='"+i+"'";
			java.sql.Statement st = con.createStatement();
			st.executeUpdate(query);

		}
		java.sql.Statement st = con.createStatement();
		query = "UPDATE f_h_h_s set VE= '" + max +  
				"' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+D+"'";
		st = con.createStatement();
		st.executeUpdate(query);
		
		query ="insert into log (product_name,shelf,quantity) values('" +product_name+ "', '" +"E"+E+"R"+R+"D"+D+  
        "','" +quantity+ "'  )";
		st = con.createStatement();
		st.executeUpdate(query);
		
		
	}
	public static ResultSet get_t_h_h_s(int VE) throws SQLException {
		String query = "select E,R,D from f_t_h_h_s where VE>='"+VE+"' limit 1";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		//st.close();
		return rs;
	}
	public static ResultSet get_XY_t_h_h(int E, int R, int D) throws SQLException {
		String query = "select VE from t_w_s where E='"+E+"' AND R='"+R+"' AND D='"+D+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		//st.close();
		return rs;
	}
	public static void update_XY_t(int VE[], int E, int R, int D, int max,String product_name,int quantity) throws SQLException {
		
		String query="";
		for(int i=1;i<33;i++) {
			query = "UPDATE t_w_s set VE= '" + VE[i] +  
					"' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+D+"' AND XY='"+i+"'";
			java.sql.Statement st = con.createStatement();
			st.executeUpdate(query);

		}
		java.sql.Statement st = con.createStatement();
		query = "UPDATE f_t_h_h_s set VE= '" + max +  
				"' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+D+"'";
		st = con.createStatement();
		st.executeUpdate(query);
		
		query ="insert into log (product_name,shelf,quantity) values('" +product_name+ "', '" +"E"+E+"R"+R+"D"+D+  
		        "','" +quantity+ "'  )";
				st = con.createStatement();
				st.executeUpdate(query);
		
		
		
		
		
		
		
		
		
		//		String query = "UPDATE t_w_s set VE= '" + VE +  
//                "' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+D+"' AND XY='"+X_Y+"'";
//		java.sql.Statement st = con.createStatement();
//		st.executeUpdate(query);
//		 query = "UPDATE f_t_h_h_s set VE= '" + max +  
//				 "' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+D+"'";
//		 st = con.createStatement();
//		st.executeUpdate(query);
		
	}
	public static ResultSet check_shels_statuse(int VE) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select shelf_type from shelfs_statuse where max_VE>='"+VE+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	public static void update_XY_F(int[] VE, int E, int R, int H, int max,String product_name,int quantity) throws SQLException {
		// TODO Auto-generated method stub
		String query="";
		for(int i=1;i<2001;i++) {
			query = "UPDATE f_h_s set VE= '" + VE[i] +  
					"' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+H+"' AND XY='"+i+"'";
			java.sql.Statement st = con.createStatement();
			st.executeUpdate(query);

		}
		java.sql.Statement st = con.createStatement();
		query = "UPDATE f_f_h_s set VE= '" + max +  
				"' WHERE E ='" +E+ "' AND R='"+R+"' AND D='"+H+"'";
		st = con.createStatement();
		st.executeUpdate(query);
		
		query ="insert into log (product_name,shelf,quantity) values('" +product_name+ "', '" +"E"+E+"R"+R+"H"+H+  
		        "','" +quantity+ "'   )";
				st = con.createStatement();
				st.executeUpdate(query);
		
	}
	public static ResultSet get_pallet(String product_name) throws SQLException {
		String query = "select * from pallets where product_name='"+product_name+"'OR product_name='empty' limit 1";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	public static void update_pallet(int id, int quantity, String product_name) throws SQLException {
		String query = "UPDATE pallets set product_name= '" + product_name +  
				"',quantity='"+quantity+"' WHERE id ='" +id+ "'";
		java.sql.Statement st = con.createStatement();
		st.executeUpdate(query);
		
	}
	public static ResultSet get_pallet_p(String product_name) throws SQLException {
		String query = "select * from pallets where product_name='"+product_name+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public static ResultSet get_product_placment(String product_name) throws SQLException {
		
		String query = "select * from log where product_name='"+product_name+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	public static ResultSet get_product_placment_o(String product_name) throws SQLException {
		
		String query = "select * from log where product_name='"+product_name+"' limit 1";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	
	public static String get_product_placment_o_o(String product_name) throws SQLException {
		
		String query = "select * from log where product_name='"+product_name+"' limit 1";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		String s="";
		while (rs.next())
	      {
	         s = rs.getString("shelf");
	      
	      }	
		System.out.println(product_name);

		return s;
	}
	
	public static boolean sign_in(String name,String password) throws SQLException {
		String query = "select * from costumer where username='"+name+"' AND password='"+password+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		String username="";
		while (rs.next())
	      {
	         username = rs.getString("username");
	      }
		if(username=="") {
			return false;
		}
		return true;
	}
	
	public static boolean get_costumer(String name) throws SQLException {
		String query = "select id from costumer where username='"+name+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int id=-1;
		while (rs.next())
	      {
	         id = rs.getInt("id");
	      }
		if(id==-1) {
			
			return true;
		}
		return false;
	}
	
	public static void add_costumer(String name,String password,int premium) throws SQLException {
		String query ="insert into costumer (username,password,premium) values('" +name+ "', '" +password+"','"+premium+"')";
		java.sql.Statement st = con.createStatement();
				st.executeUpdate(query);
	}
	
	public static boolean get_costumer_statuse(String name) throws SQLException {
		String query = "select premium from costumer where username='"+name+"'";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int B=0;
		while (rs.next())
	      {
	         B = rs.getInt("premium");
	      }
		if(B==0) {
			return false;
		}
		return true;
	}
	
	public static  void buy_product(String name ,int quantity,int VE) throws SQLException {
		
		String query = "UPDATE products set quantity= '" + quantity +  
				"' WHERE product_name ='" +name+ "'";
		java.sql.Statement st = con.createStatement();
		st.executeUpdate(query);
		
		//TODO :free shelf space
	}
	
	public static void place_order(String name,int quantity) throws SQLException {
		String query = "delete from ordr ";
		PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
	      //preparedStmt.setInt(1, 3);

	      // execute the preparedstatement
	      preparedStmt.execute();
	}
	
	public static ResultSet get_product_ordr() throws SQLException {
		String query = "select product_name from ordr";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public static ResultSet get_product_info() throws SQLException {
		String query = "select * from ordr";
		java.sql.Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public static void add_ordr_product(String name,int quantity,int price,int VE) throws SQLException {
		String query ="insert into ordr (product_name,quantity,price,VE) values('" +name+ "', '" +quantity+"','"+price+"','"+VE+"')";
		java.sql.Statement st = con.createStatement();
		st.executeUpdate(query);
	}
}
	
	
