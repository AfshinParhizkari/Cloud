package com.afshin.shopping.infrastructure.resource;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 07
 * @Time 6:08 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
import com.afshin.shopping.domain.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductRso {
    @Value("${product.find}") private String findPath;
    public List<Product> find(String inputValue) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =new HttpEntity<String>(inputValue, headers);
        Product[] products =restTemplate.postForObject(findPath, request, Product[].class);
        //String productJson =restTemplate.postForObject(findPath, request, String.class);
        return Arrays.asList(products);
    }
}