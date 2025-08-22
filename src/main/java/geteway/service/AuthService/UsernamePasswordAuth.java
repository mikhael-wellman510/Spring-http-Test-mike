package geteway.service.AuthService;

import geteway.entity.Authentication.UserPrincipal;
import geteway.util.AuthUtils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsernamePasswordAuth implements AuthenticationProvider {
	private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("Authenrication usernamePasswordAuth");
		try {
			String username = authentication.getName();
			String password = authentication.getCredentials().toString();

			UserPrincipal up = (UserPrincipal)customUserDetailsService.loadUserByUsername(username);
			log.info("Username benar !");
			if(passwordEncoder.matches(password,up.getPassword())){
				String token = jwtUtils.generateToken(up);
				up.setJwtToken(token);
				log.info("Password benar !");
				return new UsernamePasswordAuthenticationToken(up,password,up.getAuthorities());
			}else {
				throw new RuntimeException("Password invalid");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}



	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
