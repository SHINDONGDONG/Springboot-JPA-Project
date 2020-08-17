package com.cos.blog1.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void 해쉬_암호화() {
		String EncPassword = new BCryptPasswordEncoder().encode("1234");
		System.out.println("해쉬 비밀번호 : "+ EncPassword);
	}
}
