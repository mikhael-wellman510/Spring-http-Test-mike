package geteway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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

    // Todo -> Declare Topic
    public static final String APP_EXCHANGE = "app.topic.exchange";

    // Todo -> Routing keys
    public static final String BROADCAST_ROUTING_KEY = "broadcast";
    public static final String BROADCAST_MESSAGE_KEY = "broadcast_message";
    public static final String ORDER_ROUTING_KEY = "order";
    public static final String TESTING_ROUTING_KEY = "testing";
    public static final String EMAIL_ROUTING_KEY =  "email";
    public static final String SPORTY_ROUTING_KEY = "sporty";
    public static final String SPORTY_SERVICE1_ROUTING_KEY = "service1";
    public static final String SPORTY_SERVICE2_ROUTING_KEY = "service2";
    public static final String SPORTY_SERVICE3_ROUTING_KEY = "service3";

    // Todo -> Queue Names
    public static final String BROADCAST_QUEUE = "broadcast.queue";
    public static final String BROADCAST_MESSAGE_QUEUE = "broadcast.message.queue";
    public static final String ORDER_QUEUE = "order.queue";
    public static final String PAYMENT_QUEUE = "payment.queue";
    public static final String TESTING_QUEUE = "testing.queue";
    public static final String EMAIL_QUEUE = "email.queue";
    public static final String SPORTY_QUEUE = "sporty.queue";
    public static final String SPORTY_SERVICE1_QUEUE = "service1.queue";
    public static final String SPORTY_SERVICE2_QUEUE = "service2.queue";
    public static final String SPORTY_SERVICE3_QUEUE = "service3.queue";



    //Todo -> Declare Topic Exchange
    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(APP_EXCHANGE);
    }


    //Todo -> Declare Queue
    @Bean
    public Queue emailQueue(){
        return new Queue(EMAIL_QUEUE,true);
    }

    @Bean
    public Queue broadcastQueue(){
        return new Queue(BROADCAST_QUEUE ,true);
    }

    @Bean Queue broadcastMessageQueue(){
        return new Queue(BROADCAST_MESSAGE_QUEUE,true);
    }
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true); // durable
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE, true); // durable
    }

    @Bean
    public Queue testingQueue(){
        return new Queue(TESTING_QUEUE ,true);
    }

    @Bean
    public Queue sportyQueue(){
        return new Queue(SPORTY_QUEUE,true);
    }

    @Bean
    public Queue sportyService1Queue(){
        return new Queue(SPORTY_SERVICE1_QUEUE,true);
    }
    @Bean
    public Queue sportyService2Queue(){
        return new Queue(SPORTY_SERVICE2_QUEUE,true);
    }
    @Bean
    public Queue sportyService3Queue(){
        return new Queue(SPORTY_SERVICE3_QUEUE,true);
    }



    //Todo-> Binding: hubungkan queue ke exchange dengan routing key
    @Bean
    public Binding emailBinding(Queue emailQueue , TopicExchange appExchange){
       return BindingBuilder.bind(emailQueue).to(appExchange).with(EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding broadcastBinding(Queue broadcastQueue , TopicExchange appExchange){
        return BindingBuilder.bind(broadcastQueue).to(appExchange).with(BROADCAST_ROUTING_KEY);
    }

    @Bean
    public Binding broadcastMessageBinding(Queue broadcastMessageQueue , TopicExchange appExchange){
        return BindingBuilder.bind(broadcastMessageQueue).to(appExchange).with(BROADCAST_MESSAGE_KEY);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange appExchange) {
        return BindingBuilder.bind(orderQueue).to(appExchange).with(ORDER_ROUTING_KEY);
    }

    @Bean
    public Binding testingBinding(Queue testingQueue, TopicExchange appExchange){
        return BindingBuilder.bind(testingQueue).to(appExchange).with(TESTING_ROUTING_KEY);
    }

    @Bean
    public Binding sportyBinding(Queue sportyQueue , TopicExchange appExchange){
        return BindingBuilder.bind(sportyQueue).to(appExchange).with(SPORTY_ROUTING_KEY);
    }

    @Bean
    public Binding sportyService1Binding(Queue sportyService1Queue , TopicExchange appExchange){
        return BindingBuilder.bind(sportyService1Queue).to(appExchange).with(SPORTY_SERVICE1_ROUTING_KEY);
    }

    @Bean
    public Binding sportyService2Binding(Queue sportyService2Queue , TopicExchange appExchange){
        return BindingBuilder.bind(sportyService2Queue).to(appExchange).with(SPORTY_SERVICE2_ROUTING_KEY);
    }

    @Bean
    public Binding sportyService3Binding(Queue sportyService3Queue , TopicExchange appExchange){
        return BindingBuilder.bind(sportyService3Queue).to(appExchange).with(SPORTY_SERVICE3_ROUTING_KEY);
    }




    //Todo -> Untuk auto convert ke JSON , Message converter untuk JSON
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate dengan converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter());
        return template;
    }

    // Todo -> supaya listener yg menerima auto di convert dari Json ke Java Object
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

}
