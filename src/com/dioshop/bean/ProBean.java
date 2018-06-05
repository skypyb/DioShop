package com.dioshop.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dioshop.dao.ProImageDao;
import com.dioshop.dao.ProductDao;
import com.dioshop.dao.ProtypeDao;
import com.dioshop.pojo.ProImage;
import com.dioshop.pojo.Product;
import com.dioshop.pojo.Protype;

public class ProBean {
	
	
	
	//大类型名字对应的所有具体类型名字。(菜单栏需要)
	private Map<String, List<String>> menu = new HashMap<>();
	//大类型名字对应此大类型的所有商品集合(分类查询需要)
	private Map<String,List<Product>> protShow = new HashMap<>();
	//商品id所对应的该商品所有图片集合(显示商品图片需要)
	private Map<String,List<ProImage>> proImages  = new  HashMap<>();
	
	
	
	
	
	/**
	 * 此方法将获取一个K(商品大类型名):V(具体类型名集合)的map集合
	 * @author pyb
	 * @return 菜单栏map
	 */
	public Map<String, List<String>> getMenu() {
		ProtypeDao protDao = new ProtypeDao();
		ProductDao prodDao = new ProductDao();
		
		List<Protype> protList = protDao.findAll();;//所有商品集合
		if(menu!=null)
		menu.clear();
		
		
		List<String> pro;//用于保存具体类型的集合
		//遍历所有大类型，通过其主键值寻找出它包含的所有具体类型
		for(Protype prot:protList) {
			pro = prodDao.findNameByProtId(prot.getProt_id());
			
			if(pro==null||pro.size()==0)
				pro.add("暂且无货");
			
			menu.put(prot.getProt_name(), pro);
		}
		return menu;
	}

			
	/**
	 * 此方法将获取一个K(商品大类型名):V(该类型对应的所有商品List集合)的map集合
	 * @author pyb
	 * @return 首页显示商品map
	 */
	public Map<String,List<Product>> getProtShow(){
		ProtypeDao protDao = new ProtypeDao();
		ProductDao prodDao = new ProductDao();
		List<Protype> protList = protDao.findAll();;//所有商品集合
		if(protShow!=null) 
			protShow.clear();
		
		
		List<Product> pro;//用于保存对应商品的集合
		for(Protype prot:protList) {
			
			pro = prodDao.findByProtId(prot.getProt_id());
			
			protShow.put(prot.getProt_name(), pro);
		}
		
		return protShow;
	}
	
	
	/**
	 * 此方法将获取一个K(商品主键):V(该商品的所有图片List集合)的map集合
	 * @author pyb
	 * @return 每个商品对应的图片集合（map形式表示）
	 */
	public Map<String,List<ProImage>> getProImages(){
		ProImageDao proImageDao = new ProImageDao();
		ProtypeDao protDao = new ProtypeDao();
		ProductDao prodDao = new ProductDao();
		List<Protype> protList = protDao.findAll();;//所有商品集合
		if(proImages!=null) 
			proImages.clear();
		
		//查出所有商品
		List<Product> pro = prodDao.findAll();
		
		List<ProImage> images;//保存图片的集合
		for(Product p:pro) {
			images = proImageDao.findByProId(p.getPro_id());
			proImages.put(p.getPro_id()+"", images);
		}
		
		
		
		return proImages;
	}
	
	
	
}
