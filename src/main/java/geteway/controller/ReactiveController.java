package geteway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
public class ReactiveController {


	@GetMapping("/hello")
	public Mono<String> helloGuys(){
		log.info("Thread: {}", Thread.currentThread().getName());

		Mono<String> cek = Mono.just("Hello cok")
				.delayElement(Duration.ofSeconds(2));

		log.info("Thread: {}", Thread.currentThread().getName());
		return cek;
	}

	@GetMapping("/hello2")
	public void hel() throws InterruptedException {
		log.info("Thread: {}", Thread.currentThread().getName());
		Thread.sleep(2000);
		log.info("Thread: {}", Thread.currentThread().getName());

	}
}

