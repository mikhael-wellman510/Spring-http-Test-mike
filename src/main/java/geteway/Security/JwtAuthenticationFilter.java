package geteway.Security;

import geteway.service.AuthService.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final CustomUserDetailsService customUserDetailsService;

//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		String path = request.getServletPath();
//		logger.info(path);
//		// skip untuk endpoint auth
//		return path.startsWith("/api/v1/auth");
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			logger.info("cek");
		filterChain.doFilter(request, response);

		//		Cookie[] cookies = req.getCookies();
//		if (cookies != null) {
//			Cookie access = Arrays.stream(cookies)
//					.filter(c -> "ACCESS_TOKEN".equals(c.getName()))
//					.findFirst().orElse(null);
//
//			if (access != null && jwtService.isValid(access.getValue())) {
//				String username = jwtService.getUsername(access.getValue());
//				if (SecurityContextHolder.getContext().getAuthentication() == null) {
//					UserDetails user = userDetailsService.loadUserByUsername(username);
//					UsernamePasswordAuthenticationToken auth =
//							new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//					SecurityContextHolder.getContext().setAuthentication(auth);
//				}
//			}
//		}
//		filterChain.doFilter(req, res);
	}
}
