package com.swj.msgr.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swj.msgr.member.model.Member;
import com.swj.msgr.message.dao.RoomDao;
import com.swj.msgr.message.model.Room;
import com.swj.msgr.message.model.Room.TypeCd;
import com.swj.msgr.message.model.RoomMember;
import com.swj.msgr.message.model.RoomMember.StatusCd;
import com.swj.msgr.message.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Resource
	RoomDao roomDao;
	

	@Override
	public Room setRoom(Member member, String descr) {
		
		Room room = new Room();
		room.setDescr(descr);
		room.setOwner_mid(member.getMid());
		room.setType(TypeCd.PUB);
		
		//TODO need handle return 
		roomDao.setRoom(room);
		
		roomDao.setRoomMember(room.getRid(), member.getMid());
		
		return room;
	}

	@Override
	public long setRoomMember(Member member, long rid, Member owner) {
		return roomDao.setRoomMember(rid, member.getMid());
	}

	@Override
	public List<RoomMember> getRoomMember(long rid, Member member) {
		
		Long mid = (member==null)?null:member.getMid();
		
		return roomDao.getRoomMember(rid, mid, StatusCd.OK.toString());
	}

	@Override
	public List<RoomMember> getRoomMemberMember(long mid) {
		return roomDao.getRoomMemberMember(mid, StatusCd.OK.toString());
	}

}
