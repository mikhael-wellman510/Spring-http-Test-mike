package geteway.controller;

import geteway.service.PubSubServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PubSubController {

    private final PubSubServiceImpl pubSubService;

    @PostMapping("/pubSub")
    public void sendMessagePubSub(){
        pubSubService.sendMessage();
    }
}
