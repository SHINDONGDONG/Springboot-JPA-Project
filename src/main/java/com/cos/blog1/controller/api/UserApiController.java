package com.cos.blog1.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog1.dto.ResponseDto;
import com.cos.blog1.model.User1;
import com.cos.blog1.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save (@RequestBody User1 user1) {
		System.out.println("UserApiController save 호출");
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
	
	@PutMapping("/user")
	public ResponseDto<Integer> update (@RequestBody User1 user1){
		userService.update(user1);
		//여기서는 트랜잭션이 종료되기ㄸ문에 DB값은 변경
		//하지만 세션값은 변경되지 않은 상태 우리가 직접 세션값을 변경시켜줌
////		//세션등록 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user1.getUsername(), user1.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
}
