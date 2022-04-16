package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class ProductDAO {
   
   public ProductDAO(){
   }

   //상품등록
   public void insertProduct(ProductVO productVO) throws Exception {
      
      Connection con = DBUtil.getConnection();

      String sql = "insert into PRODUCT values (seq_product_prod_no.NEXTVAL,?,?,?,?,?,sysdate)";
      
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, productVO.getProdName());
      stmt.setString(2, productVO.getProdDetail());
      stmt.setString(3, productVO.getManuDate().replace("-", ""));
      stmt.setInt(4, productVO.getPrice());
      stmt.setString(5, productVO.getFileName());
      
      stmt.executeUpdate();
          
      con.close();
   }

   //상품조회
   public ProductVO findProduct(int prodNo) throws Exception {
      
      Connection con = DBUtil.getConnection();

      String sql = "select * from product where prod_No=?";
      
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, prodNo);

      ResultSet rs = stmt.executeQuery();

      ProductVO productVO = null;
      while (rs.next()) {
         productVO = new ProductVO();
         
         productVO.setFileName(rs.getString("image_file"));
         productVO.setManuDate(rs.getString("manufacture_day"));
         productVO.setPrice(rs.getInt("price"));
         productVO.setProdDetail(rs.getString("prod_detail"));
         productVO.setProdName(rs.getString("prod_name"));
         productVO.setProdNo(rs.getInt("prod_no"));
         productVO.setRegDate(rs.getDate("reg_date"));
         //productVO.setProTranCode(rs.getString("PROTRANCODE"));
         
      }
      
      System.out.println(productVO);
      
      con.close();
      return productVO;
   }

   //상품 목록조회
   public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
	      
	      Connection con = DBUtil.getConnection();
	      
	      String sql = "select p.prod_no, p.prod_name, p.price, p.reg_date, NVL(t.tran_status_code,0) from product p, transaction t where p.prod_no = t.prod_no(+)";
	      if (searchVO.getSearchCondition() != null) {
	         if (searchVO.getSearchCondition().equals("0")) {
	            sql += " and prod_no=' " + searchVO.getSearchKeyword()
	                  + " ' ";
	         }if (searchVO.getSearchCondition().equals("1")) {
	            sql += " and prod_name like '%"+searchVO.getSearchKeyword()+"%' ";
	                  
	         }
	         if (searchVO.getSearchCondition().equals("2")) {
	             sql += " and price='" + searchVO.getSearchKeyword()
	                   + "'"; 
	          }
	      }
	      
	      sql += "order by p.prod_no";

	      PreparedStatement stmt = 
	         con.prepareStatement(   sql,
	                                          ResultSet.TYPE_SCROLL_INSENSITIVE,
	                                          ResultSet.CONCUR_UPDATABLE);
	      ResultSet rs = stmt.executeQuery();

	      rs.last();
	      int total = rs.getRow();
	      System.out.println("로우의 수:" + total);

	      HashMap<String,Object> map = new HashMap<String,Object>();
	      map.put("count", new Integer(total));

	      rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
	      System.out.println("searchVO.getPage():" + searchVO.getPage());
	      System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

	      ArrayList<ProductVO> list = new ArrayList<ProductVO>();
	      if (total > 0) {
	         for (int i = 0; i < searchVO.getPageUnit(); i++) {
	            ProductVO vo = new ProductVO();
	            //PurchaseVO pv = new PurchaseVO();
	            //vo.setFileName(rs.getString("image_file"));
	            //vo.setManuDate(rs.getString("manufacture_day"));
	            vo.setPrice(rs.getInt("price"));
	            //vo.setProdDetail(rs.getString("prod_detail"));
	            vo.setProdName(rs.getString("prod_name"));
	            vo.setProdNo(rs.getInt("prod_no"));
	            vo.setRegDate(rs.getDate("reg_date"));
	            vo.setProTranCode(rs.getString("NVL(t.tran_status_code,0)"));
	            
	            System.out.println("네임"+vo.getProdName());

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

   //상품 정보수정
   public void updateProduct(ProductVO productVO) throws Exception {
      
      Connection con = DBUtil.getConnection();

      String sql = "update product set prod_name=?, prod_detail=?, manufacture_day=?, price=?, image_file=? where prod_no=?";
      
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1,productVO.getProdName());
      stmt.setString(2,productVO.getProdDetail());
      stmt.setString(3, productVO.getManuDate());
      stmt.setInt(4, productVO.getPrice());
      stmt.setString(5, productVO.getFileName());
      stmt.setInt(6, productVO.getProdNo());
   
      stmt.executeUpdate();
      
      con.close();
   }
}