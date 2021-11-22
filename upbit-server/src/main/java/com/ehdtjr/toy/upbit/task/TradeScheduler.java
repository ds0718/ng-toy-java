package com.ehdtjr.toy.upbit.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ehdtjr.toy.upbit.config.TradeConfig;
import com.ehdtjr.toy.upbit.model.dto.UpbitParams;
import com.ehdtjr.toy.upbit.service.TradeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TradeScheduler {
	
	@Autowired
	private TradeConfig tradeConf;
	
	@Autowired
	private TradeService tradeService;
	
//	@Scheduled(fixedRate = 5000)
	@Scheduled(fixedDelayString = "${ehdtjr.task.do-trade-schedule}", initialDelayString = "${ehdtjr.task.initial-delay-schedule}")
	public void executeTrade() {
		log.trace("current thread[{}], execute trade", Thread.currentThread().getName());

		UpbitParams params = UpbitParams.of(tradeConf);
		tradeService.trade(params);
	}
}
