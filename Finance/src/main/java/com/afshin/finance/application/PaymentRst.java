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
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController//Application Layer
@RequestMapping("/finance")
public class PaymentRst {
    @Autowired private PaymentSrv srv;

    @PostMapping(value = "/payed")
    public String payInvoice(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer customerCode=json.optInt("customer",0);
        String transaction=json.optString("transaction","");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String defaultData = df.format(new Date());
            String transportDate=json.optString("transport",defaultData);
            Date parsedDate = df.parse(transportDate);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        return srv.payOrder(customerCode,timestamp,transaction);
    }
    @PutMapping(value = "/carry")
    public String carry(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        return srv.updateState(code,"shipped");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }
}