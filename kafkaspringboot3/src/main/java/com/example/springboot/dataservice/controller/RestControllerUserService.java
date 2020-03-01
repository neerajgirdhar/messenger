package com.example.springboot.dataservice.controller;




import com.example.springboot.dataservice.entity.Authentication;
import com.example.springboot.dataservice.entity.Employee;
import com.example.springboot.dataservice.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
@RequestMapping("/kafka")

public class RestControllerUserService {

	private static final Logger log = LoggerFactory
			.getLogger(RestControllerUserService.class);

	private static final String TOPIC ="authenticationTopic";

	@Autowired
	private KafkaTemplate<String, Authentication> kafkaTemplate;
	//private KafkaTemplate<String, String> kafkaTemplate;


	@PostMapping(path ="/login", consumes="application/json"   ,produces = "application/json" )
	public String login(@RequestBody Authentication authentication) {
		log.info("authentication"+authentication.toString());
		//kafkaTemplate.send(TOPIC, "This is message to kafka from Spring boot Service");
		authentication.setLogin(true);
		authentication.setLogoff(false);
		authentication.setTimeinMilliSeconds(System.currentTimeMillis());
			kafkaTemplate.send(TOPIC, authentication);


		return "Successfully Published  login Information  Kafka Topic ";
	}


	@PostMapping(path ="/logOff", consumes="application/json"   ,produces = "application/json" )
	public String logOff(@RequestBody Authentication authentication) {
		log.info("authentication"+authentication.toString());
		//kafkaTemplate.send(TOPIC, "This is message to kafka from Spring boot Service");
		authentication.setLogin(false);
		authentication.setLogoff(true);
		authentication.setTimeinMilliSeconds(System.currentTimeMillis());
		kafkaTemplate.send(TOPIC, authentication);


		return "Successfully Published  logOff Information  Kafka Topic ";
	}


}
