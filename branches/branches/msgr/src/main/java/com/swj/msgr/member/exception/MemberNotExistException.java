package com.swj.msgr.member.exception;

import com.swj.msgr.commons.exception.CommonException;
import com.swj.msgr.commons.model.ErrorCode;

public class MemberNotExistException extends CommonException {

	private static final long serialVersionUID = -355969690113723246L;

	@Override
	public ErrorCode getExceptionCode() {
		return ErrorCode.FAIL_MEMBER_NOT_EXIST;
	}

}