package geteway.controller;


import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class RateLimiterController {


	@GetMapping("/limiter")
	@RateLimiter(name = "apiService" , fallbackMethod = "rateLimiterFallback")
	public ResponseEntity<?> testRatelimiter(){

		return ResponseEntity.ok("Rate limiter testing");
	}

	public ResponseEntity<?> rateLimiterFallback(RequestNotPermitted ex){

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("To Many Request , try again later");
	}
}
