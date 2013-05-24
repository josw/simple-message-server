package com.swj.msgr.commons.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6502304549409895887L;
	
	private Date crt_dt;
	private Date upd_dt;

	public DTO() {
		super();
	}

	public DTO(DTO dto) {
		super();
		this.crt_dt = dto.crt_dt;
		this.upd_dt = dto.upd_dt;
	}
	
	public static enum Status {
		OK, DEL, NOTICE
	};
}
