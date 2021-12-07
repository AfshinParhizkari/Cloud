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
import com.afshin.finance.domain.entity.Quantity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ProductResFB implements ProductRes {
    public static final Logger logger  = LoggerFactory.getLogger(ProductResFB.class);

	@Override
	public List<Quantity> getQuantity(List<Integer> inputValue) {
		logger.info("Quantity service Circuit is open");
		List<Quantity> quantities=new ArrayList<Quantity>();
		quantities.add(new Quantity(0,0));
		return quantities;
	}
}
