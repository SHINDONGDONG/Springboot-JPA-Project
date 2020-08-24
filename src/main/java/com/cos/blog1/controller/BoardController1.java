package com.cos.blog1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cos.blog1.config.auth.PrincipalDetail;
import com.cos.blog1.service.BoardService;

@Controller
public class BoardController1 {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.detail(id));
		
		return "board/detail";
	}
	
	@GetMapping({"/",""}) //컨트롤러에서 세션을 어떻게 찾나.?
	public String index(Model model,@PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {
//		 prefix: /WEB-INF/views/
//	     suffix: .jsp
//		우리가 설정 해놓았던 yml파일이 return해줄때 prefix와 suffix를 함께 붙여준다.
		//결국 리턴값은 /WEB-INF/views/index.jsp 인 것이다. 
		model.addAttribute("boards",boardService.list(pageable));
		return "index"; 
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/updateForm";
	}
	
	
}
