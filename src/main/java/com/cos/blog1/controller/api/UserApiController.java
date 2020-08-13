package com.cos.blog1.controller.api;

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
	
	
}
