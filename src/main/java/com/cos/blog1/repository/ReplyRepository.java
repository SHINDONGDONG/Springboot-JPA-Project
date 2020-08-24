package com.cos.blog1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog1.model.Reply1;


public interface ReplyRepository extends JpaRepository<Reply1, Integer> {

	@Modifying
	@Query(value="INSERT INTO reply1(userId,boardId,content,createDate) values(?1,?2,?3,now())",nativeQuery = true)
	int mSave(int userId,int boardId,String content); //업데이트 된 행의 갯수를 리턴해준다.
}
