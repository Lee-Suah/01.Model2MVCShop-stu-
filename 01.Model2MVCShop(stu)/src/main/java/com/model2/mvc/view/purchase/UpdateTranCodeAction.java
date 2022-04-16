package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

//  구매 상태코드 수정 (2 배송중으로) 
public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
	
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		ProductVO productVO = new ProductVO();
		PurchaseVO purchaseVO=new PurchaseVO();
		
		productVO.setProdNo(prodNo);
		purchaseVO.setPurchaseProd(productVO);
		
		//System.out.println("좋은말로 할때 되라..");
		
		purchaseVO.setTranCode(request.getParameter("tranCode"));
//		purchaseVO.setReceiverName(request.getParameter("receiverName"));
//		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
//		purchaseVO.setDivyAddr(request.getParameter("divyAddr"));
//		purchaseVO.setDivyRequest(request.getParameter("divyRequest"));
//		purchaseVO.setDivyDate(request.getParameter("divyDate"));
		
	    PurchaseService service=new PurchaseServiceImpl();
		service.updateTranCode(purchaseVO);
		
		return "redirect:/listProduct.do?menu=manage";
		//return "forward:/product/listProductView.jsp";
	}
}