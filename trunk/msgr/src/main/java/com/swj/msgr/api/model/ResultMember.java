package com.swj.msgr.api.model;

import lombok.Data;

import com.swj.msgr.member.model.Member;

@Data
public class ResultMember {
	Member member;
	boolean isfriend;
	boolean ischat;
}
