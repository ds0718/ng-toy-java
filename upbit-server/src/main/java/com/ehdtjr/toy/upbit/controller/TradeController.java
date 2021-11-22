package com.ehdtjr.toy.upbit.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehdtjr.toy.upbit.config.TradeConfig;
import com.ehdtjr.toy.upbit.model.dto.UpbitParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/trade")
public class TradeController {

	@Autowired
	private TradeConfig tradeConf;
	
	@PostMapping("/tokens")
	public String tokens(@ModelAttribute UpbitParams params) throws Exception {
		try {
			if (StringUtils.isNotBlank(params.getAccessKey())) {
				tradeConf.setAccessKey(params.getAccessKey());
			}
			if (StringUtils.isNotBlank(params.getSecretKey())) {
				tradeConf.setSecretKey(params.getSecretKey());
			}
			if (StringUtils.isNotBlank(params.getMarket())) {
				tradeConf.setMarket(params.getMarket());
			}
			if (params.getRatio() != null && params.getRatio() > 0) {
				tradeConf.setRatio(params.getRatio());
			}
			if (params.getBasePrice() != null && params.getBasePrice() > 0) {
				tradeConf.setBasePrice(params.getBasePrice());
			}
			
			log.info(tradeConf.toString());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return "OK";
	}
}
