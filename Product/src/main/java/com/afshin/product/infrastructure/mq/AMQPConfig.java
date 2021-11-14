package com.afshin.product.infrastructure.mq;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {
    @Value("${product.queue}") String queueName;
    @Bean Queue queue(){return new Queue(queueName);}
}