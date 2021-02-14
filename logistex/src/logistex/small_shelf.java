package logistex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


public class small_shelf {

	private int z[] = new int[13];
	 private int r_z;
	private int T_VE;
	private int VE;
	private int quantity;
	private int remainder;

	public void get_z(int VE, int quantity, int T_VE,String product_name) throws SQLException {
		this.T_VE = T_VE;
		this.VE = VE;
		this.quantity = quantity;
		// x=0;
//		int i=1;
//		while(this.T_VE>0) {
//			
//			ResultSet rs=data_manager.get_z(VE,i);
//			int x=1;
//			while (rs.next())
//		      {
//		         this.z[x] = rs.getInt("VE");
//
//		         //System.out.println(z[x]);
//		         x++;
//		      }
//			
//			/*
//			 * Arrays.sort(z,1,13); for(int y=1;y<13;y++) { for(int m=y+1;m<12;m++) {
//			 * if(this.z[m]>this.z[m+1]) { int p=this.z[m+1]; this.z[m+1]=this.z[m];
//			 * this.z[m]=p; x[m]++; x[m+1]--; } } }
//			 */
//			
//			//int min=500;
//			for(int j=1;j<=12;j++) {
//				if((VE<=this.z[j])) {
//					int f=this.z[j]/this.VE;
//					int q = this.quantity;
//					if(f>=q)
//					{
//					 this.T_VE = 0;
//					 this.quantity=0;
//					 this.z[j]-=(q*this.VE);
//						 
//					}
//					else {
//					this.T_VE=this.T_VE-(this.VE*f);
//					this.z[j]-=(f*this.VE);
//					this.quantity -= f;
//							
//					}
//
//				}
//			}
//			data_manager.update_z(this.z,i);
//			i++;
//		}
//		this.remainder=this.T_VE;

		/*
		 * int min =500; while(min==500) {
		 * 
		 * if(min<500) { break; } i++; } int f=this.z[r_z]/this.VE; int q
		 * =this.quantity; if(f>=q) { this.T_VE = 0; this.quantity=0;
		 * this.z[r_z]-=(q*this.VE);
		 * 
		 * } else { this.T_VE=this.T_VE-(this.VE*f); this.z[r_z]-=(f*this.VE);
		 * this.quantity -= f;
		 * 
		 * }
		 * 
		 * }
		 */
		 while(this.T_VE>0) {
			 int w=this.quantity;
			 int min=500;
			 int i=1;
			 while(min==500) {
				 ResultSet rs=data_manager.get_z(VE,i);
				 int x=1;
				 while (rs.next())
				 {
					 this.z[x] = rs.getInt("VE");
					 
					 //System.out.println(z[x]);
					 x++;
				 }
				 
				 for(int j=1;j<=12;j++) {
					 if((VE<=this.z[j]) && (min>this.z[j])) {
						 min=this.z[j];
						 this.r_z=j;
					 }
				 }
				 //if(min==500)
					 //continue;
				 int f =this.z[r_z]/this.VE;
					//min=this.XY[j];
					int q = this.quantity;
					if(f>=q)
					{
					 this.T_VE = 0;
					 this.quantity=0;
					 this.z[r_z]-=(q*this.VE);
						 
					}
					else {
					this.T_VE=this.T_VE-(this.VE*f);
					this.z[r_z]-=(f*this.VE);
					this.quantity -= f;
							
					}
					data_manager.update_z(this.z[r_z], i, this.r_z,product_name,w-this.quantity);
				 
				 if(min<500) {
					 break;
				 }
				 i++;
			 }
			 
		 }
			 
		 }

	public int get_remaindor() {
		return this.remainder;
	}

}
