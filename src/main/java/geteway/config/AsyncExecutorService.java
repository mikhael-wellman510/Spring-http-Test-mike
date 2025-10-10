package geteway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncExecutorService {

	@Bean
	public ExecutorService securityExecutor() {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		return new DelegatingSecurityContextExecutorService(executor);
	}
}
