package com.afshin.finance.infrastructure.mq;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 10 - 12
 * @Time 11:44 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AMQPConfig {
    @Value("${rabbitmq.exchange}") String exchangeName;
    @Value("${product.queue}") String queueName;
    @Value("${product.routingkey}") String routingKey;
    @Autowired private AmqpAdmin amqpAdmin;


    @Bean public TopicExchange getExchange(){return new TopicExchange(exchangeName,Boolean.TRUE,Boolean.FALSE);}
   /* @Bean Queue queue() {return new Queue(queueName);}*/

    @Bean public Binding getBinding(){
        Queue queue=new Queue(queueName,true,false,false, getArguments());
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(getExchange()).with(routingKey);
        amqpAdmin.declareBinding(binding);
        return binding;
    }
    public Map<String, Object> getArguments() {
        Map<String, Object> arguments=new HashMap<>();
        arguments.put("x-message-ttl",360000);//queue remove: 100*60*60*24*3=3Days
        arguments.put("x-expires", 60480000);//idle Queue : 100*60*60*24*7=1Week
        arguments.put("x-max-length", 1000);//message
        arguments.put("x-max-length-bytes", 3145728);//1024*1024*3=3MByte
        arguments.put("x-queue-mode", "lazy");//Saved message on HDD
        return arguments;
    }
}