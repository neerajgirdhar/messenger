package com.example.springboot.dataservice.controller;




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

	private static final String TOPIC ="messangerTopic";

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;
	//private KafkaTemplate<String, String> kafkaTemplate;


	@PostMapping(path ="/send", consumes="application/json"   ,produces = "application/json" )
	public String post(@RequestBody Message message) {
		log.info("Message"+message.toString());
		//kafkaTemplate.send(TOPIC, "This is message to kafka from Spring boot Service");
		message.setTimeinMilliSeconds(System.currentTimeMillis());
			kafkaTemplate.send(TOPIC, message);


		return "Successfully Published  On Kafka Topic ";
	}



}
