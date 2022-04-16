<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.service.purchase.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>


<%
UserVO userVO = (UserVO)session.getAttribute("user");
System.out.println("유저브이오 : " +userVO);
%>

<%
	PurchaseVO vo2=(PurchaseVO)request.getAttribute("purchaseVO");
%>



<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%=vo2.getPurchaseProd().getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=userVO.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
		<%if(vo2.getPaymentOption().equals("1  ")){%>
			현금구매
			<%}else{ %>
				신용구매	
			 <%}%>	
			 		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%=vo2.getReceiverName()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%=vo2.getReceiverPhone()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%=vo2.getDivyAddr()%></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%=vo2.getDivyRequest()%></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%=vo2.getDivyDate()%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>