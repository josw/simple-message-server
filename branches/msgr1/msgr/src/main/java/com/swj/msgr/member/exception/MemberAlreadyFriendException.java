package com.swj.msgr.member.exception;

import com.swj.msgr.commons.exception.CommonException;
import com.swj.msgr.commons.model.ErrorCode;

public class MemberAlreadyFriendException extends CommonException {

	private static final long serialVersionUID = 7221725773591379858L;

	@Override
	public ErrorCode getExceptionCode() {
		return ErrorCode.FAIL_MEMBER_ALREADY_FRIEND;
	}

}
