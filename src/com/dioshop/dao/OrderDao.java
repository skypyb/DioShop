package com.dioshop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dioshop.pojo.Order;
import com.dioshop.util.JDBCUtils;

public class OrderDao {
	
	private Connection conn;
	private QueryRunner qu;
	/*
	 * 	private int order_id;//主键值
		private int orderCode;//订单号
		private String address;//收货地址
		private String receiver;//收货人信息
		private int mobile;//手机号码
		private String userMessage;//备注
		private Date createDate;//订单创建日期
		private Date payDate;//支付日期
		private Date deliveryDate;//发货日期
		private Date confirmDate;//确认收货日期
		private int status;//订单状态
		private int user_id;//user_id,那个用户的订单
	 */
	/**
	 * 
	 * 此方法将一个订单类传入，将其所有的内容插入数据库（时间使用sysdate获得当前时间）
	 * @author pyb
	 * @param order
	 * @return 创建的订单主键
	 */
	public int createOrder(Order order) {
		int order_id = 0;
		//订单编号生成
		long orderCode = Long.parseLong(order.getUser_id()+"12450"+new Random().nextInt(999999999));
		
		String sql = "INSERT INTO SHOP_ORDER VALUES(Seq_Shop_Order.nextval,?,?,?,?,?,sysdate,null,null,null,0,?)";
		if(order.getUserMessage() == null) {
			order.setUserMessage("无");
		}
		try {
			conn = JDBCUtils.getConnection();
			qu= new QueryRunner();
			//将订单增加进数据库
			qu.update(conn, sql, new Object[] {orderCode,order.getAddress(),order.getReceiver(),
					order.getMobile(),order.getUserMessage(),order.getUser_id()});
			
			String sql2 = "select Seq_Shop_Order.currval from dual";
			//获取订单的主键
			BigDecimal curr = qu.query(conn, sql2, new ScalarHandler<>());
			order_id = curr.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return order_id;
	}
	
	/**
	 * 通过用户的id查询到该用户的所有订单
	 * @param user_id
	 * @return
	 */
	public List<Order> findByUserId(int user_id){
		List<Order> list =null;
		String sql = "select * from SHOP_ORDER where user_id=?";
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		
		try {
			list = qu.query(conn, sql, new BeanListHandler<>(Order.class),user_id);
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
	 * 通过订单id查询到该订单数据
	 * @param user_id
	 * @return
	 */
	public Order findByOrderId(int order_id){
		Order o = null;
		String sql = "select * from SHOP_ORDER where order_id=?";
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		
		try {
			o = qu.query(conn, sql, new BeanHandler<>(Order.class),order_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return o;
	}
	
	
	/**
	 * 修改订单状态，根据订单id和要修改的状态参数决定
	 * @param order_id
	 * @param status
	 * @return
	 */
	public boolean updateOrderStatus(int order_id,int status) {
		boolean flag = false;
		String sql;
		switch (status) {
		case 1://支付
			sql  = "UPDATE shop_order SET status=?,payDate=sysdate WHERE order_id=?";
			break;
		case 2://发货
			sql  = "UPDATE shop_order SET status=?,deliveryDate=sysdate WHERE order_id=?";
			break;
		case 3://收货
			sql  = "UPDATE shop_order SET status=?,confirmDate=sysdate WHERE order_id=?";
			break;
		case 4://申请退款
			sql  = "UPDATE shop_order SET status=? WHERE order_id=?";
			break;
		
		default:
			return false;
		}
		
		
		conn = JDBCUtils.getConnection();
		qu = new QueryRunner();
		
		try {
			int row = qu.update(conn, sql,new Object[] {status,order_id});
			if(row>0) 
				flag = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	/**
	 * 通过订单id删除该条订单
	 * @author pyb
	 * @param order_id
	 * @return
	 */
	public boolean removeOrderItem(int order_id) {
		boolean flag = false;
		qu = new  QueryRunner();
		conn = JDBCUtils.getConnection();
		
		
		String sql = "DELETE shop_orderitem where order_id=?";
		String sql2 = "DELETE shop_order where order_id=?";
		
		try {
			int row =qu.update(conn, sql,order_id);
			row += qu.update(conn, sql2,order_id);
			
			if(row>1) 
				flag = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
}
