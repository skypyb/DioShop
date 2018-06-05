package com.dioshop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dioshop.pojo.Product;
import com.dioshop.pojo.Prospe;
import com.dioshop.util.JDBCUtils;

import sun.security.jca.GetInstance;

public class ProductDao {
	
	private Connection conn;
	private QueryRunner qu;

	
	
	
	/**
	 * 查所有的商品
	 * @author pyb
	 * @return 装了所有商品的集合
	 */
	public List<Product> findAll(){
		List<Product> list=null;
		
		String sql = "SELECT * FROM shop_product";
		try {
			conn = JDBCUtils.getConnection();
			qu= new QueryRunner();
			list = qu.query(conn, sql, new BeanListHandler<>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 通过传入的prot_id(大类型主键)查询出此类型中所包含的所有具体类型（名字）
	 * @author pyb
	 * @param prot_id 大类型主键
	 * @return 一个包含查询类型中所有具体类型的String集合
	 */
	public List<String> findNameByProtId(int prot_id){
		List<String> list=null;
		
		String sql = "SELECT pro_type FROM shop_product WHERE prot_id=? GROUP BY pro_type";
		try {
			conn = JDBCUtils.getConnection();
			qu= new QueryRunner();
			list = qu.query(conn, sql, new ColumnListHandler<>(),prot_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * 通过传入的prot_id(大类型主键)查询出此类型中所包含的所有具体商品，保存为一个商品集合
	 * @author pyb
	 * @param prot_id大类型主键
	 * @return
	 */
	public List<Product> findByProtId(int prot_id){
		List<Product> list = null;
		conn = JDBCUtils.getConnection();
		qu= new QueryRunner();
		String sql = "SELECT * FROM shop_product WHERE prot_id=?";
		try {
			list = qu.query(conn, sql, new BeanListHandler<>(Product.class),prot_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	/**根据前台给和类型，查询出指定类型的商品集合
	 * @author lizp
	 * @param String type 大类型商品的类型
	 * @return 返回一个指定类型的List集合;
	 */
	public List<Product> findProductAll(String type) {
		List<Product> list = null;
		conn = JDBCUtils.getConnection();
		qu= new QueryRunner();
		String sql = "select * from shop_product s,shop_pro_image a where s.pro_id=a.pro_id and s.prot_id=? ";
		try {
			list = qu.query(conn, sql, new BeanListHandler<Product>(Product.class),type);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}


	/**根据客户端过来的商品ID查询一条数据
	 * @author lizp
	 * @param pid
	 * @return 返回一个Product对象
	 */
	public Product findSingleProduct(String pid) {
		Product product = null;
		conn = JDBCUtils.getConnection();
		qu= new QueryRunner();
		String sql = "select * from shop_product where pro_id=?";
		try {
			product = qu.query(conn, sql, new BeanHandler<Product>(Product.class),pid);
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
		return product;
	}
	
	
	/**
	 * 查找单个规格对象
	 * @author lizp
	 * @param pros_id
	 * @return
	 */
	public Prospe findProspeSingle(int pros_id) {
		Prospe prospe = null;
		conn = JDBCUtils.getConnection();
		QueryRunner qu= new QueryRunner();
		String sql = "select * from shop_prospe where pros_id=?";
		try {
			prospe = qu.query(conn, sql, new BeanHandler<Prospe>(Prospe.class), pros_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prospe;
	}
	
	
	/**根据商品的ID,model,color查询出当前商品规格的ID;
	 * 返回一个Prosep对象
	 * @author lizp
	 * @param pid
	 * @param model
	 * @param color
	 * @return
	 */
	public Prospe findInventory(String pid, String model, String color) {
		Prospe prospe = null;
		conn = JDBCUtils.getConnection();
		QueryRunner qu= new QueryRunner();
		String sql = "select * from shop_prospe where pro_id=? and pro_size=? and pro_color=?";
		try {
			prospe = qu.query(conn, sql, new BeanHandler<Prospe>(Prospe.class), pid,model,color);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prospe;
	}
	
	/**
	 * 通过商品的Id查询图片地址
	 * @author lizp
	 * @param pro_id
	 * @return
	 */
	public String findProductImages(int pro_id) {
		List<String> list = null;
		conn = JDBCUtils.getConnection();
		QueryRunner qu= new QueryRunner();
		String sql = "Select img_src from SHOP_PRO_IMAGE where IMG_ID=(select  MIN(IMG_ID) from SHOP_PRO_IMAGE where pro_id=? and IMG_TYPE=0)";
		try {
			list = qu.query(conn, sql, new ColumnListHandler<>(),pro_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.size()==0){
			list.add("images/暂无图片.png");
		}
		return list.get(0);
	}
	
	
}
