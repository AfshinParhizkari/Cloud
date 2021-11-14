package com.afshin.product.domain.service;

import com.afshin.product.domain.entity.Category;
import com.afshin.product.domain.entity.Product;
import com.afshin.product.infrastructure.repository.CategoryDao;
import com.afshin.product.infrastructure.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 4:29 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
@Service
public class CategorySrv {
    @Autowired private CategoryDao catDao;

    public List<Category> find() throws Exception {
        return catDao.findAll();
    }
    public String delete(Integer code) throws Exception {
        catDao.deleteById(code);
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }
    public String save(Category category) throws Exception {
        if(category.getCategorypk()==null) {//not pk => maybe New person
            return "{'code':1,'message':'record code "+catDao.save(category).getCategorypk()+" is added'}";
        }else {//Exist NationalKey  => Edit Current person
            List<Category> dbcategory = catDao.findByCategorypk(category.getCategorypk());
            if(dbcategory.size()==0) return "{'code':0,'message':'record code "+ dbcategory.get(0).getCategorypk()+" is Not found'}";
            else return "{'code':2,'message':'record code "+updateCategory(category, dbcategory.get(0))+" is updated'}";
        }
    }
    public Integer updateCategory(Category viewcategory, Category dbcategory){
        //update person
        dbcategory.setCategoryname(viewcategory.getCategoryname());
        dbcategory.setCategorydescription(viewcategory.getCategorydescription()); ;
        return catDao.save(dbcategory).getCategorypk();
    }

}
