package com.dioshop.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter{

	private String encoder;
	
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)arg0;
		//设置请求对象的字符集
		req.setCharacterEncoding(encoder);
		
		HttpServletResponse resp = (HttpServletResponse)arg1;
		resp.setContentType("text/html;charset="+encoder);
		
		
		arg2.doFilter(req, resp);
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoder=filterConfig.getInitParameter("encoder");
	}
	
	
}
