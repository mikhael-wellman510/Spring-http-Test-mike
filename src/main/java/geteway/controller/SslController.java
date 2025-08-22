package geteway.controller;

import geteway.service.SslServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SslController {

	private final SslServiceImpl sslService;

	@GetMapping("/testSsl")
	public CompletableFuture<?>testSsl(){

		return sslService.testSsl().thenApply(res -> {

			return ResponseEntity.ok(res);
		}).exceptionally(ex -> {
			log.info("Cek {} " , ex.getMessage());
			return ResponseEntity.internalServerError().build();
		});
	}

}
