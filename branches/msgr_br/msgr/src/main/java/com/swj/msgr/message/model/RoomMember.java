package com.swj.msgr.message.model;

import java.util.Date;

import lombok.Data;

@Data
public class RoomMember {
	public enum StatusCd {
		OK, OUT, BAN
	}
	private long rid;
	private long mid;
	private StatusCd status;
	private Date crt_dt;
	private Date upd_dt;
}
