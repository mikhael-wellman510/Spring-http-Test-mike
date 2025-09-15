package geteway.util.AuthUtils;

import geteway.entity.Authentication.Role;
import geteway.entity.Authentication.UserPrincipal;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpiration;

	public String generateToken(UserPrincipal userPrincipal){
		Map<String,Object>claim = new HashMap<>();
		claim.put("username" , userPrincipal.getUsername());
		claim.put("roles" , userPrincipal.getRoles().stream().map(Role::getName).toList());
		claim.put("address" , userPrincipal.getAddress());

		return Jwts.builder()
				.setIssuer("JSON-WEB-TOKEN")
				.setSubject(userPrincipal.getName())
				.addClaims(claim)
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() +jwtExpiration))
				.signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
				.compact();
	}

	public Map<String ,Object> validateTokenUsernameClaims(String token){

		try {
			return Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
					.build()
					.parseClaimsJws(token)
					.getBody();

		} catch (JwtException e) {
			log.info("Err : {} " ,e.getMessage());
			return null;

		}

	}

	public String validateTokenAndGetUsername(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (JwtException e) {
			return null; // invalid token
		}
	}
}
