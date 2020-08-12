package com.cos.blog1.model.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog1.model.User1;
import com.cos.blog1.repository.UserRepository1;

@RestController //Data를 받는 어노테이션
public class DummyControllerTest {

	@Autowired
	private UserRepository1 userRepository1;
	
	//http://localhost:8000/blog1/dummy/join 에 요청
	//http의 body에 username,password,email을 가지고 요청하게 되면 파라메터에 받아진다.
	@PostMapping("/dummy/join")
	public String join(User1 user1) {
		System.out.println("Username : "+ user1.getUsername());
		System.out.println("password : "+ user1.getPassword());
		System.out.println("email : "+ user1.getEmail());
		System.out.println("Role : "+ user1.getRole());
		System.out.println("Create : "+ user1.getCreateDate());
		
		userRepository1.save(user1);
		
		return "회원가입이 완료 되었습니다";
	}
}
