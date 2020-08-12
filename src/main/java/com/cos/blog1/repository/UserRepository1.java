package com.cos.blog1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog1.model.User1;


//DAO 로 생각하면됨.
//자동으로 BEAN에 등록된다.
//@repository 생략가능
public interface UserRepository1  extends JpaRepository<User1, Integer>{ //이 테이블을 관리하는 것은 user1이고 프라이머리키는 integer이다 라는
//jparepository가 가지고 있는 메소드를 활용하여 insert,select,delete,update를 한다.
	
}
