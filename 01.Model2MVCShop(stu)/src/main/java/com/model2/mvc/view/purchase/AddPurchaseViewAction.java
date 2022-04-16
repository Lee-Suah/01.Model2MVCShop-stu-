// ��ǰ ��� 
package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;

import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;
import javax.servlet.http.HttpSession;

// ���Ÿ� ���� ȭ�� ��û 
public class AddPurchaseViewAction extends Action {
	
	@Override
	public String execute( HttpServletRequest request,
												HttpServletResponse response) throws Exception {
	// ���� ���ǰ� �־��ֱ� 				
		//HttpSession session = request.getSession();
		//UserVO userVO = (UserVO)session.getAttribute("userVO");
	
		// ��ǰ��ȣ�� �ĺ��� 
		System.out.println(request.getParameter("prodNo"));
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service=new ProductServiceImpl();
		ProductVO productVO=service.getProduct(prodNo);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
}
