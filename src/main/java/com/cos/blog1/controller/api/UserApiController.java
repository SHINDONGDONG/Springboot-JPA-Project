package com.cos.blog1.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog1.dto.ResponseDto;
import com.cos.blog1.model.RoleType;
import com.cos.blog1.model.User1;
import com.cos.blog1.service.UserService;

@RestController
public class UserApiController {

	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save (@RequestBody User1 user1) {
		System.out.println("UserApiController save 호출");
		user1.setRole(RoleType.USER1);
		userService.save(user1);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//스프링 시큐리티를 이용하여 로그인하기. 할거임.
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User1 user1, HttpSession session){
	 * System.out.println("UserApiController의 login접속됨"); User1 principal =
	 * userService.login(user1); //principal 접근주체 if(principal != null) {
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
	
}
