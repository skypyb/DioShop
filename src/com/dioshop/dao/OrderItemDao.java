package com.dioshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dioshop.pojo.OrderItem;
import com.dioshop.util.JDBCUtils;

public class OrderItemDao {
	private Connection conn;
	private QueryRunner qu;
	
	
	
	/**
	 * 修改订单项的QueryId（即让还在购物车的订单项指向一个真实存在的订单）
	 * @author pyb
	 * @param item_id
	 * @return
	 */
	public boolean updateQueryId(OrderItem itme) {
		boolean flag= false;
		
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		String sql = "update SHOP_ORDERITEM set order_id=? WHERE itme_id=?";
		
		try {
			int row = qu.update(conn, sql,new Object[] {itme.getOrder_id(),itme.getItme_id()});
			if(row>0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 通过传入的订单项对象创建一个订单项（当临时订单项确认提交订单时）
	 * @author pyb
	 * @param itme
	 * @return 一个布尔值，表示是否成功
	 */
	public boolean createQueryItem(OrderItem itme) {
		boolean flag= false;
		
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		String sql = "insert into SHOP_ORDERITEM values(seq_SHOP_ORDERITEM.nextval,?,?,?,?,?)";
		
		try {
			int row = qu.update(conn, sql,new Object[] {itme.getUser_id(),itme.getPro_id(),itme.getOrder_id(),
					itme.getQuantity(),itme.getPros_id()});
			if(row>0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 通过传入的pros_id数组和用户id
	 * 找出该用户所有提交的订单项，保存为一个集合
	 * @param pros_ids
	 * @param user_id
	 * @return
	 */
	public List<OrderItem> findByPros_idAndUser_id(String[] pros_ids, int user_id) {
		List<OrderItem> list = new ArrayList<OrderItem>();
		String sql = "SELECT * FROM SHOP_ORDERITEM WHERE pros_id=? AND user_id=? AND order_id is null";
		
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		try {
			for (String str : pros_ids) {
				OrderItem item = qu.query(conn, sql, new BeanHandler<>(OrderItem.class),new Object[] {str,user_id});
				list.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	/**
	 * 通过订单id查出该订单包含哪一些订单项
	 * @author pyb
	 * @param order_id
	 * @return
	 */
	public List<OrderItem> findByOrderId(int order_id){
		List<OrderItem> list =null;
		String sql = "select * from SHOP_ORDERITEM where order_id=?";
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		
		try {
			list = qu.query(conn, sql, new BeanListHandler<>(OrderItem.class),order_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 删除该用户在数据表中的所有购物车数据
	 * @return
	 */
	public boolean remove(int user_id) {
		boolean flag= false;
		
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		String sql = "DELETE SHOP_ORDERITEM WHERE user_id=? AND order_id is null";
		
		try {
			int row = qu.update(conn,sql,user_id);
			if(row>0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 通过用户id查出该用户买了些啥
	 * @author pyb
	 * @param order_id
	 * @return
	 */
	public List<OrderItem> findByUserId(int user_id){
		List<OrderItem> list =null;
		String sql = "select * from SHOP_ORDERITEM where user_id=? AND order_id is null";
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		
		try {
			list = qu.query(conn, sql, new BeanListHandler<>(OrderItem.class),user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	/**
	 * 添加购物项方法
	 * @param item
	 * @return
	 */
	public int addCartItem(OrderItem item) {
		int row = 0;
		String sql = "insert into shop_orderitem values(seq_shop_orderitem.nextval,?,?,null,?,?)";
		conn = JDBCUtils.getConnection();
		QueryRunner qu= new QueryRunner();
		try {
			row = qu.update(conn, sql, new Object[] { item.getUser_id(),item.getPro_id(),item.getQuantity(),item.getPros_id()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
	}
	
	
	/**
	 * 修改购物项方法
	 * @param item
	 * @return
	 */
	public int modifCartItem(OrderItem item) {
		int row = 0;
		String sql = "update shop_orderitem set quantity=? where itme_id=?";
		conn = JDBCUtils.getConnection();
		QueryRunner qu= new QueryRunner();
		try {
			row = qu.update(conn, sql, item.getQuantity(),item.getItme_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	
	
	/**
	 * 删除购物项方法
	 * @param itme_id
	 * @return
	 */
	public int deleteCartItem(String itme_id) {
		int row = 0;
		String sql = "delete shop_orderitem where itme_id=?";
		conn = JDBCUtils.getConnection();
		QueryRunner qu= new QueryRunner();
		try {
			row = qu.update(conn, sql, itme_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}
}
