package com.swj.msgr.member.exception;

import com.swj.msgr.commons.exception.CommonException;
import com.swj.msgr.commons.model.ErrorCode;

public class RegistFailedException extends CommonException {

	private static final long serialVersionUID = -379182628657794384L;

	@Override
	public ErrorCode getExceptionCode() {
		return ErrorCode.FAIL_REGISTER;
	}

}
