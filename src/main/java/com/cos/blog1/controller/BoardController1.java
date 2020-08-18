package com.cos.blog1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog1.config.auth.PrincipalDetail;
import com.cos.blog1.service.BoardService;

@Controller
public class BoardController1 {


	
	@GetMapping({"/",""}) //컨트롤러에서 세션을 어떻게 찾나.?
	public String index() {
//		 prefix: /WEB-INF/views/
//	     suffix: .jsp
//		우리가 설정 해놓았던 yml파일이 return해줄때 prefix와 suffix를 함께 붙여준다.
		//결국 리턴값은 /WEB-INF/views/index.jsp 인 것이다. 
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
