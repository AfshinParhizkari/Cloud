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

import com.afshin.shopping.domain.entity.Person;
import com.afshin.shopping.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class PeopleRso {
	@Autowired RestTemplate restTemplate;
	@Autowired ResorceConfig resoConfig;
    @Value("${people.who}") private String serviceName;
    public Person find(String inputValue) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =new HttpEntity<String>(inputValue, headers);
        Person[] persons =restTemplate.postForObject(resoConfig.getURI("people")+serviceName, request, Person[].class);
        //String productJson =restTemplate.postForObject(findPath, request, String.class);
        return Arrays.asList(persons).get(0);
    }
}