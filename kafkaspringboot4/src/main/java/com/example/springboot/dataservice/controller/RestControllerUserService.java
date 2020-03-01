package com.example.springboot.dataservice.controller;




import com.example.springboot.dataservice.entity.Authentication;
import com.example.springboot.dataservice.entity.Employee;
import com.example.springboot.dataservice.entity.Message;
import com.example.springboot.dataservice.manager.KafkaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.kafka.core.KafkaTemplate;

@RestController
@RequestMapping("/kafka")

public class RestControllerUserService {

	private static final Logger log = LoggerFactory
			.getLogger(RestControllerUserService.class);

	private static final String TOPIC ="authenticationTopic";

	@Autowired

	KafkaManager kafkaManager;

	@GetMapping("/fetchLoginInfo")
	public String loggedInUsers() {
		log.info("In fetchLoginInfo");
		String message="";
		DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z") ;



		// Formatting Date according to the
		//kafkaTemplate.send(TOPIC, "This is message to kafka from Spring boot Service");
		Map<String, LinkedHashMap<String, Authentication>> map = kafkaManager.getMessages();

		if(map.size()==0){
			message = "No Logged In User.";
			return message;
		}


		Iterator<Map.Entry<String,LinkedHashMap<String, Authentication>>> itr
				= map.entrySet().iterator();
		while(itr.hasNext())
		{
			List<String> loginInfoInOrder = new ArrayList<>();
			Map.Entry<String,LinkedHashMap<String, Authentication>> entry =  itr.next();
			String username = entry.getKey();
			LinkedHashMap<String, Authentication> loggingInfoList =  entry.getValue();


			Iterator<Map.Entry<String,Authentication>> itr2 =
					loggingInfoList.entrySet().iterator();
			while(itr2.hasNext())
			{
				Map.Entry<String,Authentication> ent =  itr2.next();
				loginInfoInOrder.add(ent.getKey());
			}

			Collections.reverse(loginInfoInOrder);

			Authentication authenticationStatus = loggingInfoList.get(loginInfoInOrder.get(0));
			if(authenticationStatus.isLogin())
			{
				Date result = new Date(authenticationStatus.getTimeinMilliSeconds());
				message = message + authenticationStatus.getName()+" is Logged In from "+ simple.format(result);
				message = message + "\n";
			}
		}

		return message;
	}


}
