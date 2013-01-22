package com.swj.msgr.config;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import com.swj.msgr.commons.model.ResultJsonContainer;


public class MsgrJsonMesageConverter extends MappingJacksonHttpMessageConverter {

	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
			super.writeInternal(new ResultJsonContainer(o), outputMessage);	
	}

}
