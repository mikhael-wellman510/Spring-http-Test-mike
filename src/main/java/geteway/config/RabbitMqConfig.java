package geteway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMqConfig {

//    @Value("${spring.rabbitmq.queue}")
//    private String queueName;
//
//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
    public static final String QUEUE_NAME = "exampleQueue";

    @Bean
    public Queue queue(){
        log.info("Queue : {} " , QUEUE_NAME);
        return new Queue(QUEUE_NAME,false);
    }

}
