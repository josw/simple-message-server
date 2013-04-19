package com.swj.msgr.member.service;

import com.swj.msgr.api.model.Login;
import com.swj.msgr.member.model.Member;

public interface LoginService {
	Login getLogin(String token);
	String getToken(Member member);
}
