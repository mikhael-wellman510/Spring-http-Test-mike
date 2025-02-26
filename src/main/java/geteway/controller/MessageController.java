package geteway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.config.RabbitMqConfig;
import geteway.dto.MessageRequest;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {


    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${spring.rabbitmq.routingkey}")
//    private String routingKey;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageRequest messageRequest) throws JsonProcessingException {

        String json = objectMapper.writeValueAsString(messageRequest);
        log.info("Hasil json : {} " , json);

        rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME ,json);

        return "Success";
    };
}
