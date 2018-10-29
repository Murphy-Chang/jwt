package com.mine.jwt.jwt.service;

import com.mine.jwt.util.Base64Util;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/9/12
 */
public class JwtAuthencationService {
	private static Logger logger = LoggerFactory.getLogger(JwtAuthencationService.class);

	//非对称加密钥匙串--公钥(解析用)和私钥(加密用)
	private static KeyPair KETPAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);

	//有效时间(2小时)
	private static final long EXPIRATION = 1000*60*60*2L;

	//构造方法
//	public JwtAuthencationService(){
//		KETPAIR = Keys.keyPairFor(SignatureAlgorithm.RS256); //or RS384, RS512, PS256, PS384, PS512, ES256, ES384, ES512
//	}

	/**
	 * 创建jwt
	 * @param userId
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public static String createJsonWebToken(Map<String, Object> data) throws JwtException{
		//创建jwt
		String jsonWebToken = Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.addClaims(data)
				.setExpiration(new Date(new Date().getTime()+EXPIRATION))
				.signWith(KETPAIR.getPrivate())
				.compact();

		return jsonWebToken;
	}

	/**
	 * 解析jwt
	 * @param jsonWebToken
	 * @return
	 * @throws Exception
	 */
	public static Jws<Claims> parseJsonWebToken(String jsonWebToken) throws JwtException{
		Jws<Claims> claims = Jwts.parser().setSigningKey(KETPAIR.getPrivate()).parseClaimsJws(jsonWebToken);
		return claims;
	}

}
