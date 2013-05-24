package com.swj.msgr.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swj.msgr.member.model.Friend;

public interface FriendDao {
	
	List<Friend> getFriends (@Param(value="mid") long mid, @Param(value="start") long start, @Param(value="cnt") int cnt);
	
	Friend getFriend (@Param(value="mid") long mid, @Param(value="friend_mid") long friend_mid);
	
	int addFriend (@Param(value="mid") long mid, @Param(value="friend_mid") long friend_mid);
	
	int deleteFriend (@Param(value="mid") long mid, @Param(value="friend_mid") long friend_mid);

}
