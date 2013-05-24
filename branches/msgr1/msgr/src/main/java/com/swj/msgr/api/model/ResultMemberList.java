package com.swj.msgr.api.model;

import java.util.List;

import lombok.Data;

import com.swj.msgr.member.model.Member;

@Data
public class ResultMemberList {
	private int page;
	private List<Member> members;
}
