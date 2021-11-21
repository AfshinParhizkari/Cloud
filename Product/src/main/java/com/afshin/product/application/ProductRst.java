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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController//Application Layer
@RequestMapping("/product")
public class ProductRst {
    @Autowired private ProductSrv srv;

    @Operation(summary = "return a Product or Products")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Examples for get Product(s)",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "All Products according to page number",
    								value = "{\n"
    										+ "  \"code\":null,\n"
    										+ "  \"page\":0\n"
    										+ "}",
    								summary = "All Products"),
    						@ExampleObject(
    								name = "Just Product code 2",
    								value = "{\n"
    										+ "  \"code\":2,\n"
    										+ "  \"page\":0\n"
    										+ "}",
    								summary = "One Product") }))
    @PostMapping(value = "/find")
    public ResponseEntity<String> find(@RequestBody String receivedData) throws Exception {
        System.err.println("Attention: Some App call this Instance");
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        Integer page=json.optInt("page",0);
        String response=(new ObjectMapper()).writeValueAsString(srv.find(code,page));
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @Operation(summary = "return quantity of Products")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Examples for get Quantity",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "List of product code",
    								value = "[2,3,4,5]",
    								summary = "quantities") }))
    @PostMapping(value = "/quantity")
    public ResponseEntity<String> getQuantity(@RequestBody List<Integer> productKeys) throws Exception {
        System.err.println("Attention: Some App call this Instance");
        String response=(new ObjectMapper()).writeValueAsString(srv.getQuantity(productKeys));
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @Operation(summary = "Delete a Product")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Delete",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "Delete Product",
    	    								value = "{\n"
    	    										+ "    \"code\":38\n"
    	    										+ "}",
    								summary = "delete Pro# 38") }))
    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        return new ResponseEntity<String>(srv.delete(code),HttpStatus.OK);
    }

    @Operation(summary = "Save a Product")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Examples for Save Product",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "Save a Product",
    								value = "{\n"
    										+ "  \"productpk\": null,\n"
    										+ "  \"productname\": \"New 3D Samplea\",\n"
    										+ "  \"categoryfk\": 3,\n"
    										+ "  \"vendor\": \"Afshin Parhizkari Code Gen\",\n"
    										+ "  \"quantity\": 1,\n"
    										+ "  \"unit\": \"Kmeter\",\n"
    										+ "  \"buyprice\": 10.00,\n"
    										+ "  \"saleprice\": 20.30,\n"
    										+ "  \"active\": true,\n"
    										+ "  \"description\": \"This code generate for working with microservice tools.\"\n"
    										+ "}",
    								summary = "Save Product") }))

    @Schema(implementation = Product.class)
    @PutMapping(value = "/save")
    public ResponseEntity<String> save(@Valid @RequestBody Product obj) throws Exception {
        return new ResponseEntity<String>(srv.save(obj),HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }
}