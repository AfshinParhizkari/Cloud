package com.afshin.finance.domain.entity;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 3:27 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Value Object
 */
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quantity {
    private Integer productpk;
    private Integer quantity;
}
