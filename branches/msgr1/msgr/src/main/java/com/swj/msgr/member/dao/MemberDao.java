package com.swj.msgr.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swj.msgr.member.model.Member;

public interface MemberDao {

	Member getEmailMember(@Param(value = "email") String email);

	Member getNicknameMember(@Param(value = "nickname") String nickname);
	
	Member getUuidMember(@Param(value = "uuid") String uuid);
	
	Member getMember(@Param(value = "mid") long mid);
	
	int insertMember(Member member);

	List<Member> getMembers(@Param(value="start") long start, @Param(value="cnt") int cnt);
	
	List<Member> getMembersAround(@Param(value="lat") float lat, @Param(value="lng") float lng, @Param(value="distance") int distance,
			@Param(value="start") long start, @Param(value="cnt") int cnt);
	
	List<Member> getMembersIn(@Param(value="mids") List<Long> mids);

	int updateMemberUpdDt(@Param(value="mid")long mid);

}
