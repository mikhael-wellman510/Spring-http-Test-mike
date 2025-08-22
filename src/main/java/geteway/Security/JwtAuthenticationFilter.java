package geteway.Security;

import geteway.entity.Authentication.UserPrincipal;
import geteway.service.AuthService.CustomUserDetailsService;
import geteway.util.AuthUtils.CookieUtils;
import geteway.util.AuthUtils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final CustomUserDetailsService customUserDetailsService;
	private final CookieUtils cookieUtils;
	private final JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			logger.info("cek");

			// todo -> dapatkan token dari cookies
			String token = cookieUtils.getTokenFromCookies(request);
			logger.info("token from cookie : " + token);

		if (token != null) {
			Map<String,Object> claims = jwtUtils.validateTokenUsernameClaims(token);
			logger.info("get username : {} " + claims);
			String username = claims.get("username").toString();
			logger.info("username " + username);
			UserPrincipal up = (UserPrincipal)customUserDetailsService.loadUserByUsername(username);
			if (username != null) {
				UsernamePasswordAuthenticationToken auth =
						new UsernamePasswordAuthenticationToken(up,null, up.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		}
		filterChain.doFilter(request, response);

	}
}
