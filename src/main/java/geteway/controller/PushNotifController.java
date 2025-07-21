package geteway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.config.RabbitMqConfig;
import geteway.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PushNotifController {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    @PostMapping("/senNotifikasi")
    public void sendNotif() throws JsonProcessingException {

        ChatMessage chat = new ChatMessage();
        chat.setContent("Test Notif");

        String json =  mapper.writeValueAsString(chat);
        rabbitTemplate.convertAndSend("notif",json);
        log.info("Send Notif !");
    }
}
