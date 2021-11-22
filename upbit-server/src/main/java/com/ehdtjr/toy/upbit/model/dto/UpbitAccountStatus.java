package com.ehdtjr.toy.upbit.model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * (this -> 업비트) 요청에 대한 응답 > 매도/매수 시 사용하는 화폐의 계좌 상태
 */
@Data
public class UpbitAccountStatus {
	/** 화폐를 의미하는 영문 대문자 코드 */
	@JsonProperty("currency")
	private String currency;
	
	/** 주문가능 금액/수량 */
	@JsonProperty("balance")
	private String balance;
	
	/** 주문 중 묶여있는 금액/수량 */
	@JsonProperty("locked")
	private String locked;
	
	/** 매수평균가 */
	@JsonProperty("avg_buy_price")
	private String avgBuyPrice;
	
	/** 매수평균가 수정 여부 */
	@JsonProperty("avg_buy_price_modified")
	private Boolean avgBuyPriceModified;
	
	/** 평단가 기준 화폐 */
	@JsonProperty("unit_currency")
	private String unitCurrency;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
