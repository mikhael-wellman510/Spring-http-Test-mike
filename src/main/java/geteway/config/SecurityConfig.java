package geteway.config;

import geteway.Security.JwtAuthenticationFilter;
import geteway.service.AuthService.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Slf4j
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable) // gunakan SameSite=Strict + origin check
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/v1/auth/**" ,"/api/v1/authTest/**" , "/testing/**"  ).permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
						.requestMatchers("/user/**").hasAnyRole("USER","ADMIN","MANAGER")
						.requestMatchers("/api/manager/**").hasAnyRole("MANAGER")
						.anyRequest().authenticated()
				)
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		log.info("----- FilterChain ----");
		return http.build();
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		log.info("Pasword encoder");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		log.info("Authentication manager security config");
		return config.getAuthenticationManager();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration cfg = new CorsConfiguration();
		cfg.setAllowedOrigins(List.of(System.getProperty("frontend.origin",
				"http://localhost:5173"))); // atau dari application.yml
		cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
		cfg.setAllowedHeaders(List.of("*"));
		cfg.setAllowCredentials(true); // wajib kalau pakai cookie
		cfg.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cfg);
		return source;
	}
}
