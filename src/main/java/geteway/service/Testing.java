package geteway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geteway.dto.BaseEventData;
import geteway.dto.ReasonFeedback;
import geteway.dto.ReasonFeedbackEventData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class Testing {
    private final ObjectMapper objectMapper;

//    @Scheduled(fixedRate = 5000)
    public void testRandom() throws JsonProcessingException {


        ReasonFeedback reasonFeedback = ReasonFeedback.builder()
                .text("Testing reason")
                .isActive("Masih aktiv")
                .build();

        String data = objectMapper.writeValueAsString(reasonFeedback);
        log.info("Data hasil : {} " , data);
        bindingData(data);

    }

    public void bindingData(String data){
        ReasonFeedbackEventData res = ReasonFeedbackEventData.DeseriallizeFromJson(data);

        log.info("Hasil res : = {} " , res);
    }
}
