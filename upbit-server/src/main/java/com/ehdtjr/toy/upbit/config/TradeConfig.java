package com.ehdtjr.toy.upbit.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix="ehdtjr.trade")
@Getter @Setter
public class TradeConfig {
	private String accessKey;
	private String secretKey;
	private String market;
	private Double ratio;
	private Double basePrice;
	private Double unit;
	private Double decimalPlaces;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
