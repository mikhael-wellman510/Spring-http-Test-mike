package geteway.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestSendEmailController {


	@PostMapping("/api/restricted/sendEmailTest")
	public ResponseEntity<?>sendEmailTestings(){

		CompletableFuture.runAsync(()->{
			try {
				sendEmails();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

		return ResponseEntity.ok("Email sudah terkirim");

	}


	public void sendEmails() throws InterruptedException {
		Thread.sleep(5000);
		log.info("Email Sudah Terkirim !!!");
	}

}
