package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.vo.UserVO;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

// ���Ÿ�� ��û (ui - �����̷���ȸ) 
public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		SearchVO searchVO=new SearchVO();
		
	    HttpSession session = request.getSession();
	    UserVO userVO = (UserVO)session.getAttribute("user");
	    
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
	    searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		
		PurchaseService service=new PurchaseServiceImpl();
		HashMap<String,Object> map=service.getPurchaseList(searchVO, userVO.getUserId());
		
		//request.setAttribute("menu", menu);
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		//request.setAttribute("userId", userId);
		
		System.out.println(searchVO.getPage());
		
		return "forward:/purchase/listPurchaseView.jsp"; // ��ǰ �����ȸ ui �̵� 
	}
}