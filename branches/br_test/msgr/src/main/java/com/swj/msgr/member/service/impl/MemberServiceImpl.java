package com.swj.msgr.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swj.msgr.member.dao.MemberDao;
import com.swj.msgr.member.model.Member;
import com.swj.msgr.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(MemberServiceImpl.class);

	private static final int WAIT_MEMBER_CNT = 10;
	
	private static final int DISTANCE = 10;

	
	@Resource
	MemberDao memberDao;

	@Override
	public Member getEmailMember(String email) {
		return memberDao.getEmailMember(email);
	}

	@Override
	public Member getNicknameMember(String nickname) {
		return memberDao.getNicknameMember(nickname);
	}
	
	@Override
	public Member getUuidMember(String uuid) {
		return memberDao.getUuidMember(uuid);
	}

	@Override
	public Member getMember(long mid) {
		return memberDao.getMember(mid);
	}

	@Override
	@Transactional
	public long register(Member member) {
		memberDao.insertMember(member);
		return member.getMid();
	}

	@Override
	public List<Member> getWaitList(Integer page) {
		return memberDao.getMembers(page*WAIT_MEMBER_CNT, WAIT_MEMBER_CNT);
	}
	
	@Override
	public List<Member> getWaitListAround(Member member, Integer distance, Integer page) {
		
		Float lat = member.getLat();
		Float lng = member.getLng();
		
		if (lat == null || lng == null)
			return getWaitList(page);
		
		distance = (distance == null)?DISTANCE:distance;
		
		return memberDao.getMembersAround(lat, lng, distance, page*WAIT_MEMBER_CNT, WAIT_MEMBER_CNT);
	}

	@Override
	public List<Member> getMembersIn(List<Long> mids) {
		return memberDao.getMembersIn(mids);
	}

	@Override
	public void updateMemberUpdDt(long mid) {
		memberDao.updateMemberUpdDt(mid);
	}


}
