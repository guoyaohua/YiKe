package com.lore.biz;

import java.util.List;

import com.lore.dao.MenueDetImpl;
import com.lore.entity.MenuDet;

public class MenueDetBean {
		
		private int pagenum;
		private int pagesize = 13;
		private int totalpage;
		private int typeid;
		private int total;
		
		
		MenueDetImpl m = new MenueDetImpl();
		public int getTotal() {

			int total = m.count();
			return total;
		}


		public void setTotal(int total) {
			this.total = total;
		}
		
		
		public int getTypeid() {
			return typeid;
		}


		public void setTypeid(int typeid) {
			this.typeid = typeid;
		}


		public List<MenuDet> findPage(int pagenum , String firstname){
			
			String sql = null;
			if (firstname != ""){
				
				sql = "select table1.menuId,table1.num," +
                "table2.price,table2.filename,table3.orderid,table3.orderTime,table3.tableId,table3.personNum,table3.isPay " +
                "from orderdetailtbl table1 " +
                "left join  menutbl table2 on table1.menuId = table2.id" +
                " left join ordertbl table3 on table1.id = table3.id where table3.orderid='" + firstname+ "'limit ?,?";
			
			}else{                                                 
				sql = "select table1.menuId,table1.num," +
                "table2.price,table2.filename,table3.orderid,table3.orderTime,table3.tableId,table3.personNum,table3.isPay " +
                "from orderdetailtbl table1 " +
                "left join  menutbl table2 on table1.menuId = table2.id" +
                " left join ordertbl table3 on table1.id = table3.id limit ?,? ";
			}
			//return fbi.findByPage(sql, pagenum, this.pagesize);
			return m.findByPage(sql, pagenum, this.pagesize);
			
		}
	
		
		public int getPagenum() {
			return pagenum;
		}
		public void setPagenum(int pagenum) {
			this.pagenum = pagenum;
		}
		
		public int getPagesize() {
			return pagesize;
		}
		public void setPagesize(int pagesize) {
			this.pagesize = pagesize;
		}
		
		


		//获得总的页数
		public int getTotalpage() {
			
		
			int total = m.count();
			if(total%pagesize==0){
				this.totalpage = total/pagesize;
			}else{
				this.totalpage = total/pagesize+1;
			}
			
			return this.totalpage;
		}
		public void setTotalpage(int totalpage) {
			this.totalpage = totalpage;
		}
		
		
	}


