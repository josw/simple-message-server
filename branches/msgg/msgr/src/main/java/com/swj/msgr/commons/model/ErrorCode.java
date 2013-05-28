package com.swj.msgr.commons.model;

public enum ErrorCode {
	OK("000"),
	ERROR("-1"), 
	FAIL_REGISTER("200"),
	FAIL_LOGIN_REQUIRED("201"), 
	FAIL_MEMBER_NOT_EXIST("202"), 
	FAIL_MEMBER_NOT_IN_ROOM("203"), 
	FAIL_MEMBER_NOT_FRIEND("204"),
	FAIL_MEMBER_ALREADY_FRIEND("205"),
	FAIL_TARGET_MEMBER_IS_YOU("206"),
	ERROR_REQUEST_BINDING("204");

	public String code;

	ErrorCode(String code) {
		this.code = code;
	}
}
