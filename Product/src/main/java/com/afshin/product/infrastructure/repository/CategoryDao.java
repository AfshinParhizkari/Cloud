package com.afshin.product.infrastructure.repository;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date Apr 26, 2021 2:58:19 AM
 * @version
 * Created by Eclipse 2020-09
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: 
*/

import com.afshin.product.domain.entity.Category;
import com.afshin.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer>{
    List<Category> findByCategorypk(Integer categorycode);

}