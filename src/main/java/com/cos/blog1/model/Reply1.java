package com.cos.blog1.model;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 200)
	private String content;
	
	@ManyToOne //하나의 게시글에 여러개의 댓글이 존재할 수 있다.
	@JoinColumn(name = "boardId")
	private Board1 board1;
	
	@ManyToOne //한명의 유저는 여러개의 답변을 달 수 있다.
	@JoinColumn(name = "userId")
	private User1 user1;
	
	
	@CreationTimestamp
	private Timestamp createDate; //답변을 작성한 시간.
}
