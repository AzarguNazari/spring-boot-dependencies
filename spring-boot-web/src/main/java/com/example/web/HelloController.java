package com.example.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class HelloController {
	
    @RequestMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public String hello() {
		return "Hello World";
	}

}