package com.ehdtjr.toy.upbit.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConfigurationProperties(prefix="ehdtjr.http-client")
@Data
public class RestTemplateConfig {
	private int connTimeout;
	private int readTimeout;
	private int poolSize;
	private int routeSize;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
	@Bean
	public RestTemplate getCustomRestTemplate() {
		log.info("RestTemplate(HttpClient) : connTimeout({}), readTimeout({}), poolSize({}), routeSize({})"
				, connTimeout, readTimeout, poolSize, routeSize);
		
		try {
			// 서버에서 keep-alive 셋팅이 되어 있지 않으면, 의도한대로 connection pool 이 동작하지 않는다
			HttpClient httpClient = HttpClientBuilder.create()
					.setMaxConnTotal(poolSize) // connection pool size
					.setMaxConnPerRoute(routeSize) // ip, port 하나 당 연결 제한 개수
					.build();
			
			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
			httpRequestFactory.setConnectTimeout(connTimeout);
			httpRequestFactory.setReadTimeout(readTimeout);
			httpRequestFactory.setHttpClient(httpClient);
			
			return new RestTemplate(httpRequestFactory);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return null;
//		return new RestTemplateBuilder()
//				.setConnectTimeout(Duration.ofMillis(connTimeout))
//				.setReadTimeout(Duration.ofMillis(readTimeout))
////				.additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
//				// response 한글 안깨짐, but kakao image 전송 시 안됨(org.springframework.web.client.RestClientException: No HttpMessageConverter for org.springframework.util.LinkedMultiValueMap and content type "multipart/form-data")
////				.additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))	
//				.build();
	}
	
}
