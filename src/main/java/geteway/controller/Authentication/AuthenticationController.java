package geteway.controller.Authentication;

import geteway.dto.AuthenticationDto.LoginRequest;
import geteway.dto.AuthenticationDto.RegisterRequest;
import geteway.entity.Authentication.UserPrincipal;
import geteway.enums.CookieEnum;
import geteway.service.AuthService.AuthenticationServiceImpl;
import geteway.util.AuthUtils.DecryptUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@EnableMethodSecurity(prePostEnabled = true)
public class AuthenticationController {

	private final AuthenticationServiceImpl authenticationService;

	private final AuthenticationManager authenticationManager;
	@PostMapping("/register")
	public ResponseEntity<?>register(@RequestBody RegisterRequest registerRequest){
		log.info("res: {}" ,registerRequest);
		var res = authenticationService.registerAccount(registerRequest);

		return ResponseEntity.ok(res);
	}


	/**
	 * 1. filter chain
	 * 2. authenticate(UsernamePassAuth)
	 * 3. LoadByUsername
	 */

	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest , HttpServletResponse response){
		String password = DecryptUtils.decrypt(loginRequest.getPassword());
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), password);

		Authentication authentication = authenticationManager.authenticate(auth);

		// Todo -> coba pakai , dan tidak pakai , kata bikin user yg login ga ketawan
		// Todo -> ketika hit api lain ya
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
		addSecureHttpOnlyCookie(response , CookieEnum.AUTH_TOKEN.name(), userPrincipal.getJwtToken());

		// Todo -> Tambahkan setup cookie
		return ResponseEntity.ok("Success Login Wit Cookie httponly");
	}

	public void addSecureHttpOnlyCookie(HttpServletResponse response , String key , String value){
		Cookie cookie =new Cookie(key , value);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60);

		response.addCookie(cookie);

	}
}
