package com.ehdtjr.toy.upbit.model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 요청에 대한 파라미터
 */
@Slf4j
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // not null && not empty
public class LineParams {
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	@JsonProperty("message")
	private String message;
	
	public static LineParams of(String message) {
		LineParams to = new LineParams();
		to.setMessage(message);
		return to;
	}
}
