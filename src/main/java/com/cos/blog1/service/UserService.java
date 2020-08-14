package com.cos.blog1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog1.model.User1;
import com.cos.blog1.repository.UserRepository1;

@Service //컴포넌트 스캐너를 통해서 빈에 등록해준다.
public class UserService {

	@Autowired
	private UserRepository1 userRepository1;

	@Transactional
	public  void save(User1 user1) {
			userRepository1.save(user1);
	}
	
	@Transactional(readOnly = true)//SELECT 시작할때 트랜잭션 시작 서비스 종료시에 트랜잭션 종료.(정합성)
	public  User1 login(User1 user1) {
		return userRepository1.findByUsernameAndPassword(user1.getUsername(),user1.getPassword());
	}
	
}
