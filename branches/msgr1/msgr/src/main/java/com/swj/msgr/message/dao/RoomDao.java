package com.swj.msgr.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swj.msgr.message.model.Room;
import com.swj.msgr.message.model.RoomMember;

public interface RoomDao {
	
	List<RoomMember> getRoomMember(@Param(value="rid") long rid, @Param(value="mid") Long mid, @Param(value="status") String status);
	
	int setRoomMember(@Param(value="rid") long rid, @Param(value="mid") long mid);
	
	int setRoom(Room room);

	List<RoomMember> getRoomMemberMember(@Param(value="mid") long mid, @Param(value="status") String string);
	
}
