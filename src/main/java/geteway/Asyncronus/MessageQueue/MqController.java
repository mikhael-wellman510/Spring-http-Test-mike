package geteway.Asyncronus.MessageQueue;

import geteway.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MqController {

	private final RabbitTemplate rabbitTemplate;


	@PostMapping("/api/restricted/sendQueue")
	public ResponseEntity<?>queueControler(){

		rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE, RabbitMqConfig.SPORTY_SERVICE1_ROUTING_KEY , "tes1");
		rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE, RabbitMqConfig.SPORTY_SERVICE2_ROUTING_KEY,"tes2");
		rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE, RabbitMqConfig.SPORTY_SERVICE3_ROUTING_KEY,"tes3");
		return ResponseEntity.ok("Success Send");
	}
}
