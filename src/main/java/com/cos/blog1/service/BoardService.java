package com.cos.blog1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog1.dto.ReplySaveRequestDto;
import com.cos.blog1.model.Board1;
import com.cos.blog1.model.Reply1;
import com.cos.blog1.model.RoleType;
import com.cos.blog1.model.User1;
import com.cos.blog1.repository.BoardRepository1;
import com.cos.blog1.repository.ReplyRepository;
import com.cos.blog1.repository.UserRepository1;

@Service //컴포넌트 스캐너를 통해서 빈에 등록해준다.
public class BoardService {

	@Autowired
	private BoardRepository1 boardRepository1;

	@Autowired
	private UserRepository1 userRepository1;

	@Autowired
	private ReplyRepository replyRepository;
	
	public  void save(Board1 board1,User1 user1) { //title ,content
		board1.setCount(0);
		board1.setUser1(user1);
		boardRepository1.save(board1);
	}
	
	@Transactional(readOnly = true)
	public Page<Board1> list (Pageable pageable){
		return boardRepository1.findAll(pageable);
	}
	
	public Board1 detail(int id) {
		return boardRepository1.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void delete(int id) {
		boardRepository1.deleteById(id);
	}
	
	@Transactional
	public void update(int id,Board1 requestboard) {
		Board1 board = boardRepository1.findById(id)
				.orElseThrow(()->{
			return new IllegalArgumentException("글찾기 실패.");
		});//영속화완료
		board.setTitle(requestboard.getTitle());
		board.setContent(requestboard.getContent());
		//해당함수가 종료시 서비스단에서 종료돌때 트랜잭션이 종료된다.
		//이때 더티체킹이 일어남 (자동업데이트)
	}
	
	@Transactional
	public void replySave(ReplySaveRequestDto replySaveRequestDto) {
//		User1 user1 = userRepository1.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//			return new IllegalArgumentException("댓글작성 실패 : 유저 id를 찾을 수 없습니다.");
//		});//영속화 완료
//
//		Board1 board1 = boardRepository1.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//			return new IllegalArgumentException("댓글작성 실패 : 게시글 id를 찾을 수 없습니다.");
//		});//영속화 완료
		
//		Reply1 reply = Reply1.builder()
//				.user1(user1)
//				.board1(board1)
//				.content(replySaveRequestDto.getContent())
//				.build();
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
		System.out.println("boardservice" + result);
	}
	public void replyDelete(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
}
