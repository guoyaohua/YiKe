package com.lore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import com.bean.CardBean;
import com.lore.biz.UploadBean;

public class UploadDao {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public int insert(UploadBean bean) {

		int n = 0;
		DBSource source = new SimpleDBSource();
		try {

			conn = source.getConn();
			String sql = "insert into menutbl(filename,fileExt,filePath,price,typename,remark,typeId) values(?,?,?,?,?,?,?)";
			
			
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getFileName());
			ps.setString(2, bean.getFileExt());
			ps.setString(3, bean.getFilePath());
			ps.setInt(4, bean.getPrice());
			ps.setString(5, bean.getTypename());
			ps.setString(6,bean.getRemark());
			ps.setInt(7,bean.getTypeId());
			n = ps.executeUpdate();

		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {

			try {
				source.closeConn(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return n;

	}

	public List<UploadBean> selectAll() {

		List<UploadBean> list = new ArrayList<UploadBean>();

		DBSource source = new SimpleDBSource();
		try {
			conn = source.getConn();
			String sql = "select * from upload";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				UploadBean bean = new UploadBean();
				bean.setId(rs.getInt("id"));
				bean.setFileName(rs.getString("fileName"));
				bean.setFileExt(rs.getString("fileExt"));
				bean.setFilePath(rs.getString("filePath"));
				list.add(bean);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			try {
				source.closeConn(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return list;

	}
}
