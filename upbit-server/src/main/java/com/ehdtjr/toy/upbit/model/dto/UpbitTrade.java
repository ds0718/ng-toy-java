package com.ehdtjr.toy.upbit.model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * (this -> 업비트) 요청에 대한 응답 > 채결
 */
@Data
public class UpbitTrade {
//	trades.market	마켓의 유일 키	String
//	trades.uuid	체결의 고유 아이디	String
//	trades.price	체결 가격	NumberString
//	trades.volume	체결 양	NumberString
//	trades.funds	체결된 총 가격	NumberString
//	trades.side	체결 종류	String
//	trades.created_at	체결 시각	DateString
	/** 마켓의 유일 키 */
	@JsonProperty("market")
	private String market;
	
	/** 체결의 고유 아이디 */
	@JsonProperty("uuid")
	private String uuid;

	/** 체결 가격 */
	@JsonProperty("price")
	private String price;

	/** 체결 양 */
	@JsonProperty("volume")
	private String volume;
	
	/** 체결된 총 가격 */
	@JsonProperty("funds")
	private String funds;
	
	/** 체결 종류 */
	@JsonProperty("side")
	private String side;
	
	/** 체결 시각 */
	@JsonProperty("created_at")
	private String createdAt;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
