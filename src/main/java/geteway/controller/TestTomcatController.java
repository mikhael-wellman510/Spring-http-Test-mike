package geteway.controller;

import geteway.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/restricted")
@RequiredArgsConstructor
public class TestTomcatController {

	private final RabbitTemplate rabbitTemplate;

	@GetMapping("/tesTomcat")
	public void testTomcat() throws InterruptedException {
		log.info("Cek ");

		Thread.sleep(2000);
	}

	@GetMapping("/testRabbit")
	public ResponseEntity<?>testRabbitMq(){
		log.info("Lagi jalan di thread: {}", Thread.currentThread().getName());
		rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE , RabbitMqConfig.TESTING_ROUTING_KEY , "Anjay");

		return ResponseEntity.ok("Sukses Coy");

	}


}
