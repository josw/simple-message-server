package com.swj.msgr.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swj.msgr.message.model.Message;


public interface MessageDao {
	int setMessage(Message message);
	List<Message> listMessage(@Param(value="rid") long rid, @Param(value="msid") Long msid, @Param(value="cnt") int cnt);
	Message getMessage(@Param(value="msid") long msid);
}
