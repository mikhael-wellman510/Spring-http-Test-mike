package geteway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper mapper;

    public void sendMessage(ChatMessage chatMessage){
        messagingTemplate.convertAndSend("/topic/messages" , chatMessage);
    }

//    @RabbitListener(queues = "exampleQueue")
    public void sendNotification(String msg) throws JsonProcessingException {
//        ChatMessage chat = mapper.readValue(msg,ChatMessage.class);
//        log.info("Kepanggil untuk kirim ke WS ! {} " , msg);
//        log.info("Hasil obj : {} " , chat);
//        messagingTemplate.convertAndSend("/topic/notification" , chat);
    }
}
