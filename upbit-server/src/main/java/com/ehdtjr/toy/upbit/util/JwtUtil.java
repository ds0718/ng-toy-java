package com.ehdtjr.toy.upbit.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtil {
	public static String getToken() {
		String accessKey = "bIqtMfLqM5Cf7GKHtG93sP07mFdQ7HpvUYOd2Yba";
		String secretKey = "ezR43IbNc5iCXjjCKxNVVxFthALHlE5nFtqnQxOK";
//		String accessKey = "NzYaM9mL4QAeG6nEZMm0qjNY5M3HPxubxxW4cPJp";
//		String secretKey = "wJx9zR1iK01Y4nT0N02VD5DVRMcKnDRkr8xunHzC";
		
		return getToken(accessKey, secretKey);
	}
	
	private static String getToken(String accessKey, String secretKey) {

		Algorithm algorithm = Algorithm.HMAC256(secretKey);

		String jwtToken = JWT.create().withClaim("access_key", accessKey)
				.withClaim("nonce", UUID.randomUUID().toString()).sign(algorithm);

		return "Bearer " + jwtToken;
	}
	
	public static String getToken(String queryString) throws Exception {
		String accessKey = "bIqtMfLqM5Cf7GKHtG93sP07mFdQ7HpvUYOd2Yba";
		String secretKey = "ezR43IbNc5iCXjjCKxNVVxFthALHlE5nFtqnQxOK";
		
		return getToken(accessKey, secretKey, queryString);
	}
	
	private static String getToken(String accessKey, String secretKey, String queryString) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(queryString.getBytes("utf8"));

		String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String jwtToken = JWT.create().withClaim("access_key", accessKey)
				.withClaim("nonce", UUID.randomUUID().toString()).withClaim("query_hash", queryHash)
				.withClaim("query_hash_alg", "SHA512").sign(algorithm);

		return "Bearer " + jwtToken;
	}
}
