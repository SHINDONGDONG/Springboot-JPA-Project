package com.cos.blog1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog1.model.User1;

import lombok.Data;
import lombok.Getter;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행해서 완료가되면 UserDetail타입의 오브젝트를 
//스프링 시큐리티의 고유한 세션저장소에 저장을해준다.
@Data
public class PrincipalDetail implements UserDetails{
	private User1 user1;//콤포지션(객체를 품고있는것 )

	public PrincipalDetail(User1 user1) {
		this.user1 = user1;
	}
	
	@Override
	public String getPassword() {
		return user1.getPassword();
	}

	@Override
	public String getUsername() {
		return user1.getUsername();
	}

	//계정이 만료되지 않았는지 리턴함.
	@Override
	public boolean isAccountNonExpired() {
		return true;//true이면 만료되지 않음
	}

	//계정이 잠겨있는지 아닌지 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true; //true일경우 안잠겨있음
	}

	//비밀번호가 만료되었는지 아닌지
	@Override
	public boolean isCredentialsNonExpired() {
		return true;//true일경우 만료가안됨
	}

	//계정 활성화가 되어있는지 아닌지
	@Override
	public boolean isEnabled() {
		return true; //true이면 활성화되어있음
	}
	
	//계정의 권한을 리턴한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{ return "ROLE_"+user1.getRole();});
		
		return collectors;
	}
	
	
}
