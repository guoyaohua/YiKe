package com.lore.biz;

import java.util.List;


import com.lore.dao.MenutblImpl;
import com.lore.entity.Menutbl;

public class MenutblBean {
		
		private int pagenum;
		private int pagesize = 13;
		private int totalpage;
		private int typeid;
		private int total;
		
		MenutblImpl adi = new MenutblImpl();
		public int getTotal() {
			
			
			int total = adi.findCount(typeid);
			return total;
		}


		public void setTotal(int total) {
			this.total = total;
		}
		Menutbl m = new Menutbl();
		
		public int getTypeid() {
			return typeid;
		}


		public void setTypeid(int typeid) {
			this.typeid = typeid;
		}


		public List<Menutbl> findPage(int pagenum ,int typeid){
			this.setTypeid(typeid);
			
			
			return adi.findByPage(pagenum,this.pagesize,typeid);
			
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
			
			
			int total = adi.findCount(typeid);
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


