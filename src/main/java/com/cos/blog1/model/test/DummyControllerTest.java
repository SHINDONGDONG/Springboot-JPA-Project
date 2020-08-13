package com.cos.blog1.model.test;

import java.util.List;
import java.util.function.Supplier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog1.model.RoleType;
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
		
		user1.setRole(RoleType.USER1);
		userRepository1.save(user1);
		
		return "회원가입이 완료 되었습니다";
	}
	
	//{id}주소로 파라메터를 받을 수 있다
	//http://localhost:8000/blog1/dummy/user/{3}
	@GetMapping("/dummy/user/{id}")
	public User1 detail(@PathVariable int id) {
		//user/4를 찾으면 내가 데이터베이스에서 못찾으면 null이지.. 리턴하면 null이 리턴되잖아 프로그램에 문제가 있음..... 그러므로 옵셔널로 너의 user객체를 감싸서 가져올테니 null인지 아닌지 판단해서 리턴하셈
		//1- .get
		//2- .orElseGet(new Supplier<User1>(new Supplier<User1>() {
		//@Override
		//public User1 get() {
			//return new User1();
		//}
		//**선호함 3- .
		User1 user1 = userRepository1.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당유저는 없습니다. id : " + id);
			}
		});
 	//람다식
/*
 * User1 user1 = userRepository1.findById(id).orElseThrow(()->{ return new
 * IllegalArgumentException("해당유저는 없습니다. id : " + id); });
 */
		
		//요청 : 웹브라우저
		//user객체 = 자바 오브젝트
		//변환 ( 웹브라우저가 이해할 수 있는 언어) ->JSON
		//스프링부트 ->messageConverter 응답시에ㅔ 자동작동
		//자바 오브젝트를 리턴하게 되면 Messageconverter가 JackSon 라이브러리를 호출해서 user객체를 json으로 변환하여 반환해준다
		return user1;
	}
	
	//http://localhost:8000/blog1/dummy/user
	@GetMapping("/dummy/users")
	public List<User1> list() {
		return userRepository1.findAll();
	}
	
	//http://localhost:8000/blog1/dummy/user/page
	//한페이지당 2건의 데이터를 받기  sort : id , 최신순으로 들고오기.
	@GetMapping("/dummy/user")
	public List<User1> pageList(@PageableDefault(size = 2,sort="id",direction = Sort.Direction.DESC)Pageable pageable){
		Page<User1> pagingUser=  userRepository1.findAll(pageable);
		List<User1> users = pagingUser.getContent();
		return users;
	}
	
	@PutMapping("/dummy/user/{id}")
	@Transactional
	public User1 updateUser(@PathVariable int id,@RequestBody User1 requestUser1) {//json데이터를 받기 위해선 RequestBody 어노테이션 있어야함
			//json데이터 요청 ->java object (message converter의 jackson라이브러리가 변환해서 받아줌)
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser1.getPassword());
		System.out.println("email : "+requestUser1.getEmail());
		
		User1 user1 = userRepository1.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user1.setPassword(requestUser1.getPassword());
		user1.setEmail(requestUser1.getEmail());

		//userRepository1.save(user1);
		return user1;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository1.deleteById(id);
		}catch(Exception e){
			return "삭제를 실패 하였습니다."+e.getMessage();
		}
		return "삭제가 완료 되었습니다. : " + id;
	}
	
}
