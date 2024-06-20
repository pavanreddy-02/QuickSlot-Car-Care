package com.voltmoney.carservicescheduler.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean error, String message, Object responseObj) {
        Map<String, Object> map = new HashMap<>();

            map.put("timestamp", new Date());
            map.put("status", status.value());
            map.put("isSuccess", !error);
            map.put("message", message);
            map.put("data", responseObj);
            return new ResponseEntity<Object>(map,status);
    }
}
