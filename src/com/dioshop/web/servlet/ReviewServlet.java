package com.dioshop.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dioshop.bean.SuperOrderBean;
import com.dioshop.dao.OrderDao;
import com.dioshop.pojo.Order;

@WebServlet("/cart/reviewServlet")
public class ReviewServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Order order = new OrderDao().findByOrderId(Integer.parseInt(req.getParameter("order_id")));
		
		SuperOrderBean sob = new SuperOrderBean(order);
		System.out.println("来评价了");
		req.getSession().setAttribute("review", sob);
		
		resp.sendRedirect("review.jsp");
	}
}
