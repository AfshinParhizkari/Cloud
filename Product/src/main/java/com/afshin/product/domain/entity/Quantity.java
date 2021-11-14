package com.afshin.product.domain.entity;
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
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quantity implements Serializable {
    private Integer productpk;
    private Integer quantity;
}
