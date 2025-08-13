package geteway.service;

import geteway.config.RabbitMqConfig;
import geteway.entity.ProductPesimistic;
import geteway.repository.ProductPesimisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductPesimisticImpl {

    private final ProductPesimisticRepository productPesimisticRepository;
    private final RabbitTemplate rabbitTemplate;
    private final AsyncHttpClient client;

    @Transactional
    public void testPesimistic(Long id){

        ProductPesimistic pp = productPesimisticRepository.findById(id).orElseThrow(()->new RuntimeException("Not found"));

        int currStock = pp.getStock();

        pp.setStock(currStock - 1);

        productPesimisticRepository.save(pp);
        log.info("Cek :");

    }

    public void testMessageBroker(Long id){

        rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE, RabbitMqConfig.ORDER_ROUTING_KEY, id);

    }

    public void testCek(Long id){

        rabbitTemplate.convertAndSend(RabbitMqConfig.APP_EXCHANGE ,RabbitMqConfig.PAYMENT_QUEUE , id);
    }
}
