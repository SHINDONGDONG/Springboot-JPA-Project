package com.cos.blog1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog1.model.Board1;
import com.cos.blog1.repository.BoardRepository1;

@RestController
public class ReplyTestController {

	@Autowired
	private BoardRepository1 boardRepository1;
	
	@GetMapping("/test/board/{id}")
	public Board1 getBoard(@PathVariable int id) {
		return boardRepository1.findById(id).get(); //jackson 라이브러리 호출(오브젝트를 ->json으로 리턴) -> 모델의 getter호출
	}
}
