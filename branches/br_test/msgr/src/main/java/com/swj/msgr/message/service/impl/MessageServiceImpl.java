package com.swj.msgr.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swj.msgr.member.model.Member;
import com.swj.msgr.message.dao.MessageDao;
import com.swj.msgr.message.model.Message;
import com.swj.msgr.message.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Resource
	MessageDao messageDao;

	@Override
	public Message getMessage(long msid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Message setMessage(Member member, long rid,  String msg) {
		Message message = Message.of(rid, member.getMid(), msg);
		messageDao.setMessage(message);
		return message;
	}

	@Override
	public List<Message> listMessage(long rid, Long msid, Integer cnt) {
		cnt = (cnt==null)?10:cnt;

		return messageDao.listMessage(rid, msid, cnt);
	}

}
