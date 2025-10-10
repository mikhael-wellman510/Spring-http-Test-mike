package geteway.service.email;

import geteway.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailService {

	private final RabbitTemplate rabbitTemplate;

	public String sendEmail(){

		rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE  , RabbitMqConfig.EMAIL_ROUTING_KEY,"Email terkirim delay");

		return "Email Terkirim";
	}

}
