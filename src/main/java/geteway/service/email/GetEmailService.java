package geteway.service.email;

import geteway.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetEmailService {


	@RabbitListener(queues = RabbitMqConfig.EMAIL_QUEUE)
	public void getEmail(String message) throws InterruptedException {
		log.info("Cek");
		Thread.sleep(4000);
		log.info("Pesan terkirim !!! {}", message);
	}

}
