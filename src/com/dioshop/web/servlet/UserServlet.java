package com.dioshop.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.dioshop.dao.UserDao;
import com.dioshop.pojo.User;
import com.dioshop.util.MD5;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
@WebServlet("/user")
public class UserServlet extends HttpServlet{
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				this.doPost(req, resp);
			}
			
			
			
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				String method =req.getParameter("method");
				if("register".equals(method)) {
					addition(req,resp); //这个是注册的方法
				}else if("checkname".equals(method)) {
					findname(req,resp);//这个是判断数据库有没有名字重复的方法
				}else if("login".equals(method)) {
					findid(req,resp);//这个是登录的方法
				}else if("remove".equals(method)) {
					req.getSession().removeAttribute("user");
					resp.sendRedirect("index.jsp");
				}
			}



			private void findid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
				UserDao ud =new UserDao();
				//User uf =new User();
			    String user_name =req.getParameter("user_name");
			    String user_pwd =req.getParameter("user_pwd");
			    String verifyCode=req.getParameter("verifyCode");
			    //System.out.println(verifyCode);
			    MD5 md5=MD5.getInstance();
				String pwd=md5.getMD5ofStr(user_pwd);
			    HttpSession session=req.getSession();
                PrintWriter out =resp.getWriter();
                JSONObject jo=new JSONObject();
			    //User u = ud.findUsername(user_name);
			    String code =(String)session.getAttribute("verifyCode");
			    if(!verifyCode.equals(code)) {
              	  jo.element("pass", false);
              	  jo.element("msg", "验证码输出错误");
                }else {
              	  
              	  
              	  User user =ud.findUsername(user_name);
              	  
              	  //System.out.println(ur);
              	  if(user==null || !user.getUser_pwd().equals(pwd)) {
        				jo.element("pass", false);
        				jo.element("msg", "用户名或密码错误！");
        			}else {
        				jo.element("pass", true);
        				session.setAttribute("user", user);
        			}
                }
			    out.print(jo);
			    out.close();

				
			}



			private void findname(HttpServletRequest req, HttpServletResponse resp) throws IOException {
				//这个是判断用户名是否能够注册
				PrintWriter out =resp.getWriter();
				String user_name =req.getParameter("user_name");
				UserDao ud =new UserDao();
				User ur =ud.findUsername(user_name);
				if(ur==null) {
			    	   out.print(true);
			       }else {
			    	   out.print(false);
			       }
                 out.close();
				
				
			}



			private void addition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
				PrintWriter out =resp.getWriter();
				UserDao ud =new UserDao();
				User ur =new User();
				String verifyCode=req.getParameter("verifyCode");//获取参数
                HttpSession session=req.getSession();//拿到session
				try {
					BeanUtils.populate(ur, req.getParameterMap());
					//System.out.println(ur);
					int i =ud.addUser(ur);
					JSONObject js =(JSONObject)JSONSerializer.toJSON(i);
					String code =(String)session.getAttribute("verifyCode");//获取session里面的验证码
					if(!verifyCode.equals(code)) {
						js.element("pass", false);
                  	  	js.element("mssg", "验证码输出错误");
					}else {
						js.element("pass", true);
					}
					out.print(js);
					out.close();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
}
