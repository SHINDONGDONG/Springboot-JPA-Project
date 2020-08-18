package com.cos.blog1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog1.model.Board1;
import com.cos.blog1.model.RoleType;
import com.cos.blog1.model.User1;
import com.cos.blog1.repository.BoardRepository1;
import com.cos.blog1.repository.UserRepository1;

@Service //컴포넌트 스캐너를 통해서 빈에 등록해준다.
public class BoardService {

	@Autowired
	private BoardRepository1 boardRepository1;

	
	@Transactional
	public  void save(Board1 board1,User1 user1) { //title ,content
		board1.setCount(0);
		board1.setUser1(user1);
		boardRepository1.save(board1);
	}
	
}
