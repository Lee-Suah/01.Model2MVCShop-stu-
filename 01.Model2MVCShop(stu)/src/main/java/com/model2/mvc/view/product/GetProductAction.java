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

// ��ǰ�󼼺���(ui - �ǸŻ�ǰ����) 
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
//		Cookie[]cookies = request.getCookies(); // ��Ű ���� �������� 
//		// �����ͼ�, �����°��� ��Ʈ���̴ϱ�, ��ǥ�� �ٿ���,, �ѹ������߰��ϱ�,, �߰��ϴµ�,, 
//		// Ư�����ڷ� ���� ���ڵ� �����丮jsp���� �ϰ�, �׼��ؼ� ���ڵ� ���ָ�ȴ�.
//		String history = null;
//		if(cookie.getName().equals("history")) {
//			history =URLDecoder.decode(cookie.getValue()); 
			
		//	Cookie a = new a("history")
		//}
	
		
		
//		if(cookies !=null) {
//			System.out.println("client���� ���۵� ��Ű�� �ִ�.  ");
//			for(int i =0; i<cookies.length; i++) {
//				String name = cookies[i].getName();
//				String value = URLDecoder.decode(cookies[i].getValue());
//				System.out.println("client���� ���۵� cookie : "+name+"="+value);
//				
//				if(name.equals("name")) {
//					history=value;
//				}
//			}
//			
//		}else {
//			System.out.println("Ŭ���̾�Ʈ�� ���� ���۵� ��Ű�� �����ϴ�. ");
//		}
//		
		response.addCookie(cookie);
		
	
				
		return "forward:/product/getProductView.jsp";  // ��ǰ ����ȸ jsp�� �̵� 
	}
}