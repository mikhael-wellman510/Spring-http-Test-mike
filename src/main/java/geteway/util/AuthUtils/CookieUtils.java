package geteway.util.AuthUtils;

import geteway.enums.CookieEnum;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CookieUtils {



	public void addTokenCookie(HttpServletResponse response, String token) {
		Cookie cookie = new Cookie(CookieEnum.AUTH_TOKEN.name(), token);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); // ganti true di production (https)
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60); // 1 hari
		response.addCookie(cookie);
	}

	public Cookie clearTokenCookie() {
		Cookie cookie = new Cookie(CookieEnum.AUTH_TOKEN.name(), null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		return cookie;
	}

	public String getTokenFromCookies(HttpServletRequest request) {
		Cookie[]cookies = request.getCookies();
		log.info("Cookies : {} " ,cookies);
		if (cookies == null) return null;
		for (Cookie c : cookies) {
			log.info("cookie name : {} " ,c.getName());
			if (CookieEnum.AUTH_TOKEN.name().equals(c.getName())) {
				return c.getValue();
			}
		}
		return null;
	}
}
