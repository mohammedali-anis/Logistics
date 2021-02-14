package logistex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class therd_width_shelf {
//	private int XY[]=new int[33]; 
//	private int E;
//	private int R;
//	private int D;
//	private int X_Y;
	
	
	
	private int XY[]=new int[33]; 
	private int E=-1;
	private int R=-1;
	private int D=-1;
	private int remainder=0;
	private int quantity;
	private int VE;
	private int T_VE;
	private String store="";
	private int[] m_XY=new int[33];
	public therd_width_shelf() {
		
	}
	
	public void get_shelf(int VE,int quantity,int T_VE,String name) throws SQLException {
		//System.out.print(2);
		this.T_VE=T_VE;
		this.quantity=quantity;
		this.VE=VE;
		this.remainder=T_VE;
		ResultSet rs=data_manager.get_t_h_h_s(VE);
		while (rs.next())
	      {
	         this.E = rs.getInt("E");
	         this.R = rs.getInt("R");
	         this.D = rs.getInt("D");

	         //System.out.println(E+" "+R+" "+D);
	         //x++;
	      }
			
		while((this.E!=-1 && this.R!=-1 && this.D!=-1)  && (this.T_VE>0)) {
			 this.E = -1;
	         this.R = -1;
	         this.D = -1;
			
			rs=data_manager.get_t_h_h_s(VE);
			while (rs.next())
		      {
		         this.E = rs.getInt("E");
		         this.R = rs.getInt("R");
		         this.D = rs.getInt("D");

		         //System.out.println(E+" "+R+" "+D);
		         //x++;
		      }
			this.get_XY(VE,name);
		}

	}
	
	public void get_XY(int VE,String product_name) throws SQLException {
		ResultSet rs=data_manager.get_XY_t_h_h(this.E,this.R,this.D);
		int x=0;
		while (rs.next())
	      {
			x++;
			this.XY[x] = rs.getInt("VE");
			this.m_XY[x] = rs.getInt("VE");

	         //System.out.println(this.XY[x]);
	         //x++;
	      }
		int min=500;
		int j=1;
		Arrays.sort(XY,1,33);
		int w=this.quantity;
		//for(j=1;j<=96;j++) {
			//System.out.println(XY[j]);}
			//if((VE<=this.XY[j]) && (min>this.XY[j])) {
				while(this.T_VE>0 ) {
					if(j==33)
						break;
					if(this.VE>this.XY[j] ) {
						j++;
						continue;
					}
					//System.out.println(j);
					int f =this.XY[j]/this.VE;
					//min=this.XY[j];
					int q = this.quantity;
					if(f>=q)
					{
					 this.T_VE = 0;
					 this.quantity=0;
					 boolean B=true;
					 for(int I=1;I<33;I++) {
						 if((this.XY[j]==this.m_XY[I]) && B==true) {
							 this.m_XY[I]-=(q*this.VE);
							 B=false;
							 
						 }
						
					 }
					 this.XY[j]-=(q*this.VE);
						 
					}
					else {
					this.T_VE=this.T_VE-(this.VE*f);
					 boolean B=true;
					 for(int I=1;I<33;I++) {
						 if((this.XY[j]==this.m_XY[I]) && B==true) {
							 this.m_XY[I]-=(f*this.VE);
							 B=false;
							 
						 }
						
					 }
					this.XY[j]-=(f*this.VE);
					this.quantity -= f;
							
					}
					j++;
					this.remainder=this.T_VE;
				}
//			}
		//}
		
		//this.XY[X_Y]=this.XY[X_Y]-VE;
		
		//System.out.println(this.XY[X_Y]);
		//System.out.println(X_Y);
		
		int max =0;
		for(int i=1;i<=32;i++) {
			if(max<this.XY[i]) {
				max=this.XY[i];
			}
			
		}
		data_manager.update_XY_t(this.m_XY,this.E,this.R,this.D,max,product_name,w-this.quantity);
		this.store+="E"+E+"R"+R+"D"+D+",";
	}
	public int get_remaindor() {
		return this.remainder;
	}

	public String get_store() {
		return this.store;
	}
	
	
	
	
	
	
	
	
	
	
	
//	public void get_shelf(int VE) throws SQLException {
//		ResultSet rs=data_manager.get_t_h_h_s(VE);
//		while (rs.next())
//	      {
//	         this.E = rs.getInt("E");
//	         this.R = rs.getInt("R");
//	         this.D = rs.getInt("D");
//
//	         System.out.println(E+" "+R+" "+D);
//	         //x++;
//	      }
//		this.get_XY(VE);
//
//	}
//	
//	public void get_XY(int VE) throws SQLException {
//		ResultSet rs=data_manager.get_XY_t_h_h(this.E,this.R,this.D);
//		int x=0;
//		while (rs.next())
//	      {
//			x++;
//			this.XY[x] = rs.getInt("VE");
//
//	         System.out.println(this.XY[x]);
//	         //x++;
//	      }
//		int min=500;
//		for(int j=1;j<=32;j++) {
//			if((VE<=this.XY[j]) && (min>this.XY[j])) {
//				min=this.XY[j];
//				this.X_Y=j;
//			}
//		}
//		this.XY[X_Y]=this.XY[X_Y]-VE;
//		
//		System.out.println(this.XY[X_Y]);
//		System.out.println(X_Y);
//		
//		int max =0;
//		for(int i=1;i<=32;i++) {
//			if(max<this.XY[i]) {
//				max=this.XY[i];
//			}
//			
//		}
//		data_manager.update_XY_t(this.XY[X_Y],this.E,this.R,this.D,this.X_Y,max);
//	}
//
//
}
