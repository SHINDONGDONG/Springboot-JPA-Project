package com.cos.blog1.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog1.config.auth.PrincipalDetail;
import com.cos.blog1.dto.ResponseDto;
import com.cos.blog1.model.Board1;
import com.cos.blog1.model.RoleType;
import com.cos.blog1.model.User1;
import com.cos.blog1.service.BoardService;
import com.cos.blog1.service.UserService;

@RestController
public class BoardApiController {

	@Autowired
	BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board1 board1, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.save(board1, principal.getUser1());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board1 board) {
		System.out.println("boardapiput id: " + id);
		System.out.println("boardapiput board: " + board.getTitle());
		System.out.println("boardapiput board: " + board.getContent());
		boardService.update(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
