package geteway.controller;

import geteway.service.HikariServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequiredArgsConstructor
public class HikariController {

	private final HikariServiceImpl hikariService;

	@PostMapping("/hikari/{curr}")
	public void testHikari(@PathVariable Integer curr) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(curr);

		for (int i = 0 ; i < curr ; i++){
			executorService.submit(()->{
				hikariService.testHikari();

			});
		}

		executorService.shutdown();
	}
}
