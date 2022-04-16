package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

// 상품 목록 조회 (ui -상품검색) 
public class ListProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		SearchVO searchVO=new SearchVO();
		
		String menu = request.getParameter("menu");
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service=new ProductServiceImpl();
		HashMap<String,Object> map=service.getProductList(searchVO);
		
		request.setAttribute("menu", menu);
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		if(menu.equals("search")) {
			return "forward:/product/listProductView.jsp"; // 상품 목록조회 ui 이동 
		}else {
			return "forward:/product/listSaleView.jsp"; //  상품관리 ui 로 이동 
		}
		
		//return "forward:/product/listProductView.jsp"; // 상품 목록조회 ui 이동 
	}
}