package com.afshin.shopping.application;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 09 - 25
 * @Time 11:43 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Application Layer
 */

import com.afshin.shopping.domain.entity.Cart;
import com.afshin.shopping.domain.service.CartSrv;
import com.afshin.shopping.infrastructure.resource.PeopleRso;
import com.afshin.shopping.infrastructure.resource.ProductRso;
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

@RestController//Application Layer
@RequestMapping("/shopping")
public class CartRst {
    @Autowired private CartSrv srv;
    @Autowired private ProductRso productRso;
    @Autowired private PeopleRso peopleRso;

    @Operation(summary = "return a customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Who am i?",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "get customer",
    	    								value = "{\n"
    	    										+ "  \"code\":2\n"
    	    										+ "}",
    								summary = "who") }))
    @PostMapping(value = "/who")
    public ResponseEntity<String> whoami(@RequestBody String receivedData) throws Exception {
        String response=(new ObjectMapper()).writeValueAsString(peopleRso.find(receivedData));
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @Operation(summary = "return shopping list")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "cart of the customer#",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "get cart for this customers",
    	    								value = "{\n"
    	    										+ "  \"code\":2\n"
    	    										+ "}",
    								summary = "shopping list") }))
    @PostMapping(value = "/showcart")
    public ResponseEntity<String> find() throws Exception {
        String response=(new ObjectMapper()).writeValueAsString(srv.showCart());
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @Operation(summary = "Delete a product from basket")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Delete a product from cart",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "Delete product#",
    	    								value = "{\n"
    	    										+ "  \"customercode\":2,\n"
    	    										+ "  \"productcode\":29\n"
    	    										+ "}",
    								summary = "delete product# 29") }))
    @DeleteMapping(value = "/deletefromcart")
    public ResponseEntity<String> delete(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer customerCode=json.optInt("customercode",0);
        Integer productCode=json.optInt("productcode",0);
        return new ResponseEntity<String>(srv.deleteFromCart(customerCode,productCode),HttpStatus.OK);
    }
    
    @Operation(summary = "Shopping cancellation")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "cancel all products from cart",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "cancel cart#",
    	    								value = "{\n"
    	    										+ "  \"code\":3\n"
    	    										+ "}",
    								summary = "cancel customer# 29") }))

    @DeleteMapping(value = "/cancelcart")
    public ResponseEntity<String> cancel(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        return new ResponseEntity<String>(srv.cancelCart(code),HttpStatus.OK);
    }

    @Operation(summary = "Add a product to basket")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Examples for order a product",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "add product",
    								value = "{\n"
    										+ "  \"customerfk\": 2,\n"
    										+ "  \"productfk\": 28,\n"
    										+ "  \"quantity\": 0,\n"
    										+ "  \"price\": 20.20\n"
    										+ "}",
    								summary = "add product to cart") }))
    @Schema(implementation = Cart.class)
    @PutMapping(value = "/addtocart")
    public ResponseEntity<String> save(@Valid @RequestBody Cart obj) throws Exception {
        return new ResponseEntity<String>(srv.addToCart(obj),HttpStatus.OK);
    }

    
    @Operation(summary = "Finalize cart for payment")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "send order to payment app",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "save order#",
    	    								value = "{\n"
    	    										+ "  \"code\":2\n"
    	    										+ "}",
    								summary = "save for customer# 29") }))
    @PostMapping(value = "/closecart")
    public ResponseEntity<String> close(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer customerCode=json.optInt("code",0);
        return new ResponseEntity<String>(srv.closeCart(customerCode),HttpStatus.OK);
    }

    @Operation(summary = "show Products for buy")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Examples for get Products",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "All Products according to page number",
    								value = "{\n"
    										+ "  \"code\":null,\n"
    										+ "  \"page\":null\n"
    										+ "}",
    								summary = "All Products") }))
    @PostMapping(value = "/showproduct")
    public ResponseEntity<String> showProduct(@RequestBody(required = false) String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        Integer page=json.optInt("page",0);
        String inputValue="{'code':"+code+",'page':"+page+"}";
        String response=(new ObjectMapper()).writeValueAsString(productRso.find(inputValue));
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }
}