package com.afshin.product.infrastructure.mq;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 09
 * @Time 11:08 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import com.afshin.product.domain.entity.Product;
import com.afshin.product.domain.entity.Quantity;
import com.afshin.product.infrastructure.repository.ProductDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class ProductMq {
    @Autowired ProductDao dao;
    @Autowired Queue queue;

    @RabbitListener(queues = "#{queue.getName()}")// Dynamically reading the queue name using SpEL from the "queue" object.
    public void getProductQuantity(final Message message) throws IOException {
        List<Quantity> quantities = (new ObjectMapper()).readValue(message.getBody(), new TypeReference<List<Quantity>>(){});
        for(Quantity quantity:quantities){
            Product product=dao.findByProductpk(quantity.getProductpk()).get(0);
            product.setQuantity(product.getQuantity()-quantity.getQuantity());
            dao.save(product);
        }
    }
}