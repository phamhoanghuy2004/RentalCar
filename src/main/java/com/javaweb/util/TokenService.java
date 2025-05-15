package com.javaweb.util;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class TokenService {
	
	public static String genarateToken (String name, Long id, String rule, String jwtSecret) throws KeyLengthException, JOSEException {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet claims = new JWTClaimsSet.Builder()
				.subject(name)
				.issuer("RetalCar.com")
				.issueTime(new Date())
				.expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
				.claim("id", id)
				.claim("role", rule)
				.build();
		
		Payload payload = new Payload(claims.toJSONObject());
		JWSObject jwsObject = new JWSObject(header,payload);
		jwsObject.sign(new MACSigner (jwtSecret.getBytes()));
		return jwsObject.serialize();
	}
	
	
	public static Boolean checkToken (String token, String jwtSecret) throws JOSEException, ParseException  {
		if (token == null) {
			return false;
		}
		JWSVerifier verifier = new MACVerifier(jwtSecret.getBytes());
		SignedJWT signedJWT = SignedJWT.parse(token);
		Date expirateTime  = signedJWT.getJWTClaimsSet().getExpirationTime();
		Boolean verified = signedJWT.verify(verifier);
		
		if (!verified) return false;
	    if (expirateTime == null) return false;
	    return expirateTime.after(new Date());
	}
	
	public static Long getId (String token) throws ParseException {
		SignedJWT signedJWT = SignedJWT.parse(token);
		Long id = signedJWT.getJWTClaimsSet().getLongClaim("id");
		return id;
	}
	
	public static String getRole (String token) throws ParseException{
		SignedJWT signedJWT = SignedJWT.parse(token);
		String role = signedJWT.getJWTClaimsSet().getStringClaim("role");
		return role;
	}
}
