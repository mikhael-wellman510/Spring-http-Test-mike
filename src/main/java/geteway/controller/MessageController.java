package geteway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.config.RabbitMqConfig;
import geteway.dto.ChatMessage;
import geteway.dto.MessageRequest;
import geteway.service.ChatServiceImpl;
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
    private final ChatServiceImpl chatService;
//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${spring.rabbitmq.routingkey}")
//    private String routingKey;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageRequest messageRequest) throws JsonProcessingException {

        String json = objectMapper.writeValueAsString(messageRequest);
        log.info("Hasil json : {} " , json);

        rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE ,json);

        return "Success";
    };

    @PostMapping("/sendNotifRabbit")
    public void sendNotifRabbit() throws JsonProcessingException {
        log.info("Sen notif");
        ChatMessage chat = new ChatMessage();
        chat.setContent("Notif Test");

        String json = objectMapper.writeValueAsString(chat);
        rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE,json);
    }
}
