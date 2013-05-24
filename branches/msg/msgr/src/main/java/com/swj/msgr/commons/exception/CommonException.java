package com.swj.msgr.commons.exception;

import java.util.Map;

import com.swj.msgr.commons.model.ErrorCode;

public abstract class CommonException extends RuntimeException {
	
	Map<String,?> errorParamMap= null;

	public CommonException( ) {
		super();
	}
	
	public CommonException( Map<String,?> errorParamMap ) {
		super();
		this.errorParamMap = errorParamMap;
	}

	private static final long serialVersionUID = 3100255079897733636L;

	public abstract ErrorCode getExceptionCode();

	public Map<String, ?> getErrorParamMap() {
		return this.errorParamMap;
	}

}
