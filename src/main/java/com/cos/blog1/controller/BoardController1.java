package com.cos.blog1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController1 {

	@GetMapping({"/",""})
	public String index() {
//		 prefix: /WEB-INF/views/
//	     suffix: .jsp
//		우리가 설정 해놓았던 yml파일이 return해줄때 prefix와 suffix를 함께 붙여준다.
		//결국 리턴값은 /WEB-INF/views/index.jsp 인 것이다. 
		return "index";
		
		
	}
}
