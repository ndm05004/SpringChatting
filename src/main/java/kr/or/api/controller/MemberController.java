package kr.or.api.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.MembershipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.api.service.IMemberService;
import kr.or.api.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@Inject
	IMemberService service;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "pages/ddit_signin";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		
		return "pages/ddit_signin";
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		

		session.removeAttribute("memberVO");
		session.invalidate();
		
		return "pages/ddit_signin";
	}
	
	
	@RequestMapping(value = "/pages/sign-up", method = RequestMethod.GET)
	public String sigup() {
		
		return "pages/ddit_signup";
	}
	
	@RequestMapping(value = "/singin.do", method = RequestMethod.POST)
	public String sigUpCheck(MemberVO memberVO, Model model) {
		
		log.info(memberVO.getMemId());
		log.info(memberVO.getMemPw());
		log.info(memberVO.getMemPhone());
		log.info(memberVO.getMemName());
		log.info(memberVO.getMemEmail());
		log.info(memberVO.getMemGender());
		log.info(memberVO.getMemAgree());
		
		if(StringUtils.isEmpty(memberVO.getMemId()) || 
				StringUtils.isEmpty(memberVO.getMemPw()) ||
				StringUtils.isEmpty(memberVO.getMemPhone()) ||
				StringUtils.isEmpty(memberVO.getMemName()) ||
				StringUtils.isEmpty(memberVO.getMemEmail()) ||
				StringUtils.isEmpty(memberVO.getMemGender()) ||
				StringUtils.isEmpty(memberVO.getMemAgree())) {
			model.addAttribute("flag","missing");
			return "pages/sign-up";
		}
			
		
		int check = service.memSignup(memberVO);
		
		if(check == 1) {
			model.addAttribute("flag","success");
			return "pages/ddit_signin";
		}else {
			model.addAttribute("flag","error");
			return "pages/ddit_signup";
		}
		
	}
	
	@RequestMapping(value="/pages/list.do", method =RequestMethod.POST)
	public String signInCheck(MemberVO memberVO, Model model, HttpSession session) {
		
		
		log.info(memberVO.getMemId());
		log.info(memberVO.getMemPw());
		
		int check = service.memLogin(memberVO);

		if(check==1) {
			session.setAttribute("memberVO", memberVO);
			return "redirect: /chat/chatList.do";
		}else {
			model.addAttribute("flag","no");
			return "pages/ddit_signin";
			
		}
		
	}
	

	
}
