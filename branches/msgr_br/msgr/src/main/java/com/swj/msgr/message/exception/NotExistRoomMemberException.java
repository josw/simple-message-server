package com.swj.msgr.message.exception;

import com.swj.msgr.commons.exception.CommonException;
import com.swj.msgr.commons.model.ErrorCode;

public class NotExistRoomMemberException extends CommonException {

	private static final long serialVersionUID = -1355679017068375780L;

	@Override
	public ErrorCode getExceptionCode() {
		return ErrorCode.FAIL_MEMBER_NOT_IN_ROOM;
	}

}
