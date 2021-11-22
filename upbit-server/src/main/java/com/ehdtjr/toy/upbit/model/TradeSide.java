package com.ehdtjr.toy.upbit.model;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * 매수/매도 유형
 *     - values :
 *         {@linkplain TradeSide#BID}
 *         {@linkplain TradeSide#ASK}
 * </pre>
 */
public enum TradeSide {
	
	/** 매수/매도 유형 : BID(매수) */
	BID("bid", "매수")
	
	/** 매수/매도 유형 : ASK(매도) */
	, ASK("ask", "매도")
	;

	
	private String code;
	private String viewName;
	
	private TradeSide(String code, String viewName) {
		this.code = code;
		this.viewName = viewName;
	}

	/**
	 * {@linkplain TradeSide#code}
	 * @return {@link TradeSide#code}
	 */
	public String getCode() {
		return this.code;
	}

	public String getViewName() {
		return this.viewName;
	}
	
	public static boolean isValidCode(String code) {
		try {
			getValueOf(code);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * {@link TradeSide} 에서 code 에 해당하는 {@link TradeSide} 를 반환
	 * @param {@link TradeSide#code}
	 * @return
	 * @throws IllegalArgumentException  if the code does not contain {@link TradeSide}.
	 */
	public static TradeSide getValueOf(String code) throws IllegalArgumentException {
		for (TradeSide v : TradeSide.values()) {
			if (StringUtils.equals(v.code, code)) {
				return v;
			}
		}
		throw new IllegalArgumentException("no matching constant for [" + code + "]");
	}

	/**
	 * {@link TradeSide} 에서 code 에 해당하는 viewName 을 반환
	 * @param {@link TradeSide#code}
	 * @return
	 * @exception  IllegalArgumentException  if the code does not contain {@link TradeSide}.
	 */
	public static String getViewNameOf(String code) throws IllegalArgumentException {
		return getValueOf(code).getViewName();
	}
	
}
