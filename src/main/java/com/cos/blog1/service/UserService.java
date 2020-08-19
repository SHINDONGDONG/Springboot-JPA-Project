package com.cos.blog1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog1.model.RoleType;
import com.cos.blog1.model.User1;
import com.cos.blog1.repository.UserRepository1;

@Service //컴포넌트 스캐너를 통해서 빈에 등록해준다.
public class UserService {

	@Autowired
	private UserRepository1 userRepository1;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public  void save(User1 user1) {
			String rawPassword = user1.getPassword(); //1234
			String encPassword = encoder.encode(rawPassword);//해쉬
			user1.setPassword(encPassword);
			user1.setRole(RoleType.USER1);
			userRepository1.save(user1);
	}
	
	@Transactional
	public void update(User1 user1) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 user오브젝트 수정
		//select 해서  user오브젝트를 DB로부터 가져오는 이유는 영속화를 시키기위해
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		User1 persistance = userRepository1.findById(user1.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		String rawPassword = user1.getPassword();
		String encPassword =encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user1.getEmail());
		//회원수정 함수 종료 = 서비스종료 = 트랜잭션 종료(자동커밋) = 커밋이 자동으로된다.
		//영속화된 persistanc 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려준다.
	}
//	@Transactional(readOnly = true)//SELECT 시작할때 트랜잭션 시작 서비스 종료시에 트랜잭션 종료.(정합성)
//	public  User1 login(User1 user1) {
//		return userRepository1.findByUsernameAndPassword(user1.getUsername(),user1.getPassword());
//	}
	
}
