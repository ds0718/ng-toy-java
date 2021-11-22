package com.ehdtjr.toy.upbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehdtjr.toy.upbit.ext.UpbitClient;
import com.ehdtjr.toy.upbit.model.dto.UpbitParams;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/test/v1")
public class TestController {

	@Autowired
	private UpbitClient upClient;
	
	@GetMapping("/accounts")
	public String getAccounts() throws Exception {
		try {
			upClient.getAccounts(null);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return "OK";
	}
	
	@GetMapping("/orders/chance")
	public String getOrdersChance(@RequestParam String market) throws Exception {
		try {
			UpbitParams params = new UpbitParams();
			params.setMarket(market);
			
			UpbitResponse res = upClient.getOrdersChance(params);
			log.info(res.toString());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return "OK";
	}
	
	@PostMapping("/orders")
	public String orders(@ModelAttribute UpbitParams params) throws Exception {
		try {
//			upClient.postOrders(params);
			upClient.postOrders2(params);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return "OK";
	}
	
	@GetMapping("/ticker")
	public String getTicker(@RequestParam String market) throws Exception {
		try {
			UpbitParams params = new UpbitParams();
			params.setMarket(market);
			
			UpbitResponse2 res = upClient.getTicker(params);
			log.info(res.toString());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return "OK";
	}
}
