package com.ehdtjr.toy.upbit.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix="upbit.url")
@Getter @Setter
public class UpbitUrlConfig {
	
	// 자산
	private String getAccounts;

	// 주문
	private String getOrdersChance;
	private String getOrder;
	private String getOrders;
	private String deleteOrders;
	private String postOrders;
	
	// 현재가 정보
	private String getTicker;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
