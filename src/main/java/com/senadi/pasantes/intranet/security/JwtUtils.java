package com.senadi.pasantes.intranet.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {

	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(
					"semillafckmsdmfmisofsfosofifisdefikiregi9483r84ruff8ufru87rg8ww322rrrrfsemillawdefrfgdtvgbomgobmdrb203290324825835824202405385358035edscfsdfsdfdsuio")
					.parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			LOG.error("ERROR", e);
		}
		return false;
	}

	public String getuserNameFromjwtToken(String token) {
		return Jwts.parser().setSigningKey(
				"semillafckmsdmfmisofsfosofifisdefikiregi9483r84ruff8ufru87rg8ww322rrrrfsemillawdefrfgdtvgbomgobmdrb203290324825835824202405385358035edscfsdfsdfdsuio")
				.parseClaimsJws(token).getBody().getSubject();

	}

}