package com.dioshop.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dioshop.dao.OrderDao;

@WebServlet("/cart/orderFlow")
public class OrderFlow extends HttpServlet{
	private OrderDao orderDao  = new OrderDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		switch (action) {
		case "pay":
			pay(req,resp);
			break;
		case "removeOrderItem":
			removeOrderItem(req,resp);
			break;
		case "delivery":
			delivery(req,resp);
			break;
		case "confirm":
			confirm(req,resp);
			break;
		case "refund":
			refund(req,resp);
			break;

		default:
			break;
		}
	}
	//支付时调用的方法
	protected void pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		orderDao.updateOrderStatus(order_id, 1);
		resp.sendRedirect("paySuccess.jsp?order_id="+order_id);
		
	}
	//催卖家发货时调用的方法
	protected void delivery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		orderDao.updateOrderStatus(order_id, 2);
		resp.sendRedirect("orderServlet?action=orderListShow");
		
	}
	//确认收货时调用的方法
	protected void confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		orderDao.updateOrderStatus(order_id, 3);
		resp.sendRedirect("orderServlet?action=orderListShow");
	}
	//退款时调用的方法
	protected void refund (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		orderDao.updateOrderStatus(order_id, 4);
		resp.sendRedirect("orderServlet?action=orderListShow");
	}
	
	//删除时调用的方法
	protected void removeOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		orderDao.removeOrderItem(order_id);
		resp.sendRedirect("orderServlet?action=orderListShow");
	}
}
