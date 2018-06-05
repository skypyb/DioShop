package com.dioshop.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.dioshop.bean.OrderBean;
import com.dioshop.bean.SuperOrderBean;
import com.dioshop.dao.OrderDao;
import com.dioshop.dao.OrderItemDao;
import com.dioshop.pojo.Cart;
import com.dioshop.pojo.Order;
import com.dioshop.pojo.OrderItem;
import com.dioshop.pojo.User;
import com.dioshop.util.DateValueProcessor;
import com.dioshop.util.OrderItemSave;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

@WebServlet("/cart/orderServlet")
public class OrderServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("进了订单Servlet，action为:"+action);
		
		switch (action) {
		case "immediatelyBuy":
			immediatelyBuy(req,resp);
			break;
		case "submit":
			submit(req,resp);
			break;
		case "orderListShow":
			orderListShow(req,resp);
			break;
		case "orderFind":
			orderFind(req,resp);
			break;
		case "cartToOrder":
			cartToOrder(req,resp);
			break;
		
		default:
			break;
		}
		
	}
	/**
	 * 订单查找，通过order_id查找这个订单的信息。
	 * 保存成json返回
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void orderFind(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		OrderDao qd = new OrderDao();
		Order order = qd.findByOrderId(order_id);
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class, new DateValueProcessor());
 		JSONObject jo = (JSONObject) JSONSerializer.toJSON(order,config);
		
 		pw.print(jo);
 		pw.close();
	}
	/**
	 * 进入我的订单的时候执行的方法
	 * 获取当前用户所有的的订单存成一个集合List<Order>
	 * 将所有的订单集合遍历一遍，通过订单的主键查找该订单下所有的订单项List<OrderItem>
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void orderListShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrderDao qd = new OrderDao();
		List<Order> list =null;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		//获取当前用户所有的的订单存成一个集合List
		list = qd.findByUserId(user.getUser_id());
		if(list==null) {
			list = new ArrayList<>();
		}
		//创建一个超级订单bean集合准备用于接收信息（订单信息和该订单对应的订单bean集合信息）
		List<SuperOrderBean> sb = new ArrayList<>();
		for(Order o:list) {
			sb.add(new SuperOrderBean(o));
		}
		
		session.setAttribute("orderList", sb);
		
		resp.sendRedirect("lookOrder.jsp");
	}
	
	
	
	
	/**
	 * 立即购买时调用的方法
	 * @author pyb
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void immediatelyBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		User user = (User) req.getSession().getAttribute("user");
		int user_id = user.getUser_id();//获取用户的id
		
		OrderItem oi = new OrderItem();
		
		try {
			new BeanUtils().populate(oi, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		oi.setUser_id(user_id);
		List<OrderItem> list = new ArrayList<>();
		list.add(oi);
		
		
		orderMsg(req, resp, list);
	}
	
	
	/**
	 * 在购物车中点击提交订单的一瞬间，将购物车和数据库进行交叉修改，刷新一遍购物车
	 * 获取提交过来的信息
	 * 需要一个规格id的数组
	 * 通过用户id、规格id、商品id查询出一条数据（代表一个在数据库中真实存在的购物项）
	 * 此时获取一个orderitem对象。将此对象塞进集合中去，然后调用显示信息页面的方法
	 * 
	 * 
	 */
	protected void cartToOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取得pros_ids数组,即提交订单时里面的所有的id
		String[] pros_ids = req.getParameterValues("pros_ids");
		
		
		User user = (User) req.getSession().getAttribute("user");
		System.out.println(user.getUser_id());
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		OrderItemSave.saveOrderItem(OrderItemSave.cartToOrderItem(cart));
		List<OrderItem> orderItems = new OrderItemDao().findByPros_idAndUser_id(pros_ids, user.getUser_id());
		
		orderMsg(req, resp, orderItems);
	}
	
	/**
	 * 订单beanList生成并跳转到订单信息页面的方法
	 * 立即购买和从购物车中多选都走这个
	 * 最后将被选中的订单项封装为一个orderBeanList
	 * @param req
	 * @param resp
	 * @param list
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void orderMsg(HttpServletRequest req, HttpServletResponse resp,List<OrderItem> list) throws ServletException, IOException {
		
		//这里遍历提交过来的购物项集合，将每个购物项封装成一个购物Bean，方便拿数据
		List<OrderBean> orderBeanList = new ArrayList<>();
		
		for(OrderItem oi:list) {
			OrderBean ob= new OrderBean(oi);
			orderBeanList.add(ob);
		}
		HttpSession session = req.getSession();
		session.setAttribute("tempOrder", list);//temp:临时订单项
		session.setAttribute("orderBeanList", orderBeanList);//这个用于在页面提取数据
		resp.sendRedirect("orderMsg.jsp");
		PrintWriter pw = resp.getWriter();
		pw.print(true);
		pw.close();
	}
	
	
	/**
	 * 用户真的填写信息提交订单的话，就按照用户填写的信息在数据库中生成一个订单。
	 * 然后查看session中的订单项集合一个个遍历,查看他的订单项id（即其主键）
	 * 如果为0，将其订单外键改为新创建的订单主键，并添加进数据库
	 * 否则则将其空外键改为新创建的订单主键，然后将数据库和购物车进行同步更新
	 * 把传过来的集合直接和数据库进行交互修改，然后更新一波购物车
	 * @author pyb
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void submit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Order order = new Order();
		//把前端传过来的数据接收并且set进新创建的对象之中去
		String receiver = req.getParameter("receiver");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		
		User user = (User) req.getSession().getAttribute("user");
		int user_id = user.getUser_id();//获取登陆用户的id
		order.setUser_id(user_id);
		order.setReceiver(receiver);
		order.setMobile(mobile);
		order.setAddress(address);
		
		OrderDao od = new OrderDao();//用于创建订单的dao方法
		int order_id = od.createOrder(order);//这里返回的是订单主键，已经创建订单
		//获取到临时订单项的集合
		List<OrderItem> tempOrder = (List<OrderItem>) req.getSession().getAttribute("tempOrder");
		
		//这是对订单项进行操作的dao方法
		OrderItemDao itemDao = new OrderItemDao();
		for(OrderItem oi:tempOrder) {
			oi.setOrder_id(order_id);//使订单项指向一个订单
			
			//主键等于0代表这条数据在购物车是没有的，是一个临时的订单数据，即代表用户操作的是立即购买
			if(oi.getItme_id() ==0) {
				System.out.println(oi);
				itemDao.createQueryItem(oi);//将此条订单塞进数据库
				continue;
			}
			
			itemDao.updateQueryId(oi);//修改此订单在表中的数据
		}
		
		//更新购物车
		Cart cart = new Cart();
		cart.init(user_id);
		req.getSession().setAttribute("cart", cart);
		
		PrintWriter pw = resp.getWriter();
		
		pw.print(order_id);
		pw.close();
	}
}
