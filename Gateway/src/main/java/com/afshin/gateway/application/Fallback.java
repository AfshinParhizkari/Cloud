package com.afshin.gateway.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.afshin.gateway.domain.entity.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/fb")
public class Fallback {

	public static final Logger logger  = LoggerFactory.getLogger(Fallback.class);

	@PostMapping(value = "/who")
	public List<Person> whoFB(String inputValue) {
        logger.info("who service Circuit is open");
		List<Person> persons=new ArrayList<Person>();
		persons.add(new Person(0,0,0,"0000000000","People microservice is down","People/who doesn't response. try it later"));
		return persons;
	}
    
    @PostMapping(value = "/showproduct")
	private ResponseEntity<String> productFB(){
    	logger.info("show product Circuit is open");
    	return new ResponseEntity<String>(
    			"[{\"productpk\":0,\"productname\":\"Product microservice is down\",\"categoryfk\":0,\"vendor\":\"AfshinParhizkari\",\"quantity\":0,\"unit\":\"Error\",\"saleprice\":0,\"description\":\"Product/showproduct doesn't work now. try it later\"}}]",
    			HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping(value = "/")
	private ResponseEntity<String> fallback(){
    	logger.info("Circuit is open");
    	return new ResponseEntity<String>(
    			"[{\"Code\":1,\"message\":\"microservice is down and doesn't work now. try it later\"}}]",
    			HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
