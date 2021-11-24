package com.ehdtjr.toy.upbit.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ehdtjr.toy.upbit.config.LineConfig;
import com.ehdtjr.toy.upbit.config.LineUrlConfig;
import com.ehdtjr.toy.upbit.model.dto.LineParams;
import com.ehdtjr.toy.upbit.model.dto.LineResponse;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LineClient {

	@Autowired
	private LineConfig lineConf;
	@Autowired
	private LineUrlConfig lineUrlConf;

	@Autowired
	private RequestClient requestClient;


	/**
	 * <pre>
	 * notify
	 *     req) POST /api/notify
	 * </pre>
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public LineResponse postNotify(LineParams params) throws Exception {
		StringBuilder url = new StringBuilder(lineConf.getHostV1());
		url.append(lineUrlConf.getPostNotify());

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + lineConf.getAccessToken());

		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("message", params.getMessage());
		
		return (LineResponse)requestClient.request(HttpMethod.POST, url.toString(), null, paramMap, headers, 
				new TypeToken<LineResponse>() {}.getType());
	}
}
