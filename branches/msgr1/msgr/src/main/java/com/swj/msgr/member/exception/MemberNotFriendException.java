package com.swj.msgr.member.exception;

import com.swj.msgr.commons.exception.CommonException;
import com.swj.msgr.commons.model.ErrorCode;

public class MemberNotFriendException extends CommonException {

	private static final long serialVersionUID = 1474091445125136955L;

	@Override
	public ErrorCode getExceptionCode() {
		return ErrorCode.FAIL_MEMBER_NOT_FRIEND;
	}

}
