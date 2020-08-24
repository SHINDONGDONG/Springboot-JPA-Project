package com.cos.blog1.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.ws.BindingType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //User 클래스가 Mysql 테이블이 생성이된다. 테이블을 생성하기 위하여 Entity 어노테이션을 붙여준다.
@Data //데이터 겟터셋터
@NoArgsConstructor //빈생성자
@AllArgsConstructor //전체 생성자
@Builder
@DynamicInsert //인서트시 널인  값을 제외시켜준다.
public class User1 {

	@Id//primary key 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다는 뜻.
	private int id; //오라클 : 시퀀스 , mysql : auto_increment
	
	@Column(nullable = false,length = 100,unique = true)
	private String username; //아이디 
	
	@Column(nullable = false,length = 100) //해쉬 비밀번호 암호화
	private String password; //패스워드
	
	@Column(nullable = false,length = 50)
	private String email; //이메일
	
//	@ColumnDefault("'user1'") // '' 조
	@Enumerated(EnumType.STRING) //Enum 타입이 String인걸 알려주는 어노테이션을 붙여줘야함.
	private RoleType role; //Enum 으로 타입을 선언하여 실수못하게 막아준다.
	
	private String oauth; //kakao,google
	
	@CreationTimestamp //시간이 자동으로 입력된다.
	private Timestamp createDate; //sql이 가지고있는 Timestamp를 사용
	
}
