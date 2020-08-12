package com.cos.blog1.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //데이터 겟터셋터
@NoArgsConstructor //빈생성자
@AllArgsConstructor //전체 생성자
@Builder//빌더 패턴
public class Board1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터를 사용할때
	private String content; //섬머노트 라이브러리 디자인이 되어 들어감 (html태그가 들어감)
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne //Many(board) / one(User) -> 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name = "userid")
	private User1 user1; //데이터베이스는 오브젝트를 저장할 수 없다, 자바는 오브젝트를 저장할 수 있음!  
										//(orm을 사용하면 오브젝트를 저장할수있다) 
	
	
	
}



