package com.ehdtjr.toy.upbit.model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;

import com.ehdtjr.toy.upbit.config.TradeConfig;
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
public class UpbitParams {

	@JsonProperty("accessKey")
	private String accessKey;
	
	@JsonProperty("secretKey")
	private String secretKey;
	
	@JsonProperty("ratioUp")
	private Double ratioUp;
	
	@JsonProperty("ratioDown")
	private Double ratioDown;
	
	@JsonProperty("basePrice")
	private Double basePrice;
	
	@JsonProperty("unit")
	private Double unit;
	
	@JsonProperty("decimalPlaces")
	private Double decimalPlaces;
	
	//
	// 주문 가능 정보
	/** 마켓 ID, ex)KRW-MVL, KRW-SSX... */
	@JsonProperty("market")
	private String market;

	//
	// 개별 주문 조회
//	uuid	주문 UUID	String
//	identifier	조회용 사용자 지정 값	String

	
	//
	// 주문 리스트 조회
//	market	마켓 아이디	String
//	uuids	주문 UUID의 목록	Array
//	identifiers	주문 identifier의 목록	Array
//	state	주문 상태
//	- wait : 체결 대기 (default)
//	- watch : 예약주문 대기
//	- done : 전체 체결 완료
//	- cancel : 주문 취소	String
//	states	주문 상태의 목록
//
//	* 미체결 주문(wait, watch)과 완료 주문(done, cancel)은 혼합하여 조회하실 수 없습니다.	Array
//	page	페이지 수, default: 1	Number
//	limit	요청 개수, default: 100	Number
//	order_by	정렬 방식
//	- asc : 오름차순
//	- desc : 내림차순 (default)	String
	
	
	//
	// 주문 취소 접수
//	uuid	취소할 주문의 UUID	String
//	identifier	조회용 사용자 지정값	String
	
	
	//
	// 주문하기
//	market *	마켓 ID (필수)	String
//	side *	주문 종류 (필수)
//	- bid : 매수
//	- ask : 매도	String
//	volume *	주문량 (지정가, 시장가 매도 시 필수)	NumberString
//	price *	주문 가격. (지정가, 시장가 매수 시 필수)
//	ex) KRW-BTC 마켓에서 1BTC당 1,000 KRW로 거래할 경우, 값은 1000 이 된다.
//	ex) KRW-BTC 마켓에서 1BTC당 매도 1호가가 500 KRW 인 경우, 시장가 매수 시 값을 1000으로 세팅하면 2BTC가 매수된다.
//	(수수료가 존재하거나 매도 1호가의 수량에 따라 상이할 수 있음)	NumberString
//	ord_type *	주문 타입 (필수)
//	- limit : 지정가 주문
//	- price : 시장가 주문(매수)
//	- market : 시장가 주문(매도)	String
//	identifier	조회용 사용자 지정값 (선택)	String (Uniq 값 사용)

	@JsonProperty("side")
	private String side;
	@JsonProperty("volume")
	private String volume;
	@JsonProperty("price")
	private String price;
	@JsonProperty("ord_type")
	private String ordType;
	@JsonProperty("identifier")
	private String identifier;

	public static UpbitParams of(TradeConfig from) {
		UpbitParams to = new UpbitParams();
		BeanUtils.copyProperties(from, to);
		return to;
	}
	
	public String getTradeInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("market(").append(market).append(")");
		sb.append(", ratioUp(").append(ratioUp).append(")");
		sb.append(", ratioDown(").append(ratioDown).append(")");
		sb.append(", basePrice(").append(basePrice).append(")");
//		sb.append(", unit(").append(unit).append(")");
//		sb.append(", decimalPlaces(").append(decimalPlaces).append(")");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
