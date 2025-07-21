package geteway.controller;

import geteway.dto.ChatMessage;
import geteway.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatServiceImpl chatService;

    @MessageMapping("/sendMessage")
    public void processMessage(@Payload ChatMessage chatMessage){

        chatMessage.setTimestamp(LocalDateTime.now());
        chatService.sendMessage(chatMessage);
    }
}
