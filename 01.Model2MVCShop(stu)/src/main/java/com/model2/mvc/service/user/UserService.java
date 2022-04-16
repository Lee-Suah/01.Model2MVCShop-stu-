package com.model2.mvc.service.user;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.vo.UserVO;

// action.java들의 인터페이스화 

public interface UserService {
	
	public void addUser(UserVO userVO) throws Exception; // 회원가입 요청 
	
	public UserVO loginUser(UserVO userVO) throws Exception; // 로그인 요청 
	
	public UserVO getUser(String userId) throws Exception; // 유저상세보기 요청 
	
	public HashMap<String, Object> getUserList(SearchVO searchVO) throws Exception; // 유저목록조회
	
	public void updateUser(UserVO userVO) throws Exception; // 유저정보 수정요청 
	
	public boolean checkDuplication(String userId) throws Exception; // 아이디 중복확인
	
}