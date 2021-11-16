package com.afshin.finance.infrastructure.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResorceConfig {
	@Bean public RestTemplate restTemplateBean(){return new RestTemplate();}
	@Autowired DiscoveryClient discoveryClient;
	
	public URI getURI(String instanceName) {
	   	List<ServiceInstance> services =discoveryClient.getInstances(instanceName);
	   	if(services==null || services.size()==0) return null;
	   	else return services.get(0).getUri();//load balance
	}
}
