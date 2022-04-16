package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;

// - ���������� (Single Point of Entry)
//- Client �䱸���� �Ǵ�(?)
//- ��ó�� / ����ó��
//		- Work Flow Control :: ����, ���� ��..
//		- Client Form Data �ѱ� ó��
//- Business logic ���� (Bean Method Call)
//- Model �� View ����
//		- Business Logic ó�� ��� JSP ���� (Object Scope / VO ���)
//- ó���� ����� ����, JSP�� forward / sendRedirect : Navigation

public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources"); // �����ڵ�
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		
		String url = request.getRequestURI(); // ������Ʈ+���ϰ�α��� ������
		String contextPath = request.getContextPath(); // ������Ʈ path�� ������
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