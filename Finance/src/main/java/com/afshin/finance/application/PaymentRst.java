package com.afshin.finance.application;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 09 - 25
 * @Time 11:43 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Application Layer
 */

import com.afshin.finance.domain.service.PaymentSrv;
import com.afshin.finance.infrastructure.resource.PeopleRes;
import com.afshin.finance.infrastructure.resource.ProductRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController//Application Layer
@RequestMapping("/finance")
public class PaymentRst {
    @Autowired private PaymentSrv srv;
    @Autowired private ProductRes productRes;
    @Autowired private PeopleRes peopleRes;

    @Operation(summary = "pay Invoice")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Pay finalized order",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "Pay sample order",
    								value = "{\n"
    										+ "  \"customer\":2,\n"
    										+ "  \"transaction\":\"sb125954c\",\n"
    										+ "  \"transport\": \"2021-11-11 14:30:50\"\n"
    										+ "}",
    								summary = "pay") }))
    @PostMapping(value = "/payed")
    @CircuitBreaker(name="quantity",fallbackMethod = "quantityFB")
    public ResponseEntity<String> payInvoice(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer customerCode=json.optInt("customer",0);
        String transaction=json.optString("transaction","");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String defaultData = df.format(new Date());
            String transportDate=json.optString("transport",defaultData);
            Date parsedDate = df.parse(transportDate);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        String response=srv.payOrder(customerCode,timestamp,transaction);
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @Operation(summary = "Carry payed order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Shipping",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "carry selected product",
    								value = "{\n"
    										+ "\"code\":10447\n"
    										+ "}",
    								summary = "carry order num 10447") }))
    @PutMapping(value = "/carry")
    public ResponseEntity<String> carry(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        return new ResponseEntity<String>(srv.updateState(code,"shipped"),HttpStatus.OK);
    }
    
    @Operation(summary = "return quantity of Products")
    @PostMapping(value = "/showquantity")
    //@CircuitBreaker(name="quantity",fallbackMethod = "quantityFB")
    public ResponseEntity<String> showQuantity() throws Exception {
        List<Integer> productKeys= new ArrayList<Integer>();
        productKeys.add(1);
        productKeys.add(2);
        productKeys.add(3);
        productKeys.add(4);
        String response=(new ObjectMapper()).writeValueAsString(productRes.getQuantity(productKeys));
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

	@SuppressWarnings("unused")
	private ResponseEntity<String> quantityFB(Exception ex){
        System.out.println("Quantity Circuit is open:"+getdetailMessage(ex));
    	return new ResponseEntity<String>(
    			"[{\"productpk\":0,\"quantity\":0}]",
    			HttpStatus.INTERNAL_SERVER_ERROR);
    }

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
    	String response=(new ObjectMapper()).writeValueAsString(peopleRes.find(receivedData));
    	return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }
	private static String getdetailMessage(Exception ex) {
	    String result = ex.getMessage();
	    Throwable throwable=ex.getCause();
	    while (throwable != null) {
	       result=ex.getCause().getMessage();
	       throwable = throwable.getCause();
	    }
	    return result;
	}

}