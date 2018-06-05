package com.dioshop.web.servlet;

import java.io.IOException;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dioshop.util.SecurityCode;
import com.dioshop.util.SecurityImage;


@WebServlet("/imagecode")
public class imgServlet extends HttpServlet{
           @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	this.doPost(req, resp);
        }
           
           
           
           @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                  String code=SecurityCode.getSecurityCode();//先获取验证码
                  //保存验证码到Session
                  HttpSession session =req.getSession();
                  session.setAttribute("verifyCode", code);
                  //设置浏览器不缓存响应内容
                  resp.setContentType("image/jpeg");
                  resp.setHeader("Pragma", "NO-cache");
                  resp.setHeader("Cache-Control", "NO-cache");
                  //设置失效时间
                  resp.setDateHeader("Expires", 0);
                  //将生成的图片作为响应图片输出流，输出到客户端
                  ImageIO.write(SecurityImage.getImage(code), "JPEG", resp.getOutputStream());
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
        }
}
