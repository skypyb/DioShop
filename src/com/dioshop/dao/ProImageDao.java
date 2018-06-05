package com.dioshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dioshop.pojo.ProImage;
import com.dioshop.util.JDBCUtils;

public class ProImageDao {
	private Connection conn;
	private QueryRunner qu;

	
	/**
	 * 通过商品id查询出其所有图片
	 * @author pyb
	 * @param proId
	 * @return 此商品的图片集合
	 */
	public List<ProImage> findByProId(int proId){
		
		List<ProImage> list = null;
		conn = JDBCUtils.getConnection();
		qu= new QueryRunner();
		String sql = "SELECT * FROM shop_pro_image WHERE pro_id=?";
		try {
			list = qu.query(conn, sql,new BeanListHandler<>(ProImage.class),proId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	public String findSingleImg(int proId) {
		
		List<String> list = null;
		conn = JDBCUtils.getConnection();
		qu= new QueryRunner();
		String sql = "SELECT img_src FROM SHOP_PRO_IMAGE WHERE img_id=(" + 
				"SELECT min(img_id) FROM SHOP_PRO_IMAGE where pro_id=? and img_type=0)";
		try {
			list = qu.query(conn, sql, new ColumnListHandler<>(),proId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(list.size()==0) {
			return "暂无图片.png";
		}
		
		
		return list.get(0);
	}
}
