package com.swj.msgr.member.model;

import java.util.Date;

import lombok.Data;

@Data
public class Friend {

	private long mid;
	private long friend_mid;
	private Date crt_dt;
	private Member friend;
}
