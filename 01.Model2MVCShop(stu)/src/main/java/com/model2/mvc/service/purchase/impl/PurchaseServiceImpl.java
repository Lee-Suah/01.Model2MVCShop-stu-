package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseServiceImpl implements PurchaseService{
   
   private PurchaseDAO purchaseDAO;
   
   public PurchaseServiceImpl() {
      purchaseDAO=new PurchaseDAO();
   }
   
   public void addPurchase(PurchaseVO purchaseVO) throws Exception {
	    purchaseDAO.insertPurchase(purchaseVO);
	  }

   public PurchaseVO getPurchase(int tranNo) throws Exception {
         return purchaseDAO.findPurchase(tranNo); 
   }
   
   public HashMap<String,Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception {
	      return purchaseDAO.getPurchaseList(searchVO, userId);
	   }

   public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
      purchaseDAO.updatePurchase(purchaseVO);
   }
   
   public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
	      purchaseDAO.updateTranCode(purchaseVO);
	   }

   
} // end of class