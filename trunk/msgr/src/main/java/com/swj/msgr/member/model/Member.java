package com.swj.msgr.member.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value={"uuid"})
public class Member implements Serializable{

	private static final long serialVersionUID = 534290481255669098L;
	
	private long mid;
	private String uuid;
	private int	aid;
	private String email;
	private String nickname;
	private GenderCd gender;
	private int byear;
	private Float lat;
	private Float lng;
	private Float distance;
	private String status;
	private String comment;
	private Date crt_dt;
	private Date upd_dt;
}
