package com.afshin.product.application;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 09 - 25
 * @Time 11:43 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Application Layer
 */

import com.afshin.product.domain.entity.Product;
import com.afshin.product.domain.service.ProductSrv;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController//Application Layer
@RequestMapping("/product")
public class ProductRst {
    @Autowired private ProductSrv srv;

    @PostMapping(value = "/find")
    public String find(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        Integer page=json.optInt("page",0);
        return (new ObjectMapper()).writeValueAsString(srv.find(code,page));
    }

    @PostMapping(value = "/quantity")
    public String getQuantity(@RequestBody List<Integer> productKeys) throws Exception {
        return (new ObjectMapper()).writeValueAsString(srv.getQuantity(productKeys));
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        return srv.delete(code);
    }

    @PutMapping(value = "/save")
    public String save(@Valid @RequestBody Product obj) throws Exception {
        return srv.save(obj);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }
}