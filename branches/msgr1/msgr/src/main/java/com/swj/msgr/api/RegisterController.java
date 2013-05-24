package com.swj.msgr.api;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swj.msgr.member.exception.RegistFailedException;
import com.swj.msgr.member.model.Member;
import com.swj.msgr.member.model.ResultLogin;
import com.swj.msgr.member.service.LoginService;
import com.swj.msgr.member.service.MemberService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private final static Logger logger = Logger.getLogger(RegisterController.class);
	
	@Resource
	MemberService memberService;
	
	@Resource
	LoginService loginService;
	
	@RequestMapping("")
	@ResponseBody
	public ResultLogin register(
			@ModelAttribute Member member, 
			@RequestParam("nickname") String nick
			) {
		
		logger.debug(">>>>" + member.getNickname());
		logger.debug(">>>>" + nick);
		
		long mid = (long) memberService.register(member);
		
		Member regMember = memberService.getMember(mid);

		if (regMember == null)
			throw new RegistFailedException();
		
		
		ResultLogin lo = ResultLogin.of(regMember);
		
		String rtoken = loginService.getToken(regMember);
		lo.setRtoken(rtoken);
		
		
		logger.debug (lo);
		
		return  lo;
	}
	
	@RequestMapping("/dup/email")
	@ResponseBody
	public boolean dupEmail(
			@RequestParam("email") String email) {
		return (memberService.getEmailMember(email) != null);

	}
	
	@RequestMapping("/dup/nickname")
	@ResponseBody
	public boolean dupNickname(
			@RequestParam("nickname") String nickname) {
		return (memberService.getNicknameMember(nickname) != null);

	}
	
	
	
	
}
