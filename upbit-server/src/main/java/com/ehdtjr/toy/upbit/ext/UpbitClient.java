package com.ehdtjr.toy.upbit.ext;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ehdtjr.toy.upbit.config.UpbitConfig;
import com.ehdtjr.toy.upbit.config.UpbitUrlConfig;
import com.ehdtjr.toy.upbit.model.dto.UpbitParams;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse;
import com.ehdtjr.toy.upbit.model.dto.UpbitResponse2;
import com.ehdtjr.toy.upbit.util.JwtUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpbitClient {

	@Autowired
	private UpbitConfig upConf;
	@Autowired
	private UpbitUrlConfig upUrlConf;

	@Autowired
	private RequestClient requestClient;

	/**
	 * <pre>
	 * 자산 > 전체 계좌 조회
	 *     req) GET /v1/accounts
	 * </pre>
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public UpbitResponse getAccounts(UpbitParams params) throws Exception {
		StringBuilder url = new StringBuilder(upConf.getHostV1());
		url.append(upUrlConf.getGetAccounts());

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.set(HttpHeaders.AUTHORIZATION, JwtUtil.getToken());

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url.toString());

		return (UpbitResponse)requestClient.request(HttpMethod.GET, uriBuilder.build().toUri(), params, headers, 
				new TypeToken<UpbitResponse>() {}.getType());
	}

	/**
	 * <pre>
	 * 주문 > 주문 가능 정보
	 *     req) GET /v1/orders/chance
	 * </pre>
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public UpbitResponse getOrdersChance(UpbitParams params) throws Exception {
		StringBuilder url = new StringBuilder(upConf.getHostV1());
		url.append(upUrlConf.getGetOrdersChance());

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url.toString())
				.queryParam("market", params.getMarket());

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.set(HttpHeaders.AUTHORIZATION, JwtUtil.getToken(uriBuilder.build().getQuery()));

		return (UpbitResponse)requestClient.request(HttpMethod.GET, uriBuilder.build().toUri(), params, headers, 
				new TypeToken<UpbitResponse>() {}.getType());
	}

	/**
	 * <pre>
	 * 주문 > 주문하기
	 *     req) POST /v1/orders
	 * </pre>
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public UpbitResponse postOrders(UpbitParams params) throws Exception {
		StringBuilder url = new StringBuilder(upConf.getHostV1());
		url.append(upUrlConf.getGetOrdersChance());

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url.toString())
				.queryParam("market", params.getMarket());

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.set(HttpHeaders.AUTHORIZATION, JwtUtil.getToken(uriBuilder.build().getQuery()));

		return (UpbitResponse)requestClient.request(HttpMethod.POST, uriBuilder.build().toUri(), params, headers, 
				new TypeToken<UpbitResponse>() {}.getType());
	}

	public void postOrders2(UpbitParams params) throws Exception {
//		String accessKey = System.getenv("UPBIT_OPEN_API_ACCESS_KEY");
//		String secretKey = System.getenv("UPBIT_OPEN_API_SECRET_KEY");
//		String serverUrl = System.getenv("UPBIT_OPEN_API_SERVER_URL");
		String serverUrl = upConf.getHostV1();

		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("market", params.getMarket());
		paramsMap.put("side", params.getSide());
		paramsMap.put("volume", params.getVolume());
		paramsMap.put("price", params.getPrice());
		paramsMap.put("ord_type", params.getOrdType());
//		paramsMap.put("identifier", "asfdqqwer11324");

		ArrayList<String> queryElements = new ArrayList<>();
		for (Map.Entry<String, String> entity : paramsMap.entrySet()) {
			queryElements.add(entity.getKey() + "=" + entity.getValue());
		}

		String queryString = String.join("&", queryElements.toArray(new String[0]));

		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(queryString.getBytes("UTF-8"));

		String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

		Algorithm algorithm = Algorithm.HMAC256(params.getSecretKey());
		String jwtToken = JWT.create().withClaim("access_key", params.getAccessKey())
				.withClaim("nonce", UUID.randomUUID().toString()).withClaim("query_hash", queryHash)
				.withClaim("query_hash_alg", "SHA512").sign(algorithm);

		String authenticationToken = "Bearer " + jwtToken;

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(serverUrl + "/orders");
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", authenticationToken);
			request.setEntity(new StringEntity(new Gson().toJson(paramsMap)));

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			log.info(EntityUtils.toString(entity, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * <pre>
	 * 현재가 정보
	 *     req) GET /v1/ticker
	 * </pre>
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public UpbitResponse2 getTicker(UpbitParams params) throws Exception {
		StringBuilder url = new StringBuilder(upConf.getHostV1());
		url.append(upUrlConf.getGetTicker());

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url.toString())
				.queryParam("markets", params.getMarket());

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//		headers.set(HttpHeaders.AUTHORIZATION, JwtUtil.getToken(uriBuilder.build().getQuery()));

		return ((List<UpbitResponse2>)requestClient.request(HttpMethod.GET, uriBuilder.build().toUri(), params, headers
				, new TypeToken<List<UpbitResponse2>>() {}.getType())).get(0);
	}
}
