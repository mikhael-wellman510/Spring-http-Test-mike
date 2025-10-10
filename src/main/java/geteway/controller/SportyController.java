package geteway.controller;

import geteway.service.SportyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restricted")
public class SportyController {

	private final SportyServiceImpl sportyService;

	@PostMapping("/ceks")
	public void cekssss(){
		log.info("cekkkk");
	}

	@PostMapping("/sportyPost")
	public ResponseEntity<?>getSportyContrs() throws ExecutionException, InterruptedException {

		var data = sportyService.getSporty().get();

		return ResponseEntity.ok(data);
	}

	@PostMapping("/sporty2")
	public CompletableFuture<?>get(){

		return sportyService.getSporty2()
				.thenApply(res -> {
					return ResponseEntity.ok(res);
				})
				.exceptionally(ex-> {
					return ResponseEntity.internalServerError().build();
				});
	}
}
