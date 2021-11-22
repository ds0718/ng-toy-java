package com.ehdtjr.toy.upbit.model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * (this -> 업비트) 요청에 대한 응답 > 마켓에 대한 정보 > 매도/매수 시 제약사항
 */
@Data
public class UpbitMarketConstraint {
	/** 화폐를 의미하는 영문 대문자 코드 */
	@JsonProperty("currency")
	private String currency;
	
	/** 주문금액 단위 */
	@JsonProperty("price_unit")
	private String priceUnit;
	
	/** 최소 매도/매수 금액 */
	@JsonProperty("min_total")
	private Double minTotal;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
