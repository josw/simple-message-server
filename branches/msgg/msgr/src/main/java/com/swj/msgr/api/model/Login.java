package com.swj.msgr.api.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.swj.msgr.member.model.Member;

import lombok.Data;

@Data
@JsonIgnoreProperties(value={"member"})
public class Login {
	
	private long mid;
	private long aid;
	private Date last_login_dt;
	private Member member;

}
