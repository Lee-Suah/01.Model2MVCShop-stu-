package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


	public interface PurchaseService{
		
		public void  addPurchase(PurchaseVO purchaseVO) throws Exception; // ���Ÿ� ���� ����Ͻ� ����  
		
		public PurchaseVO getPurchase(int tranNo)throws Exception;  // �������� ����ȸ�� ����   
//		
		public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception;  // ���Ÿ�� ���⸦ ���� 
//		
//		public HashMap<String, Object> getSaleList(SearchVO seachVO) throws Exception;  // �ǸŸ�� �������� ���� 
//		
		public void updatePurchase(PurchaseVO purchaseVO) throws Exception;  // ���� ���� ������ ���� 
//		
		public void updateTranCode(PurchaseVO purchaseVO) throws Exception; // ���� �����ڵ� ������ ����

		
	}
