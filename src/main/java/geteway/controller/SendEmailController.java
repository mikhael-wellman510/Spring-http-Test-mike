package geteway.controller;

import geteway.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendEmailController {
    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendTextEmail(){
        emailService.sendEmail();

        return "Succes send";
    }
}
