package com.swj.msgr.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.swj.msgr.commons.model.ErrorCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestException extends CommonException {

	private static final long serialVersionUID = 1L;

	@Override
	public ErrorCode getExceptionCode() {
		return ErrorCode.OK;
	}

}
