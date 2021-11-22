package com.ehdtjr.toy.upbit.model.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * (this -> 업비트) 요청에 대한 응답 > 마켓에 대한 정보
 */
@Data
public class UpbitMarket {
	/** 마켓의 유일 키 */
	@JsonProperty("id")
	private String id;
	
	/** 마켓 이름 */
	@JsonProperty("name")
	private String name;
	
	/** 지원 주문 방식 */
	@JsonProperty("order_types")
	private List<String> orderTypes;
	
	/** 지원 주문 종류 */
	@JsonProperty("order_sides")
	private List<String> orderSides;
	
	/** 매수 시 제약사항 */
	@JsonProperty("bid")
	private UpbitMarketConstraint bid;
	
	/** 매도 시 제약사항 */
	@JsonProperty("ask")
	private UpbitMarketConstraint ask;

	/** 최대 매도/매수 금액 */
	@JsonProperty("max_total")
	private String maxTotal;

	/** 마켓 운영 상태 */
	@JsonProperty("state")
	private String state;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
