package geteway.service;

import geteway.config.RabbitMqConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostConstructServiceImpl {

	@RabbitListener(queues = RabbitMqConfig.TESTING_QUEUE)
	public void results() throws InterruptedException {
		log.info("di method : {}", Thread.currentThread().getName());
		Thread.sleep(3000);
		log.info("Jos");
	}


	@PostConstruct
	public void running(){
		log.info("Running");
	}

}
