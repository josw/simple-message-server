package com.swj.msgr.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.swj.msgr.commons.model.ErrorCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RequestException extends CommonException {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode = ErrorCode.ERROR;

	@Override
	public ErrorCode getExceptionCode() {
		return this.errorCode;
	}
	
}
