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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Arrays;

@Service
public class PeopleRso {
	@Autowired RestTemplate restTemplate;
	@Autowired ResorceConfig resoConfig;
    @Value("${people.who}") private String serviceName;
    
    public Person find(Integer customerCode) throws IOException {
        //Person[] persons =restTemplate.geForObject(resoConfig.getURI("people")+serviceName, request, Person[].class);
        Person[] persons =restTemplate.getForObject(serviceName+"/"+customerCode, Person[].class);

        //String productJson =restTemplate.postForObject(findPath, request, String.class);
        return Arrays.asList(persons).get(0);
    }
}