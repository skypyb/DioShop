package com.dioshop.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dioshop.pojo.User;

@WebFilter(filterName="loginFilter",urlPatterns="/cart/*")
public class LoginFiter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		
		User user = (User) req.getSession().getAttribute("user");
		if(user==null || user.getUser_id()==0) {
			resp.sendRedirect("../login.jsp");
		}else {
			arg2.doFilter(req, resp);
		}
		
	}
	
}
