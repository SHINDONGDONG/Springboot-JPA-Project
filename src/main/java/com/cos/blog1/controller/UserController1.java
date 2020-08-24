package com.cos.blog1.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog1.model.KakaoProfile;
import com.cos.blog1.model.OAuthToken;
import com.cos.blog1.model.User1;
import com.cos.blog1.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**허용로 사용함
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/** /css/** /image/** 허용

@Controller
public class UserController1 {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/auth/kakao/callback")
	public  String kakaoCallback(String code) {
		
		//POST방식으로 key=value 데이터요청(카카오쪽으로)
		RestTemplate rt = new RestTemplate();
		
		//httpheader생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		//http body 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "139427e5ebc4bbbdaf4f00c2a8696b7b");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);

		//httpheader와 httpbody를 하나의 오브젝트에 담기 
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);
		
		//Gson,Json Simple ,ObjectMapper;
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//POST방식으로 key=value 데이터요청(카카오쪽으로)
				RestTemplate rt2 = new RestTemplate();
				
				//httpheader생성
				HttpHeaders headers2 = new HttpHeaders();
				headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
				headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				//httpheader와 httpbody를 하나의 오브젝트에 담기 
				HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
						new HttpEntity<>(headers2);
				
				ResponseEntity<String> response2 = rt2.exchange(
						"https://kapi.kakao.com/v2/user/me",
						HttpMethod.POST,
						kakaoProfileRequest2,
						String.class
						);
				
		
		System.out.println("카카오 엑세스 토큰 : "+ oauthToken.getAccess_token());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//User오브젝트 username , password, email 
		
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 email  : " + kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그 서버 username : " + kakaoProfile.getKakao_account().getEmail() +"_"+kakaoProfile.getId());
		System.out.println("블로그서버 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		//UUID란 ->중복되지 않는 어떤 특정한값을 만들어내는 알고리즘
		UUID garbagePassword = UUID.randomUUID();
		System.out.println("블로그 패스워드 : "+ cosKey);
		
		//카카오로 로그인하였을때 블로그에 회원가입되는 메소드
		User1 kakaoUser = User1.builder()
				.username(kakaoProfile.getKakao_account().getEmail() +"_"+kakaoProfile.getId())
				.email(kakaoProfile.getKakao_account().getEmail())
				.password(cosKey)
				.oauth("kakao")
				.build();
		//가입자 비가입자 체크해서 처리
		User1 originUser= userService.find(kakaoUser.getUsername());

		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다.");
			userService.save(kakaoUser); 
		}
		
		//로그인처리
		System.out.println("자동로그인 진행됨");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
		
	}

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}

	
}
