package com.afshin.product.application;

import com.afshin.product.domain.entity.Product;
import com.afshin.product.domain.entity.Quantity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 11
 * @Time 3:14 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
public class Utility {

    public static List<Quantity> Product2Quantity(List<Product> products){
        try{
            List<Quantity> quantities = new ArrayList<>();
            for(Product product:products)
                quantities.add(new Quantity(product.getProductpk(),product.getQuantity()));
            return quantities;
        }catch (Exception e) {return null;}
    }
}
