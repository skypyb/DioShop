package com.dioshop.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dioshop.dao.ProImageDao;
import com.dioshop.dao.ProductDao;
import com.dioshop.pojo.Cart;
import com.dioshop.pojo.CartItem;
import com.dioshop.pojo.Product;
import com.dioshop.pojo.Prospe;
import com.dioshop.pojo.User;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
@WebServlet("/cart/product")
public class ProductServlet extends HttpServlet {
	ProductDao dao = new ProductDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("param");
		System.out.println(action);
		if("productList".equals(action)) {
			findProductAll(request,response);//查找所有商品信息
		}else if("findImageSrc".equals(action)) {
			findImageSrc(request,response);//查找图片路径
		}else if("addCart".equals(action)) {
			addProductCart(request,response);//添加商品到购物车
		}else if("deleteItem".equals(action)) {
			deleteItem(request,response);//删除购物车中的单个商品
		}else if("emptyItems".equals(action)) {
			emptyItems(request,response);//清空购物车
		}
	}


	/**清空购物车的所有商品
	 * @author lizp
	 * @param request
	 * @param response
	 */
	private void emptyItems(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cart cart = new Cart();
		session.setAttribute("cart", cart);
	}
	
	
	
	/**删除购物车中的商品，根据客户端传过来的商品Id
	 * @author lizp
	 * @param request
	 * @param response
	 */
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		int pros_id = Integer.parseInt(request.getParameter("pid"));
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart!=null) {
			Map<Integer, CartItem> cartItems = cart.getCartItems();
			//更新商品的总计价钱
			cart.setTotal(cart.getTotal()-cartItems.get(pros_id).getSubtotal());
			cartItems.remove(pros_id);
		}
		session.setAttribute("cart", cart);
		try {
			response.sendRedirect("product_cart.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将商品直接添加进入到购物车
	 * @param request
	 * @param response
	 */
	private void addProductCart(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		//当前商品ID
		String pid = request.getParameter("pro_id");
		
		//当前商品规格ID
		int pros_id = Integer.parseInt(request.getParameter("pros_id"));
		
		//获取当前商品的购买数量
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//获取到当前图片的src地址
		String pro_img = request.getParameter("pro_img");
		//通过规格Id查找到一个Prospe对象
		Prospe prospe = dao.findProspeSingle(pros_id);
		//用当前商品ID查询出当前购买的是那一件商品
		Product product = dao.findSingleProduct(pid);
		//算出当前商品ID:当前商品ID*当前商品数量
		double subtotal = product.getPro_price()*quantity;
		//封装成CartItem对象
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setProspe(prospe);
		item.setSubtotal(subtotal);
		item.setBuyNum(quantity);
		item.setProImageSrc(pro_img);
		
		//获得购物车,前提是还得判断在session中是否已经存在购物车
		Cart cart = (Cart)session.getAttribute("cart");
		
		/*
		if(cart==null) {
			cart = new Cart();
			User user = (User) request.getSession().getAttribute("user");
			cart.init(user.getUser_id());
		}*/
		
		//选判断购物车中是否已将包含此购物项了，判断key是否存在
		//如果购物车中已经存在该商品，将现在买的数量与原有的数量进行相加操作
		Map<Integer, CartItem> cartItems = cart.getCartItems();
		double newsubtotal = 0.0;//用来记录商品的总价格
		if(cartItems.containsKey(pros_id)) {
			//取出原有商品的数量
			CartItem cartItem = cartItems.get(pros_id);
			System.out.println("cartItem="+cartItem);
			//取出旧的商品数量加新的商品数量
			int oldbuyNum = cartItem.getBuyNum();
			//添加新的商品数量
			cartItem.setBuyNum(oldbuyNum+=quantity);
			
			//取出原来的商品小计
			double oldsubtotal = cartItem.getSubtotal();
			//算出新加商品的小计
			  newsubtotal = product.getPro_price()*quantity;
			cartItem.setSubtotal(oldsubtotal+=newsubtotal);
		}else {
			//将购物项放到车中，将商品的的ID作为K，后期删除好操作;
			//如果车中没有该商品，直接添加，并算出小计
			cart.getCartItems().put(pros_id, item);
			newsubtotal = product.getPro_price()*quantity;
		}
		//放进购物车之前计算总计
		double total = cart.getTotal()+newsubtotal;
		cart.setTotal(total);
		
		//并将购物项放入到session
		session.setAttribute("cart", cart); 
		
		//添加成功向用户提示一下
		//System.out.println(session.getAttribute("cart"));
		
		try {
			response.sendRedirect("product_cart.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	

	/**根据商品ID查找图片路径返回一个字符串
	 * @author lizp
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void findImageSrc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int pro_id = Integer.parseInt(request.getParameter("pro_id"));
		PrintWriter out = response.getWriter();
		String imagesrc = new ProImageDao().findSingleImg(pro_id);
		out.print("images/productShow/"+imagesrc);
	}

	

	/**
	 * 根据接收客户端的传过来的大商品类型查找，查询所有商品，并放入session域中,
	 * 查询完成后利用重定向跳转到查询到的商品展示页面。
	 * @author lizp
	 * @param request
	 * @param response
	 */
	private void findProductAll(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		System.out.println(type);
		//客户端传入的商品类型查出所有商品
		List<Product> productList = dao.findProductAll(type);
		List<String> imageList = new ArrayList<>();
		for(Product product:productList) {
			int pro_id = product.getPro_id();
			//根据商品ID查询出图片的地址
			String src = dao.findProductImages(pro_id);
			imageList.add(src);
		}
		session.setAttribute("productList", productList);
		session.setAttribute("imageList", imageList);
		try {
			response.sendRedirect("product_list.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}