package com.swj.msgr.commons.model;

import com.swj.msgr.commons.exception.CommonException;

import lombok.Data;

@Data
public class ResultJsonContainer {
	
	private ErrorCode code;
	
	private Object value;
	
	public ResultJsonContainer(Object value) {
		this();
		this.value = value;
	}
	
	public ResultJsonContainer(CommonException ex) {
		this.code = ex.getExceptionCode();
	}
	
	public ResultJsonContainer() {
		this( ErrorCode.OK);
	}
	
	public ResultJsonContainer(ErrorCode code) {
		this.code= code;
	}

}
