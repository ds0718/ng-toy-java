package com.ehdtjr.toy.upbit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehdtjr.toy.upbit.ext.LineClient;
import com.ehdtjr.toy.upbit.ext.UpbitClient;
import com.ehdtjr.toy.upbit.model.TradeSide;
import com.ehdtjr.toy.upbit.model.dto.LineParams;
import com.ehdtjr.toy.upbit.model.dto.UpbitParams;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse2;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradeService {
	
	@Autowired
	private UpbitClient upClient;
	
	@Autowired
	private LineClient lineClient;
	
	public void trade(UpbitParams params) {
		try {
			doTrade(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// bid :: 매수
	// ask :: 매도
	private void doTrade(UpbitParams params) throws Exception {
		UpbitResponse res = upClient.getOrdersChance(params);
		double bidLocked = Double.parseDouble(res.getBidAccount().getLocked());
		double askLocked = Double.parseDouble(res.getAskAccount().getLocked());
		
		if (bidLocked > 0 || askLocked > 0) {
			log.info("거래 중...");
			return;
		}
		
		UpbitResponse2 res2 = upClient.getTicker(params); 
		double tradePrice = res2.getTradePrice(); // 시세
		double basePrice = params.getBasePrice();
		double ratioUp = params.getRatioUp();
		double ratioDown = params.getRatioDown();
		TradeSide side = null;
		if ((Double.valueOf(res.getBidAccount().getBalance()) * tradePrice) > res.getMarket().getBid().getMinTotal() // 최소 매수 금액 보다 큰 경우
				&& (basePrice-tradePrice) > basePrice*ratioDown/100) { // 매수, 시장가가 작은 경우
			side =  TradeSide.BID;
			params.setVolume(res.getBidAccount().getBalance());
			
			double unit = params.getUnit();
			double decimalPlaces = params.getDecimalPlaces();
			double price = basePrice - (basePrice*ratioDown/100);
			if (unit > 0) {
//				price = Math.ceil((price)*decimalPlaces)/decimalPlaces - unit; // 올림 후 감소
				price = Math.floor((price)*decimalPlaces)/decimalPlaces + unit; // 버림 후 증가
				
			} else {
				price = Math.round((price)*decimalPlaces)/decimalPlaces; // 반올림
			}
			params.setPrice(String.valueOf(price)); // 매수인 경우, 시장가 보다 기대 이율로 거래 요청 -> 손실 최소화 목적
			
		} else if ((Double.valueOf(res.getAskAccount().getBalance()) * tradePrice) > res.getMarket().getAsk().getMinTotal() // 최소 매도 금액 보다 큰 경우
				&& (tradePrice-basePrice) > basePrice*ratioUp/100) { // 매도, 시장가가 큰 경우
			side = TradeSide.ASK;
			params.setVolume(res.getAskAccount().getBalance());
			params.setPrice(String.valueOf(tradePrice)); // 매도인 경우, 기대 이율 보다 시장가로 거래 요청 -> 이익 최대화 목적
			
		} else {
			// nothing
			log.info("대기...");
			return;
		}
		
		params.setSide(side.getCode());
		params.setOrdType("limit"); // limit : 지정가 주문, price : 시장가 주문(매수), market : 시장가 주문(매도)
		String result = upClient.postOrders2(params);
		
		lineClient.postNotify(LineParams.of("거래 결과 : " + result)); // 거래 결과 알림
	}
	
	public static void main(String[] args) {
		// KRW-MVL
		double ratio = 1;
		double basePrice = 19.5;
		double decimalPlaces = 0.1; // 10.0 : 소수 첫째자리까지 표현
		
		double tradePrice = 20.30;
		
		System.out.printf("%f, %f", (tradePrice-basePrice), basePrice*ratio/100);
		System.out.println();
		System.out.println("Math.ceil() : " + Math.ceil(basePrice*ratio/100*decimalPlaces)/decimalPlaces); // 올림
		System.out.println("Math.round() : " + Math.round((basePrice*ratio/100)*decimalPlaces)/decimalPlaces); // 반올림
		System.out.println("Math.floor() : " + Math.floor(basePrice*ratio/100*decimalPlaces)/decimalPlaces); // 버림
		System.out.println("Math.ceil(361.1415) : " + Math.ceil((361.1415)*decimalPlaces)/decimalPlaces); // 반올림
		System.out.println("Math.round(361.1415) : " + Math.round((361.1415)*decimalPlaces)/decimalPlaces); // 반올림
		System.out.println("Math.round(366.1415) : " + Math.round((366.1415)*decimalPlaces)/decimalPlaces); // 반올림
		System.out.println("Math.floor(361.1415) : " + Math.floor((361.1415)*decimalPlaces)/decimalPlaces); // 반올림
	}
}
