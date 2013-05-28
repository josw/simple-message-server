package com.swj.msgr.message.service;

import java.util.List;

import com.swj.msgr.member.model.Member;
import com.swj.msgr.message.model.Room;
import com.swj.msgr.message.model.RoomMember;

public interface RoomService {
	Room setRoom(Member member, String descr);
	long setRoomMember(Member member, long rid, Member owner);
	List<RoomMember> getRoomMember(long rid, Member member);
	List<RoomMember>  getRoomMemberMember(long mid);
}
