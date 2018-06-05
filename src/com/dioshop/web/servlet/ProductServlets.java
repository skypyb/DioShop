package com.dioshop.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dioshop.dao.ProImageDao;
import com.dioshop.dao.ProductDao;
import com.dioshop.pojo.Product;
import com.dioshop.pojo.Prospe;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
@WebServlet("/product")
public class ProductServlets extends HttpServlet {
	private ProductDao dao = new ProductDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("param");
		
		if("findSingleProduct".equals(action)) {
			findSingleProduct(request,response);//查找单个商品信息
		}else if("findImageSrc".equals(action)) {
			findImageSrc(request,response);//查找图片路径
		}else if("inventory".equals(action)) {
			findInventory(request,response);//查询库存
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
	
	/**根据接收的商品ID查找单个商品的详细信息
	 * @author lizp
	 * @param request
	 * @param response
	 */
	private void findSingleProduct(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String pid = request.getParameter("pid");
		Product currentProduct = dao.findSingleProduct(pid);
		int prot_id = currentProduct.getProt_id();
		session.setAttribute("currentProduct", currentProduct);
		try {
			response.sendRedirect("product_info.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**前台选择尺码和颜色后提交过来的数据,查询库存的多少，提交的数据有：当前商品
	 * ID，尺码，颜色,返回一个Prosep对象
	 * @author lizp
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void findInventory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pid = request.getParameter("pid");
		String model = request.getParameter("model");
		String color = request.getParameter("color");
		PrintWriter out = response.getWriter();
		Prospe prospe = dao.findInventory(pid,model,color);
		JSONObject json = (JSONObject)JSONSerializer.toJSON(prospe);
		out.print(json);
		
	}

}
