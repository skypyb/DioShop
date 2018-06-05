package com.dioshop.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dioshop.pojo.Product;
import com.dioshop.pojo.Prospe;
import com.dioshop.util.JDBCUtils;

public class ProspeDao {

	private Connection conn;
	private QueryRunner qu;
	
	
	/**根据客户端过来的商品规格ID查询一条数据
	 * @author pyb
	 * @param pid
	 * @return 返回一个Prospe对象
	 */
	public Prospe findSingleProspe(String pros_id) {
		Prospe prospe = null;
		conn = JDBCUtils.getConnection();
		qu= new QueryRunner();
		String sql = "select * from shop_prospe where pros_id=?";
		try {
			prospe = qu.query(conn, sql, new BeanHandler<Prospe>(Prospe.class),pros_id);
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
		return prospe;
	}
	
}
