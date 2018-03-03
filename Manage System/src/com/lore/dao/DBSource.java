package com.lore.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBSource {

	public Connection getConn() throws SQLException;
	public void closeConn(Connection conn) throws SQLException;
}
