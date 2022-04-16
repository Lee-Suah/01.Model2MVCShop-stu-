package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;

// - 단일인입점 (Single Point of Entry)
//- Client 요구사항 판단(?)
//- 선처리 / 공통처리
//		- Work Flow Control :: 권한, 인증 등..
//		- Client Form Data 한글 처리
//- Business logic 수행 (Bean Method Call)
//- Model 과 View 연결
//		- Business Logic 처리 결과 JSP 전달 (Object Scope / VO 사용)
//- 처리된 결과에 따라, JSP로 forward / sendRedirect : Navigation

public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources"); // 같은코딩
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		
		String url = request.getRequestURI(); // 프로젝트+파일경로까지 가져옴
		String contextPath = request.getContextPath(); // 프로젝트 path만 가져옴
		String path = url.substring(contextPath.length());
		System.out.println(path);
		
		try{
			Action action = mapper.getAction(path);
			action.setServletContext(getServletContext());
			
			String resultPage=action.execute(request, response);
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			
			if(resultPage.startsWith("forward:"))
				HttpUtil.forward(request, response, result);
			else
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}