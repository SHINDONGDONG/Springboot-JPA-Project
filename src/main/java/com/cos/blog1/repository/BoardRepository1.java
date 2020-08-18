package com.cos.blog1.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog1.model.Board1;

public interface BoardRepository1  extends JpaRepository<Board1, Integer>{ //이 테이블을 관리하는 것은 user1이고 프라이머리키는 integer이다 라는

}
