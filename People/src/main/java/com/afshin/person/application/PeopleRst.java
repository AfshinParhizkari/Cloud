package com.afshin.person.application;
/**
 * @Project DDD
 * @Author Afshin Parhizkari
 * @Date 2021 - 09 - 25
 * @Time 11:43 PM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Application Layer
 */

import com.afshin.person.domain.entity.Person;
import com.afshin.person.domain.service.PersonSrv;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController//Application Layer
@RequestMapping("/people")
public class PeopleRst {
    @Autowired private PersonSrv srv;
    public static final Logger logger  = LoggerFactory.getLogger(PeopleRst.class);

    @Operation(summary = "return a customer or all customers")
    @Parameter(name = "customerCode",description = "Integer identifier", example = "3")
    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> findCustomer(@RequestParam(defaultValue = "0") Integer customerCode) throws Exception {
        return new ResponseEntity<String>((new ObjectMapper()).writeValueAsString(srv.find(customerCode)),HttpStatus.OK);
    }
  
    @Operation(summary = "return a customer")
    @Parameter(name = "customerCode",description = "Integer identifier", example = "3")
    @GetMapping(value = "/who/{customerCode}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Person.PersonLight.class)
    public ResponseEntity<String> whoami(@PathVariable Integer customerCode) throws Exception {
    	logger.info("Attention: Some App call this Instance");
        return new ResponseEntity<String>((new ObjectMapper()).writeValueAsString(srv.find(customerCode)),HttpStatus.OK);

    }
    
    @Operation(summary = "Delete a Customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Delete",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "Delete customer",
    	    								value = "{\n"
    	    										+ "  \"code\":465\n"
    	    										+ "}",
    								summary = "delete cus# 465") }))
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        return new ResponseEntity<String>(srv.delete(code),HttpStatus.OK);
    }

    @Operation(summary = "Add a Customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    		description = "Examples for Save customer",
    		required = true,
    		content = @io.swagger.v3.oas.annotations.media.Content (
    				mediaType = MediaType.APPLICATION_JSON_VALUE,
    				examples = {
    						@ExampleObject(
    								name = "Save a Customer",
    								value = "{\n"
    										+ "    \"personpk\": null,\n"
    										+ "    \"persontypeid\": 1,\n"
    										+ "    \"typedetailid\": 1,\n"
    										+ "    \"nationalkey\": \"0042010955\",\n"
    										+ "    \"booknumber\": 2783,\n"
    										+ "    \"bookserial\": \"A/93/547643\",\n"
    										+ "    \"bookserie\": \"1334557\",\n"
    										+ "    \"passportno\": \"5432401DH\",\n"
    										+ "    \"lastname\": \"Parhizkari\",\n"
    										+ "    \"firstname\": \"Afshin\",\n"
    										+ "    \"countryid\": 98,\n"
    										+ "    \"cityid\": null,\n"
    										+ "    \"birthdate\": \"1978-12-06\",\n"
    										+ "    \"changerdate\": \"2021-09-30\",\n"
    										+ "    \"contactsByPersonpk\": [\n"
    										+ "      {\n"
    										+ "        \"contactpk\": null,\n"
    										+ "        \"personfk\": null,\n"
    										+ "        \"ownernationalkey\": \"\",\n"
    										+ "        \"contacttypeid\": 1,\n"
    										+ "        \"countryid\": 98,\n"
    										+ "        \"provinceid\": 90,\n"
    										+ "        \"cityid\": 90,\n"
    										+ "        \"contactnumber\": \"+989032430637\",\n"
    										+ "        \"contactdesc\": \"Par St,Tehran,Iran\",\n"
    										+ "        \"email\": \"Afshin.Parhizkari@gmail.com\",\n"
    										+ "        \"location\": null,\n"
    										+ "        \"contactstatus\": true,\n"
    										+ "        \"changerdate\": \"2021-08-25\"\n"
    										+ "      }\n"
    										+ "    ]\n"
    										+ "  }",
    								summary = "Save Customer") }))
    @Schema(implementation = Person.class)
    @PutMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> save(@Valid @RequestBody Person viewPerson) throws Exception {
        return new ResponseEntity<String>(srv.save(viewPerson),HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }

}