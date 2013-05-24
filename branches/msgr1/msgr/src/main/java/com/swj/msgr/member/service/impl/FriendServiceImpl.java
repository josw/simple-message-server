package com.swj.msgr.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swj.msgr.member.dao.FriendDao;
import com.swj.msgr.member.dao.MemberDao;
import com.swj.msgr.member.model.Friend;
import com.swj.msgr.member.model.Member;
import com.swj.msgr.member.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {
	
	private static final int FRIENDS_CNT = 10;
	
	@Resource
	FriendDao friendDao;
	
	@Resource
	MemberDao memberDao;

	@Override
	public List<Friend> getFriends(Member member, Integer page) {
		return friendDao.getFriends(member.getMid(), page*FRIENDS_CNT, FRIENDS_CNT);
	}

	@Override
	public Friend getFriend(Member member, long friend_mid) {
		return friendDao.getFriend(member.getMid(), friend_mid);
	}

	@Override
	public boolean addFriend(Member member, long friend_mid) {
		if (friendDao.addFriend(member.getMid(), friend_mid) > 0)
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean deleteFriend(Member member, long friend_mid) {
		if (friendDao.deleteFriend(member.getMid(), friend_mid) > 0)
			return true;
		else 
			return false;
	}

	@Override
	public void setMember(List<Friend> friend) {
		
		List<Long> mids = new ArrayList<Long>();
		for (Friend fr : friend) {
			mids.add(fr.getFriend_mid());
		}
		
		Map<Long, Member> membersMap = new HashMap<Long, Member>();
		List<Member> members = memberDao.getMembersIn(mids);
		
		for (Member member : members) {
			membersMap.put(member.getMid(), member);
		}

		for (Friend fr : friend) {
			fr.setFriend(membersMap.get(fr.getFriend_mid()));
		}
	}



}
