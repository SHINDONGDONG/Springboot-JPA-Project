package com.cos.blog1;

import org.junit.jupiter.api.Test;

import com.cos.blog1.model.Reply1;

public class ReplyObjectTest {

	@Test
	public void toStringTest() {
		Reply1 reply = Reply1.builder()
				.id(1)
				.user1(null)
				.board1(null)
				.content("안녕")
				.build();
		
		System.out.println(reply); //오브젝트 출력시에 toString이 자동 출력됨
	}
}
