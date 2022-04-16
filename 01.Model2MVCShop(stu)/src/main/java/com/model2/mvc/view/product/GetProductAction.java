package com.model2.mvc.view.product;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

// 상품상세보기(ui - 판매상품관리) 
public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("prodNo"));
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		String menu = request.getParameter("menu");
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("menu", menu);
		
		
		Cookie cookie = new Cookie("history", Integer.toString(prodNo));
		//cookie.setMaxAge(60*60);
//		
//		Cookie[]cookies = request.getCookies(); // 쿠키 정보 가져오기 
//		// 가져와서, 가져온값이 스트링이니까, 쉼표로 붙여서,, 넘버값에추가하기,, 추가하는데,, 
//		// 특수문자로 들어가서 디코드 히스토리jsp에서 하고, 액션해서 인코드 해주면된다.
//		String history = null;
//		if(cookie.getName().equals("history")) {
//			history =URLDecoder.decode(cookie.getValue()); 
			
		//	Cookie a = new a("history")
		//}
	
		
		
//		if(cookies !=null) {
//			System.out.println("client에서 전송된 쿠키가 있다.  ");
//			for(int i =0; i<cookies.length; i++) {
//				String name = cookies[i].getName();
//				String value = URLDecoder.decode(cookies[i].getValue());
//				System.out.println("client에서 전송된 cookie : "+name+"="+value);
//				
//				if(name.equals("name")) {
//					history=value;
//				}
//			}
//			
//		}else {
//			System.out.println("클라이언트로 부터 전송된 쿠키가 없습니다. ");
//		}
//		
		response.addCookie(cookie);
		
	
				
		return "forward:/product/getProductView.jsp";  // 상품 상세조회 jsp로 이동 
	}
}