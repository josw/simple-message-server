package com.swj.msgr.message.service;

import java.util.List;

import com.swj.msgr.member.model.Member;
import com.swj.msgr.message.model.Message;

public interface MessageService {
	
	Message getMessage(long msid);

	Message setMessage(Member member, long rid, String msg);

	List<Message> listMessage(long rid, Long msid, Integer cnt);

}
