package com.cos.blog1.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog1.config.auth.PrincipalDetailService;
//빈등록 스프링 컨테이너에서 객체를 관리할 수 있는것

@Configuration//빈등록 IoC관리
@EnableWebSecurity//리퀘스트 ->컨테이너 주소요청을 하는데 여기서 필터링을 해야하는데 본 시큐리티 어노테이션으로 가능
//시큐리티 필터가 등록된다. 그 설정을 본 config에서 설정한다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소로 접근하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //Ioc가됨
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	//시큐리티가 대신 로그인해주는데 password를 가로채는데 해당 password가 뭘로 해쉬가되어서 회원가입이 되었는지 알아야 같은해쉬로 암호화해서 
	//db에있는 해쉬랑 비교를 할 수 있다
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //scrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeRequests()//요청에대한 인가가 들어오면 
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //이하의 주소로 들어오면 
				.permitAll()
				.anyRequest()//이게아닌 다른 모든요청은
				.authenticated()//인증이 되어야해 
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당주소로 요청오는 로그인을 가로챈다(대신로그인해줌).
				.defaultSuccessUrl("/");
	}




}

