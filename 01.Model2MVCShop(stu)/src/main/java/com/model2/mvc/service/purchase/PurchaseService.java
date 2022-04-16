package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


	public interface PurchaseService{
		
		public void  addPurchase(PurchaseVO purchaseVO) throws Exception; // 구매를 위한 비즈니스 수행  
		
		public PurchaseVO getPurchase(int tranNo)throws Exception;  // 구매정보 상세조회를 위한   
//		
		public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception;  // 구매목록 보기를 위한 
//		
//		public HashMap<String, Object> getSaleList(SearchVO seachVO) throws Exception;  // 판매목록 보기위를 위한 
//		
		public void updatePurchase(PurchaseVO purchaseVO) throws Exception;  // 구매 정보 수정을 위한 
//		
		public void updateTranCode(PurchaseVO purchaseVO) throws Exception; // 구매 상태코드 수정을 위한

		
	}

