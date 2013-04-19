package com.swj.msgr.message.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
@NoArgsConstructor
public class Message {
	
	private long msid;
	private @NonNull long rid;
	private @NonNull long mid;
	private @NonNull String message;
	private Date crt_dt;


}
