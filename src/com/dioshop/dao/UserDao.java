package com.dioshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import com.dioshop.pojo.User;
import com.dioshop.util.JDBCUtils;
import com.dioshop.util.MD5;




public class UserDao {
	private Connection conn;
	private QueryRunner query;
	
	
	/**
	 * 
	 * 通过服务器传过来的User对象，将此对象添加到数据库
	 * @author CM
	 * @param ur（ur是服务器传过来对象）
	 * @return 影响的行数
	 */
	public int addUser(User ur) {
		int row =0;
		String sql="insert into shop_user values(seq_shop_user.nextval,?,?,?)";
		try {
			conn=JDBCUtils.getConnection();//拿连接对象
			query=new QueryRunner();//创建run
			MD5 md5=MD5.getInstance();
			String pwd=md5.getMD5ofStr(ur.getUser_pwd());
			row=query.update(conn, sql, new Object[] {ur.getUser_name(),pwd,ur.getUser_type()});
		} catch (SQLException e) {//专门针对sql异常
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);//关闭连接
		}
		return row;
	}
	
	/**
	 * 通过服务器传过来的用户id，根据此id查找出对应的用户
	 * @author CM
	 * @param user_id
	 * @return 返回此id对应的用户
	 */
	public User findUser(int user_id) {
		User ur =new User();
		String sql="select * from shop_user where user_id=?";
		try {
	  		   conn=JDBCUtils.getConnection();
	 			   query=new QueryRunner();
	 			   ur=query.query(conn, sql,new BeanHandler<User>(User.class),user_id);
			} catch (SQLException e) {
				DbUtils.closeQuietly(conn);
			}
	  	   finally {
	 			DbUtils.closeQuietly(conn);
	 		}

		return ur;
		
	}
	/**
	 * 调用该方法查询所有的用户 
	 * @author CM
	 * @return 包含所有用户的集合
	 */
	public List<User> findAll(){
		List<User> list =null;
		String sql="select * from shop_user";
		try {
			conn=JDBCUtils.getConnection();
			query=new QueryRunner();
			list=query.query(conn, sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return list;
	}
	
	/**
	 * 通过服务器传过来的一个user对象，来修改里面的密码
	 * @author CM
	 * @param ur
	 * @return 影响的行数
	 */
	
	public int alterUser(User ur) {
		int row =0;
		String sql ="update shop_user set user_pwd=? WHERE user_id=?";
		try {
			conn=JDBCUtils.getConnection();
			query=new QueryRunner();
			MD5 md5=MD5.getInstance();
			String pwd=md5.getMD5ofStr(ur.getUser_pwd());
			row=query.update(conn, sql, new Object[] {pwd,ur.getUser_id()});
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}

		return row;
	}
	/**
	 * 通过服务器传过来的用户名字，根据此名字判断客户能否创建名字
	 * @author CM
	 * @param user_name
	 * @return 返回此用户的名字
	 */
	
	public User findUsername(String user_name) {
		User ur =new User();
		String sql="select * from shop_user where user_name=?";
		try {
	  		   conn=JDBCUtils.getConnection();
	 			   query=new QueryRunner();
	 			   ur=query.query(conn, sql,new BeanHandler<User>(User.class),user_name);
			} catch (SQLException e) {
				DbUtils.closeQuietly(conn);
			}
	  	   finally {
	 			DbUtils.closeQuietly(conn);
	 		}

		return ur;
		
	}
	
	
	
}
    





