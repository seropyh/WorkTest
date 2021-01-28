package com.example.test.controllers;

import com.example.test.model.ServiceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {
    @RequestMapping(value = "/restusercntrl", method = RequestMethod.POST)
    public String request(@RequestBody String request) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var response = objectMapper.readValue(request, ServiceRequest.class);

        return response.toString();
    }
}
