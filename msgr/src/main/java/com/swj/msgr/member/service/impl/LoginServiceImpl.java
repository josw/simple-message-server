package com.swj.msgr.member.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.swj.msgr.api.model.Login;
import com.swj.msgr.member.model.Member;
import com.swj.msgr.member.service.LoginService;
import com.swj.msgr.member.service.MemberService;
import com.swj.msgr.util.BCrypt;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	private final static BCrypt bcrypt = new BCrypt("Rkrh.dlTsp");
	
	@Resource
	MemberService memberService;

	@Override
	public Login getLogin(String token) {
		
		String[] parsed = null;
		Login login = new Login();
		/*
		 * 
		 * appId | logininfo
		 * 
		 */
		String[] stoken = token.split("\\|");
				
		try {
			parsed = bcrypt.decrypt(stoken[1]).split("\\|");
			
			logger.debug (parsed);
			
			if (!stoken[0].equals(parsed[1]))
				return login;
			
			Member member = memberService.getMember(Long.valueOf(parsed[1]));
			if (member == null)
				return login;

			login.setAid(Long.valueOf(parsed[0]));
			login.setMid(member.getMid());
			login.setLast_login_dt(member.getUpd_dt());
			login.setMember(member);
			
			
			
			if (member.getUpd_dt().getTime() < new Date().getTime() - 60*60 ) {
				memberService.updateMemberUpdDt(member.getMid());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return login;
	}

	@Override
	public String getToken(Member member) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(member.getAid())
		.append("|")
		.append(member.getMid())
		.append("|")
		.append(member.getUpd_dt());
		
		logger.debug (sb.toString());

		try {
			return member.getMid() + "|" +bcrypt.encrypt(sb.toString());
		} catch (Exception e) {
			logger.error(">>"+ e.getMessage());
			e.printStackTrace();
			
			return "";
		} 
		
	}

}
