package com.swj.msgr.member.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ResultLogin {
	private @NonNull Member member;
	private String rtoken; 
	
}
