	package logistex;

import java.sql.ResultSet;
import java.sql.SQLException;

public class storing  {
		
		private int VE;
		private int T_VE;
		private String name;
		private int quantity;
		private String[] shelfs=new String[4];
		Half_high h_h_s=new Half_high();
		therd_width_shelf t_w_s=new therd_width_shelf();
		small_shelf s_m_s=new small_shelf();
		full_hight f_h_s=new full_hight();
		pallets plts=new pallets();
		
		private int E ;
		private int R ;
		private int D ;
		private int F ;
		private int H ;
		private int P ;
		private int remaindor;
		private String store="";
		
		public storing (int VE,String name,int quantity,boolean demand) throws SQLException {
			this.name=name;
			this.quantity=quantity;
			if(demand==true) {
				store+=plts.get_pallet(this.name, this.quantity);
			}
			else {
				
					this.VE=VE;
					this.T_VE=this.VE*this.quantity;
					//check_shelfs();
					
					h_h_s.get_shelf(this.VE,this.quantity,this.T_VE,name);
					this.T_VE=h_h_s.get_remaindor();
					this.quantity=this.T_VE/this.VE;
					//System.out.println(this.T_VE);
					store+=h_h_s.get_store();
					
					if(this.quantity>0) {
						t_w_s.get_shelf(this.VE,this.quantity,this.T_VE,name);
						this.T_VE=t_w_s.get_remaindor();
						this.quantity=this.T_VE/this.VE;
						//System.out.println(this.T_VE);
						store+=t_w_s.get_store();
					}
					
					if(this.quantity>0) {
						s_m_s.get_z(this.VE,this.quantity,this.T_VE,name);
						this.T_VE=s_m_s.get_remaindor();
						this.quantity=this.T_VE/this.VE;
						//System.out.println(this.T_VE);
						store+=h_h_s.get_store();
					}
					if(this.quantity>0) {
						f_h_s.get_shelf(this.VE,this.quantity,this.T_VE,name);
						this.T_VE=f_h_s.get_remaindor();
						this.quantity=this.T_VE/this.VE;
						//System.out.println(this.T_VE);
						store+=f_h_s.get_store();
					}
					
					System.out.print(store);
				}
			}
		
		
		
		
		
		
		
/*		public void check_shelfs() throws SQLException {
			ResultSet rs=data_manager.check_shels_statuse(this.VE);
			int x=0;
			while (rs.next())
		      {
		         //this.shelfs[x]=rs.getNString("shelf_type");
		         this.shelfs[x]=rs.getString("shelf_type");
		         x++;
		      }
			while(this.T_VE>0) {
			}
		}*/

}
