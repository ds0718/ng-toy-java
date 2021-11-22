package com.ehdtjr.toy.upbit.ext;

import java.lang.reflect.Type;
import java.net.URI;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ehdtjr.toy.upbit.model.dto.UpbitParams;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestClient {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * encode 안된 url 요청 시
	 * 
	 * @param method
	 * @param url
	 * @param params
	 * @param paramMap
	 * @param headers
	 * @param resDataType
	 * @return
	 * @throws Exception
	 */
	public Object request(HttpMethod method, String url, UpbitParams params, MultiValueMap<String, Object> paramMap, HttpHeaders headers, Type resDataType)
			throws Exception {
		return request(method, new URI(url), params, paramMap, headers, resDataType);
	}
	
	
	/**
	 * encode 된 url, params 요청 시
	 * 
	 * @param method
	 * @param uri
	 * @param params
	 * @param paramMap
	 * @param headers
	 * @param resDataType
	 * @return
	 * @throws Exception
	 */
	public Object request(HttpMethod method, URI uri, UpbitParams params, MultiValueMap<String, Object> paramMap, HttpHeaders headers, Type resDataType)
			throws Exception {
		HttpEntity<?> entity = new HttpEntity<>(paramMap, headers);
		
		return request(method, uri, params, entity, resDataType);
	}
	
	public Object request(HttpMethod method, URI uri, UpbitParams params, HttpHeaders headers, Type resDataType)
			throws Exception {
//		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		Gson gson = new Gson();
		HttpEntity<?> entity = new HttpEntity<>(gson.toJson(params), headers);
		
		return request(method, uri, params, entity, resDataType);
	}
	
	private Object request(HttpMethod method, URI uri, UpbitParams params, HttpEntity<?> entity, Type resDataType)
			throws Exception {
		
		log.info("req-url : {}", uri.toString());
		log.info("req-header : {}", entity.getHeaders());
		log.info("req-body : {}", entity.getBody());

		try {
			ResponseEntity<String> httpResponse = restTemplate.exchange(uri, method, entity, String.class);
			log.info("res-status : {}", httpResponse.getStatusCode().toString());
			log.info("res-header : {}", httpResponse.getHeaders().toString());
			log.info("res-body : {}", httpResponse.getBody());

			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
			if (httpResponse.getStatusCode() == HttpStatus.OK) {
//				return new Gson().fromJson(httpResponse.getBody(), new TypeToken<UpbitResponse>() {}.getType());
				return gson.fromJson(httpResponse.getBody(), resDataType);
				
			} else { // status 200이 아닌 경우, 아래 HttpStatusCodeException 에서 catch 됨. function return을 위해 추가
				throw new Exception(String.format("failed to request. httpStatus(%d), body(%s)", 
						httpResponse.getStatusCode().value(), httpResponse.getBody()));
			}

		}  catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(String.format("failed to request. cause(%s)", ExceptionUtils.getRootCauseMessage(e)));
		}
	}

}
