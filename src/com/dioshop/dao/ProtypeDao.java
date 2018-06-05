package com.dioshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dioshop.pojo.Protype;
import com.dioshop.util.JDBCUtils;

public class ProtypeDao {
	private Connection conn;
	private QueryRunner qu;

	
	/**
	 * 查shop_protype所有数据，保存为一个Protype集合
	 * @author pyb
	 * @return 所有大类型集合
	 */
	public List<Protype> findAll(){
		List<Protype> list = null;
		conn = JDBCUtils.getConnection();
		qu= new QueryRunner();
		try {
			list = qu.query(conn, "SELECT * FROM shop_protype", new BeanListHandler<Protype>(Protype.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NoSuchElementException ne) {
			list = findAll();
		}
		finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
		
		
	}
}
