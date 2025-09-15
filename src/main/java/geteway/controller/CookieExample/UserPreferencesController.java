package geteway.controller.CookieExample;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class UserPreferencesController {

	@PostMapping("/savePreferences")
	public ResponseEntity<?>addCookie(@RequestParam String lang , HttpServletResponse httpServletResponse){
		Cookie langCookie = new Cookie("lang" , lang);
		langCookie.setHttpOnly(true);
		langCookie.setSecure(true);
		langCookie.setPath("/");
		langCookie.setMaxAge(24 * 60 * 60);

		httpServletResponse.addCookie(langCookie);

		return ResponseEntity.ok("Succes");
	}

	@GetMapping("/getLang")
	public ResponseEntity<?>getLang(HttpServletRequest httpServletRequest){
		Cookie[]cookies = httpServletRequest.getCookies();

		log.info("Cookies : {} " , cookies);

		for (Cookie cookie : cookies){

			log.info("Cookie -> name={}, value={}", cookie.getName(), cookie.getValue());
		}

		return ResponseEntity.ok("Okey");
	}
}
