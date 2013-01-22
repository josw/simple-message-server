package com.swj.msgr.member.service;

import java.util.List;

import com.swj.msgr.member.model.Member;

public interface MemberService {

	Member getEmailMember(String email);

	Member getNicknameMember(String nickname);
	
	Member getUuidMember(String uuid);
	
	Member getMember(long mid);
	
	List<Member> getMembersIn(List<Long> mids);

	long register(Member member);

	List<Member> getWaitList(Integer page);

	List<Member> getWaitListAround(Member member, Integer distance, Integer page);

	void updateMemberUpdDt(long mid);

}
