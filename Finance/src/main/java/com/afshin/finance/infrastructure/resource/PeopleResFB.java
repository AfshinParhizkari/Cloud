package com.afshin.finance.infrastructure.resource;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 08
 * @Time 8:24 AM
 * Created by   Eclipse2021
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: OpenFeign
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.afshin.finance.domain.entity.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PeopleResFB implements PeopleRes{
    public static final Logger logger  = LoggerFactory.getLogger(PeopleResFB.class);

	@Override
	public List<Person> find(Integer inputValue) {
        logger.info("who service Circuit is open");
		List<Person> persons=new ArrayList<Person>();
		persons.add(new Person(0,0,0,"0000000000","People microservice is down","People/who doesn't response. try it later"));
		return persons;
	}
}