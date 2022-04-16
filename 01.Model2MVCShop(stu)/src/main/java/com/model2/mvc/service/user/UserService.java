package com.model2.mvc.service.user;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.vo.UserVO;

// action.java���� �������̽�ȭ 

public interface UserService {
	
	public void addUser(UserVO userVO) throws Exception; // ȸ������ ��û 
	
	public UserVO loginUser(UserVO userVO) throws Exception; // �α��� ��û 
	
	public UserVO getUser(String userId) throws Exception; // �����󼼺��� ��û 
	
	public HashMap<String, Object> getUserList(SearchVO searchVO) throws Exception; // ���������ȸ
	
	public void updateUser(UserVO userVO) throws Exception; // �������� ������û 
	
	public boolean checkDuplication(String userId) throws Exception; // ���̵� �ߺ�Ȯ��
	
}