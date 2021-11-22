package com.ehdtjr.toy.upbit.model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * (this -> 업비트) 요청에 대한 응답 
 */
@Data
public class UpbitResponse2 {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	//
	// 시세 Ticker 조회 > 현재가 정보
	/** 최근 거래 일자(UTC) */
	@JsonProperty("market")
	private String market;
	
	/** 최근 거래 일자(UTC) */
	@JsonProperty("trade_date")
	private String tradeDate;
	/** 최근 거래 시각(UTC) */
	@JsonProperty("trade_time")
	private String tradeTime;
	
	/** 최근 거래 일자(KST) */
	@JsonProperty("trade_date_kst")
	private String tradeDateKst;
	/** 최근 거래 시각(KST) */
	@JsonProperty("trade_time_kst")
	private String tradeTimeKst;
	
	/** 시가 */
	@JsonProperty("opening_price")
	private Double openingPrice;
	/** 고가 */
	@JsonProperty("high_price")
	private Double highPrice;
	/** 저가 */
	@JsonProperty("low_price")
	private Double lowPrice;
	/** 종가 */
	@JsonProperty("trade_price")
	private Double tradePrice;
	/** 전일 종가 */
	@JsonProperty("prev_closing_price")
	private Double prevClosingPrice;
//	change	EVEN : 보합
//	RISE : 상승
//	FALL : 하락	String
//	change_price	변화액의 절대값	Double
//	change_rate	변화율의 절대값	Double
//	signed_change_price	부호가 있는 변화액	Double
//	signed_change_rate	부호가 있는 변화율	Double
//	trade_volume	가장 최근 거래량	Double
//	acc_trade_price	누적 거래대금(UTC 0시 기준)	Double
//	acc_trade_price_24h	24시간 누적 거래대금	Double
//	acc_trade_volume	누적 거래량(UTC 0시 기준)	Double
//	acc_trade_volume_24h	24시간 누적 거래량	Double
//	highest_52_week_price	52주 신고가	Double
//	highest_52_week_date	52주 신고가 달성일	String
//	lowest_52_week_price	52주 신저가	Double
//	lowest_52_week_date	52주 신저가 달성일	String
//	timestamp	타임스탬프	Long
}
