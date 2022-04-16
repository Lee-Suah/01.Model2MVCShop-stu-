package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class PurchaseDAO {
   
   public PurchaseDAO(){
   }

   //구매정보 상세조회를 위한 
   public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
      
      Connection con = DBUtil.getConnection();

      String sql = "insert into transaction values (seq_transaction_tran_no.NEXTVAL,?,?,?,?,?,?,?,?,sysdate,?)";
      
      PreparedStatement stmt = con.prepareStatement(sql);
      
//      PurchaseVO purchase = new PurchaseVO();
//      UserVO buyer=purchase.getBuyer();
//      ProductVO purchaseProd = purchase.getPurchaseProd();
     
      stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
      stmt.setString(2, purchaseVO.getBuyer().getUserId());
      stmt.setString(3, purchaseVO.getPaymentOption());
      stmt.setString(4, purchaseVO.getReceiverName());
      stmt.setString(5,  purchaseVO.getReceiverPhone());
      stmt.setString(6, purchaseVO.getDivyAddr());
      stmt.setString(7, purchaseVO.getDivyRequest());
      stmt.setString(8, purchaseVO.getTranCode());
      stmt.setString(9, purchaseVO.getDivyDate().replace("-", "")); 
      
      stmt.executeUpdate();
          
      con.close();
   
   } //end of insertPurchase
   
   
   //구매이력조회 
   public HashMap<String,Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception {
      
      Connection con = DBUtil.getConnection();
      
      String sql = "SELECT * FROM transaction WHERE buyer_id='"+userId+"'";
      
      sql += " order by tran_no";
      
      PreparedStatement stmt = 
    		  // resultset의 위치를 이동시키는 쿼리 
         con.prepareStatement(  sql,
                                          ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                          ResultSet.CONCUR_UPDATABLE
                                          );
      ResultSet rs = stmt.executeQuery();
     
      rs.last();
      int total = rs.getRow();
      System.out.println("로우의 수:" + total);

      HashMap<String,Object> map = new HashMap<String,Object>();
      map.put("count", new Integer(total));

      rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
      System.out.println("searchVO.getPage():" + searchVO.getPage());
      System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
      
      ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
      if (total > 0) {
         for (int i = 0; i < searchVO.getPageUnit(); i++) {
            PurchaseVO vo = new PurchaseVO();
            UserVO uvo = new UserVO();
            ProductVO pvo = new ProductVO();
            
            
            vo.setTranNo(rs.getInt("tran_no"));
            uvo.setUserId(rs.getString("buyer_id"));
            vo.setBuyer(uvo);
            pvo.setProdNo(rs.getInt("prod_no"));
            vo.setPurchaseProd(pvo);
            
           // vo.setPurchaseProd(new ProductDAO().findProduct(rs.getInt("prod_no")));
            //vo.setBuyer(new UserDAO().findUser(rs.getString("user_id")));
            
            vo.setPaymentOption(rs.getString("payment_option"));
            vo.setReceiverName(rs.getString("receiver_name"));
            vo.setReceiverPhone(rs.getString("receiver_phone"));
            vo.setDivyAddr(rs.getString("demailaddr"));
            vo.setDivyRequest(rs.getString("dlvy_request"));
            vo.setTranCode(rs.getString("tran_status_code"));
            vo.setOrderDate(rs.getDate("order_data"));
            vo.setDivyDate(rs.getString("dlvy_date"));

            list.add(vo);
            if (!rs.next())
               break;
         }
      }
      
      System.out.println("list.size() : "+ list.size());
      map.put("list", list);
      System.out.println("map().size() : "+ map.size());
     

      con.close();
         
      return map;
   }
   
   // 구매 상세조회 
   public PurchaseVO findPurchase(int tranNo) throws Exception {
	      
	      Connection con = DBUtil.getConnection();

	      String sql = "select * from transaction where tran_no=?";
	      
	      PreparedStatement stmt = con.prepareStatement(sql);
	      stmt.setInt(1, tranNo);

	      ResultSet rs = stmt.executeQuery();

	      PurchaseVO purchaseVO = null;
	      while (rs.next()) {
	    	  purchaseVO = new PurchaseVO();
	          UserVO uvo = new UserVO();
	          ProductVO pvo = new ProductVO();
	            
	            purchaseVO.setTranNo(rs.getInt("tran_no"));
	            uvo.setUserId(rs.getString("buyer_id"));
	            purchaseVO.setBuyer(uvo);
	            pvo.setProdNo(rs.getInt("prod_no"));
	            purchaseVO.setPurchaseProd(pvo);
	            
	            purchaseVO.setPaymentOption(rs.getString("payment_option"));
	            purchaseVO.setReceiverName(rs.getString("receiver_name"));
	            purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
	            purchaseVO.setDivyAddr(rs.getString("demailaddr"));
	            purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
	            purchaseVO.setTranCode(rs.getString("tran_status_code"));
	            purchaseVO.setOrderDate(rs.getDate("order_data"));
	            purchaseVO.setDivyDate(rs.getString("dlvy_date"));
	         
	      }
	      
	      System.out.println("dao purchaseVO" +purchaseVO);
	      
	      con.close();
	      return purchaseVO;
	   }
   
   // 구매 정보수정 
   public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
	      
	      Connection con = DBUtil.getConnection();

	      String sql = "update transaction set payment_option=?,receiver_name=?,receiver_phone=?,demailaddr =?,dlvy_request=?,dlvy_date=? where tran_no = ?";
	      
	      PreparedStatement stmt = con.prepareStatement(sql);
	      stmt.setString(1, purchaseVO.getPaymentOption());
	      stmt.setString(2, purchaseVO.getReceiverName());
	      stmt.setString(3, purchaseVO.getReceiverPhone());
	      stmt.setString(4, purchaseVO.getDivyAddr());
	      stmt.setString(5, purchaseVO.getDivyRequest());
	      stmt.setString(6, purchaseVO.getDivyDate());
	      stmt.setInt(7, purchaseVO.getTranNo());
	     
	      stmt.executeUpdate();
	      
	      con.close();
	   }
   
   
// 구매 상태코드 수정 
   public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
	      
	      Connection con = DBUtil.getConnection();

	      String sql = "update transaction set tran_status_code= ? where prod_no = ?";
	      
	      PreparedStatement stmt = con.prepareStatement(sql);
//	      stmt.setString(1, purchaseVO.getPaymentOption());
//	      stmt.setString(2, purchaseVO.getReceiverName());
//	      stmt.setString(3, purchaseVO.getReceiverPhone());
//	      stmt.setString(4, purchaseVO.getDivyAddr());
//	      stmt.setString(5, purchaseVO.getDivyRequest());
//	      stmt.setString(6, purchaseVO.getDivyDate());
	      stmt.setString(1, purchaseVO.getTranCode());
	      stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
//	      stmt.setInt(3, purchaseVO.getTranNo());
	//      stmt.setInt(3, purchaseVO.getTranNo());
	     
	      stmt.executeUpdate();
	      
	      con.close();
	   }
   

   } // end of class
   