package com.dioshop.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dioshop.util.JDBCUtils;

public class Test {
	
	
	public static void main(String[] args) {
		
		Connection conn = JDBCUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		List<BigDecimal> pro_ids = null;
		Random ran = new Random();
		try {
			pro_ids = qr.query(conn, "select PRO_ID FROM SHOP_PRODUCT", new ColumnListHandler<>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(BigDecimal pro_id:pro_ids) {
			for(int i = 0;i<3;i++) {
				String color = null;
				switch (i) {
				case 0:
					color = "睿智黑";
					break;
				case 1:
					color = "珍珠白";
					break;
				case 2:
					color = "玫瑰红";
					break;
				}
				
				for(int j=0;j<3;j++) {
					String model = null;
					
					switch (j) {
					case 0:
						model = "M";
						break;
					case 1:
						model = "L";
						break;
					case 2:
						model = "XL";
						break;
					}
					
					try {
						qr.update(conn, "INSERT INTO SHOP_PROSPE VALUES(SEQ_SHOP_PROSPE.nextval,?,?,?,?)",
								new Object[] {pro_id,model,color,ran.nextInt(1000)});
					} catch (SQLException e) {
					}
					
				}
			}
			
		}
		
		
	}

}
