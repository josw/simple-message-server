package com.swj.msgr.message.model;

import java.util.Date;

import lombok.Data;

@Data
public class Room {
	public enum StatusCd {
		OK, CLOSED
	}
	public enum TypeCd {
		PUB, PRI, OTO
	}
	
	private long rid;
	private String descr;
	private StatusCd status;
	private TypeCd type;
	private long owner_mid;
	private int max_cnt;
	private Date crt_dt;
	private Date upd_dt;

}
