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

@Component
public class ProductResFB implements ProductRes {

	@Override
	public List<Quantity> getQuantity(List<Integer> inputValue) {
        System.out.println("Quantity service Circuit is open");
		List<Quantity> quantities=new ArrayList<Quantity>();
		quantities.add(new Quantity(0,0));
		return quantities;
	}
}
