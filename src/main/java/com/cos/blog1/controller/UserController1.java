package com.cos.blog1.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog1.model.KakaoProfile;
import com.cos.blog1.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**허용로 사용함
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는 /js/** /css/** /image/** 허용

@Controller
public class UserController1 {

	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallBack(String code) {
				RestTemplate rt = new RestTemplate();
				//Httpheader 오브젝트 생성
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
				//HttpBody오브젝트 생성
				MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
				params.add("grant_type","authorization_code");
				params.add("client_id","139427e5ebc4bbbdaf4f00c2a8696b7b");
				params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
				params.add("code",code);
				
				//Httpheader와 body를 하나의 오브젝트에 담기.
				HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
						new HttpEntity<>(params,headers);
				//Http 요청하기 -post방식으로 그리고 response변수의 응답받음.
				ResponseEntity<String> response =rt.exchange(
						"https://kauth.kakao.com//oauth/token",
						HttpMethod.POST,
						kakaoTokenRequest,
						String.class
						);
				//Gson,Json Simple,ObjectMapper
				ObjectMapper objectMapper = new ObjectMapper();
				OAuthToken oauthToken = null;
				try {
					oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
				System.out.println(" 카카오 엑세스 토큰  : " + oauthToken.getAccess_token());
	
				RestTemplate rt2 = new RestTemplate();
				//Httpheader 오브젝트 생성
				HttpHeaders headers2 = new HttpHeaders();
				headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
				headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
				
				//Httpheader와 body를 하나의 오브젝트에 담기.
				HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
						new HttpEntity<>(headers2);
				//Http 요청하기 -post방식으로 그리고 response변수의 응답받음.
				ResponseEntity<String> response2 =rt2.exchange(
						"https://kapi.kakao.com/v2/user/me",
						HttpMethod.POST,
						kakaoProfileRequest2,
						String.class
						);				
				
				ObjectMapper objectMapper2 = new ObjectMapper();
				KakaoProfile kakaoProfile = null;
				try {
					kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				System.out.println("카카오 아이디(번호): " + kakaoProfile.getId());
				System.out.println("카카오 이메일 : " + kakaoProfile.getKakaoAccount().getEmail());
				return response2.getBody();
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
