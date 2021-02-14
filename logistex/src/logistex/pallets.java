package logistex;

import java.sql.ResultSet;
import java.sql.SQLException;

public class pallets {
	private int E;
	private int R;
	private int P;
	private String product_name;
	private int quantity;
	private int id;
	
	public String get_pallet(String product_name,int quantity) throws SQLException {
		ResultSet rs=data_manager.get_pallet(product_name);
		while (rs.next())
	      {
	         this.quantity= rs.getInt("quantity");
	         this.E= rs.getInt("E");
	         this.R= rs.getInt("R");
	         this.P= rs.getInt("P");
	         this.id= rs.getInt("id");
	         this.product_name= rs.getString("product_name");
	         //System.out.print(this.product_name);
	      }
		quantity+=this.quantity;
		data_manager.update_pallet(id,quantity,product_name);
		return "E"+E+"R"+R+"p"+P;
		
	}
	
}
