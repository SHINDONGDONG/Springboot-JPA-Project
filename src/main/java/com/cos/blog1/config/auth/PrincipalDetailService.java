package com.cos.blog1.config.auth;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog1.model.User1;
import com.cos.blog1.repository.UserRepository1;

@Service//bean등록
public class PrincipalDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepository1 userRepository1;
	
	//스프링이 로그인 요청을 가로챌때 username,password변수를 가로채는데 패스워드 부분처리는 알아서함
	//username이 db에 있는지만 확인해주기.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User1 principal = userRepository1.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다."+username);
				});
		return new PrincipalDetail(principal); //시큐리티 세션에 유저정보가 저장됨.
	}

	
	
}
