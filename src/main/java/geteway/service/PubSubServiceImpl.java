package geteway.service;

import geteway.config.RabbitMqConfig;
import geteway.dto.ProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PubSubServiceImpl {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(){
        ProfileResponse pr = ProfileResponse.builder()
                .id("1")
                .name("Mike")
                .address("Bogor")
                .age(12)
                .weight(70)
                .hobby("Football")
                .build();

        rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE ,RabbitMqConfig.BROADCAST_ROUTING_KEY,pr);
        rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE,RabbitMqConfig.BROADCAST_MESSAGE_KEY, "Hahahahah");
    }

}
