package com.swj.msgr.commons.exception;

import com.swj.msgr.commons.model.ErrorCode;

public class LoginRequiredException extends CommonException {

	private static final long serialVersionUID = -5512836974890723719L;

	@Override
	public ErrorCode getExceptionCode() {
		return ErrorCode.FAIL_LOGIN_REQUIRED;
	}

}
