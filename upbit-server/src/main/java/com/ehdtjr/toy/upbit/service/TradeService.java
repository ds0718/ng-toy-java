package com.ehdtjr.toy.upbit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehdtjr.toy.upbit.ext.UpbitClient;
import com.ehdtjr.toy.upbit.model.TradeSide;
import com.ehdtjr.toy.upbit.model.dto.UpbitParams;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse2;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradeService {
	
	@Autowired
	private UpbitClient upClient;
	
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
		double ratio = params.getRatio();
		TradeSide side = null;
		if ((basePrice-tradePrice) > basePrice*ratio/100) { // 매수
			side =  TradeSide.BID;
			params.setVolume(res.getBidAccount().getBalance());
			
		} else if ((tradePrice-basePrice) > basePrice*ratio/100) { // 매도
			side = TradeSide.ASK;
			params.setVolume(res.getAskAccount().getBalance());
			
		} else {
			// nothing
			log.info("대기...");
			return;
		}
		
		params.setSide(side.getCode());
		params.setPrice(String.valueOf(tradePrice));
		params.setOrdType("limit");
		upClient.postOrders2(params);
	}
}
