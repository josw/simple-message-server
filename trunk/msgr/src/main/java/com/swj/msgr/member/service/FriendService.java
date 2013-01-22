package com.swj.msgr.member.service;

import java.util.List;

import com.swj.msgr.member.model.Friend;
import com.swj.msgr.member.model.Member;

public interface FriendService {
	
	List<Friend> getFriends(Member member, Integer page); 
	
	Friend getFriend(Member member, long friend_mid);
	
	boolean addFriend(Member member, long friend_mid);
	
	boolean deleteFriend(Member member, long friend_mid);
	
	void setMember(List<Friend> friend);

}
