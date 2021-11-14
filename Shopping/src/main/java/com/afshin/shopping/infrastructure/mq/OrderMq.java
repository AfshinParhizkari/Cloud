package com.afshin.shopping.infrastructure.mq;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 5:52 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import com.afshin.shopping.domain.entity.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderMq {
    @Autowired private TopicExchange exchange;
    @Autowired private AmqpAdmin amqpAdmin;
    @Autowired private RabbitTemplate rabbitTemplate;
    //@JsonView(Chat.PrivateChat.class)
    public Integer sendOrder(List<Cart> carts){
        try {
            Queue queue=new Queue(carts.get(0).getCustomerfk().toString(),true,false,false, AMQPConfig.getArguments());
            amqpAdmin.declareQueue(queue);
            Binding binding= BindingBuilder.bind(queue).to(exchange).with(carts.get(0).getCustomerfk().toString());
            amqpAdmin.declareBinding(binding);

            String cartJson = (new ObjectMapper()).writeValueAsString(carts);
            Message message = MessageBuilder.withBody(cartJson.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();

            rabbitTemplate.send(binding.getExchange(),binding.getRoutingKey(),message);
            return 0;
        }catch (Exception ex){return 1;}
    }
}