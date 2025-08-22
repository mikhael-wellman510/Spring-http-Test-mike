package geteway.util.AuthUtils;

import geteway.entity.Authentication.Role;
import geteway.entity.Authentication.UserPrincipal;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpiration;

	public String generateToken(UserPrincipal userPrincipal){
		Map<String,Object>claim = new HashMap<>();

		claim.put("roles" , userPrincipal.getRoles().stream().map(Role::getName).toList());


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
}
