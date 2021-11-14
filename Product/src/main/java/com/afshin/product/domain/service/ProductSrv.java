package com.afshin.product.domain.service;

import com.afshin.product.application.Utility;
import com.afshin.product.domain.entity.Category;
import com.afshin.product.domain.entity.Product;
import com.afshin.product.domain.entity.Quantity;
import com.afshin.product.infrastructure.repository.CategoryDao;
import com.afshin.product.infrastructure.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 11 - 02
 * @Time 3:53 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Business Logic
 */
@Service//Logic
public class ProductSrv {
    @Autowired private ProductDao proDao;
    @Autowired private CategoryDao catDao;

    public List<Product> find(Integer code, Integer pageNum) throws Exception {
        List<Product> returnData;
        Pageable somedata =  PageRequest.of(pageNum, 10, Sort.by("productpk").descending());
        if(code==0) returnData = (proDao.findByActive(somedata,true)).getContent();
        else returnData=proDao.findByProductpk(code);
        return returnData;
    }

    public List<Quantity> getQuantity(List<Integer> productKeys) throws Exception {
        return Utility.Product2Quantity(proDao.findByProductpkIn(productKeys));
    }

    public String delete(Integer code) throws Exception {
        proDao.deleteById(code);
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }

    public String save(Product viewProduct) throws Exception {
        if(viewProduct.getProductpk()==null) {//not pk => maybe New person
                return "{'code':1,'message':'record code "+proDao.save(viewProduct).getProductpk()+" is added'}";
        }else {//Exist NationalKey  => Edit Current person
            List<Product> dbProduct = proDao.findByProductpk(viewProduct.getProductpk());
            if(dbProduct.size()==0) return "{'code':0,'message':'record code "+ viewProduct.getProductpk()+" is Not found'}";
            else return "{'code':2,'message':'record code "+updateProduct(viewProduct, dbProduct.get(0))+" is updated'}";
        }
    }
    public Integer updateProduct(Product viewProduct, Product dbProduct){
        //update person
        dbProduct.setProductname(viewProduct.getProductname());
        dbProduct.setCategoryfk(viewProduct.getCategoryfk()) ;
        dbProduct.setVendor(viewProduct.getVendor());
        dbProduct.setQuantity(viewProduct.getQuantity());
        dbProduct.setUnit(viewProduct.getUnit());
        dbProduct.setBuyprice(viewProduct.getBuyprice());
        dbProduct.setSaleprice(viewProduct.getSaleprice());
        dbProduct.setActive(viewProduct.getActive());
        dbProduct.setDescription(viewProduct.getDescription());
        return proDao.save(dbProduct).getProductpk();
    }
}
