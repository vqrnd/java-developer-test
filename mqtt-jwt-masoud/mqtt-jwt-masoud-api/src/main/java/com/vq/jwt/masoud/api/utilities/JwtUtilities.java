package com.vq.jwt.masoud.api.utilities;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vq.jwt.masoud.api.CustomConfigs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * This is a Spring component which provides some utilities to work with JWTs in other components
 */
@Component
public class JwtUtilities {

	private CustomConfigs customConfigs;
	@Autowired
	public void setCustomConfigs(CustomConfigs customConfigs) {
		this.customConfigs = customConfigs;
	}

	
	
	
	public String generateJwt(String email) {
		Date expirationDate = new Date();
		expirationDate.setTime(new Date().getTime() + customConfigs.getJwtDurationInMinutes() * 60 * 1000);

		Key hmacKey = Keys.hmacShaKeyFor(customConfigs.getJwtSecret().getBytes());

		return Jwts.builder()
				.setSubject(customConfigs.getJwtSubject())
				.setExpiration(expirationDate)
				.claim("email", email)
				.claim("authorized", true)
				.signWith(hmacKey, SignatureAlgorithm.HS256)
				.compact();
	}

	
	public String validateJwt(String jwt) {
		Jws<Claims> claims = Jwts.parser()
				.setSigningKey(customConfigs.getJwtSecret().getBytes())
				.parseClaimsJws(jwt);

		String subject = claims.getBody().getSubject();
		if (subject == null || !subject.equals(customConfigs.getJwtSubject())) {
			return "Failed " + manipulateJwtForLogging(jwt) + " reason: invalid message subject";
		}

		if (!claims.getBody().containsKey("authorized") 
				|| !(claims.getBody().get("authorized") instanceof Boolean)
				|| !((Boolean) claims.getBody().get("authorized"))) {
			return "Failed " + manipulateJwtForLogging(jwt) + " reason: invalid message claim";
		}
		return null;
	}

	
	public String manipulateJwtForLogging(String jwt) {
		String vowels = "aeiouAEIOU";
		boolean isIndexEven = true;
		StringBuilder newJwt = new StringBuilder();
		char currentChar;
		int digit;
		for (int i = 0; i < jwt.length(); i++, isIndexEven = !isIndexEven) {
			currentChar = jwt.charAt(i);
			
			if (vowels.indexOf(currentChar) >= 0) {
				newJwt.append('*');
				continue;
			}
			
			if ((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z')) {
				newJwt.append('#');
				continue;
			}
			
			try {
				digit = Integer.parseInt("" + currentChar);
			} catch (NumberFormatException ex) {
				newJwt.append(currentChar);
				continue;
			}
			
			if (isIndexEven && digit % 2 == 1) {
				newJwt.append(digit - 2);
			} 
			else if (!isIndexEven && digit % 2 == 0) {
				newJwt.append(digit + 1);
			} 
			else {
				newJwt.append(currentChar);
			}
		}
		return newJwt.toString();
	}
}
