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
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import com.afshin.finance.domain.entity.Quantity;

@FeignClient(name="${product.app}",fallback = ProductResFB.class)
public interface ProductRes {
	@PostMapping(value = "${product.service}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Quantity> getQuantity(List<Integer> inputValue);
}
