package com.model2.mvc.service.product;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;


	public interface ProductService{
		
		public void  addProduct(ProductVO productVO) throws Exception; // 상품등록  
		
		public ProductVO getProduct(int prodNo)throws Exception;  // 상품상세보기
		
		public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception; // 상품조회
		
		public void updateProduct(ProductVO productVO) throws Exception;  // 상품정보수정
	}

