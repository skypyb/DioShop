package com.dioshop.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dioshop.bean.ProBean;
import com.dioshop.pojo.ProImage;
import com.dioshop.pojo.Product;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet{
	private ProBean pb = new ProBean();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		System.out.println("这次访问参数为:"+action);
		switch (action) {
		case "menuInit":
			menuInit(req,resp);
			break;
		case "protShowInit":
			protShowInit(req,resp);
			break;
			
		default:
			break;
		}
		
	}
	
	//初始化菜单栏
	protected void menuInit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession();
		
		JSONObject jo;
		if(session.getAttribute("menu")==null) {
			Map<String, List<String>> map = pb.getMenu();
			jo = (JSONObject) JSONSerializer.toJSON(map);
			session.setAttribute("menu", jo);
		}else {
			jo =(JSONObject) session.getAttribute("menu");
		}
		
		
		pw.print(jo);
		pw.close();
		System.out.println("菜单已初始化");
	}
	
	//初始化商品分类显示
	protected void protShowInit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		HttpSession session = req.getSession();
		JSONObject jo;
		if(session.getAttribute("protShow")==null) {
			System.out.println("进的if");
			Map<String, List<Product>> protShow = pb.getProtShow();
			Map<String, List<ProImage>> protImages = pb.getProImages();
			
			
			JSONObject jo1 = (JSONObject) JSONSerializer.toJSON(protShow);
			JSONObject jo2 = (JSONObject) JSONSerializer.toJSON(protImages);
			
			jo = new JSONObject();
			jo.element("protShow", jo1);
			jo.element("proImages", jo2);
			session.setAttribute("protShow", jo);
		}else {
			System.out.println("进的else");
			jo =(JSONObject) session.getAttribute("protShow");
		}
		
		
		pw.print(jo);
		pw.close();
		System.out.println("分类显示已初始化");
	}
	
}
