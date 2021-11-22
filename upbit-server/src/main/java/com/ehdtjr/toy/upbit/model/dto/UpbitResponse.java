package com.ehdtjr.toy.upbit.model.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * (this -> 업비트) 요청에 대한 응답 
 */
@Data
public class UpbitResponse {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	//
	// 전체 계좌 조회
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
	
	
	//
	// 주문 가능 정보
	/** 매수 수수료 비율 */
	@JsonProperty("bid_fee")
	private String bidFee;
	
	/** 매도 수수료 비율 */
	@JsonProperty("ask_fee")
	private String askFee;
	
	/** 마켓에 대한 정보 */
	@JsonProperty("market")
	private UpbitMarket market;
	
	/** 매수 시 사용하는 화폐의 계좌 상태 */
	@JsonProperty("bid_account")
	private UpbitAccountStatus bidAccount;
	
	/** 매도 시 사용하는 화폐의 계좌 상태 */
	@JsonProperty("ask_account")
	private UpbitAccountStatus askAccount;
	
	
	//
	// 개별 주문 조회
	/** 주문의 고유 아이디 */
	@JsonProperty("uuid")
	private String uuid;
	
	/** 주문 종류 */
	@JsonProperty("side")
	private String side;
	
	/** 주문 방식 */
	@JsonProperty("ord_type")
	private String ordType;
	
	/** 주문 당시 화폐 가격 */
	@JsonProperty("price")
	private String price;
	
	/** 주문 상태 */
	@JsonProperty("state")
	private String state;
	
//	TODO market	마켓의 유일키	String
	
	/** 주문 생성 시간 */
	@JsonProperty("created_at")
	private String createdAt;
	
	/** 사용자가 입력한 주문 양 */
	@JsonProperty("volume")
	private String volume;
	
	/** 체결 후 남은 주문 양 */
	@JsonProperty("remaining_volume")
	private String remainingVolume;
	
	/** 수수료로 예약된 비용 */
	@JsonProperty("reserved_fee")
	private String reservedFee;
	
	/** 남은 수수료 */
	@JsonProperty("remaining_fee")
	private String remainingFee;
	
	/** 사용된 수수료 */
	@JsonProperty("paid_fee")
	private String paidFee;
	
//	locked	거래에 사용중인 비용	NumberString
	
	/** 체결된 양 */
	@JsonProperty("executed_volume")
	private String executedVolume;
	
	/** 해당 주문에 걸린 체결 수 */
	@JsonProperty("trade_count")
	private Integer tradeCount;
	
	/** 체결 */
	@JsonProperty("trades")
	private List<UpbitTrade> trades;

	
	//
	// 주문 리스트 조회
//	uuid	주문의 고유 아이디	String
//	side	주문 종류	String
//	ord_type	주문 방식	String
//	price	주문 당시 화폐 가격	NumberString
//	state	주문 상태	String
//	TODO market	마켓의 유일키	String
//	created_at	주문 생성 시간	DateString
//	volume	사용자가 입력한 주문 양	NumberString
//	remaining_volume	체결 후 남은 주문 양	NumberString
//	reserved_fee	수수료로 예약된 비용	NumberString
//	remaining_fee	남은 수수료	NumberString
//	paid_fee	사용된 수수료	NumberString
//	locked	거래에 사용중인 비용	NumberString
//	executed_volume	체결된 양	NumberString
//	trade_count	해당 주문에 걸린 체결 수	Integer

	
	//
	// 주문 취소 접수
//	uuid	주문의 고유 아이디	String
//	side	주문 종류	String
//	ord_type	주문 방식	String
//	price	주문 당시 화폐 가격	NumberString
//	state	주문 상태	String
//	TODO market	마켓의 유일키	String
//	created_at	주문 생성 시간	String
//	volume	사용자가 입력한 주문 양	NumberString
//	remaining_volume	체결 후 남은 주문 양	NumberString
//	reserved_fee	수수료로 예약된 비용	NumberString
//	remaining_fee	남은 수수료	NumberString
//	paid_fee	사용된 수수료	NumberString
//	locked	거래에 사용중인 비용	NumberString
//	executed_volume	체결된 양	NumberString
//	trade_count	해당 주문에 걸린 체결 수	Integer
	
	
	//
	// 주문하기
//	uuid	주문의 고유 아이디	String
//	side	주문 종류	String
//	ord_type	주문 방식	String
//	price	주문 당시 화폐 가격	NumberString
	/** 체결 가격의 평균가 */
	@JsonProperty("avg_price")
	private String avgPrice;
//	state	주문 상태	String
//	TODO market	마켓의 유일키	String
//	created_at	주문 생성 시간	String
//	volume	사용자가 입력한 주문 양	NumberString
//	remaining_volume	체결 후 남은 주문 양	NumberString
//	reserved_fee	수수료로 예약된 비용	NumberString
//	remaining_fee	남은 수수료	NumberString
//	paid_fee	사용된 수수료	NumberString
//	locked	거래에 사용중인 비용	NumberString
//	executed_volume	체결된 양	NumberString
//	trade_count	해당 주문에 걸린 체결 수	Integer
	
}
