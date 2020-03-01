package com.example.springboot.dataservice.controller;




import com.example.springboot.dataservice.entity.Employee;
import com.example.springboot.dataservice.entity.Message;
import com.example.springboot.dataservice.manager.KafkaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import org.springframework.kafka.core.KafkaTemplate;

@RestController
@RequestMapping("/kafka")

public class RestControllerUserService {

	private static final Logger log = LoggerFactory
			.getLogger(RestControllerUserService.class);

	private static final String TOPIC ="messangerTopic";

	@Autowired

	KafkaManager kafkaManager;

	@GetMapping("/receive/{receiver}")
	public String receive(@PathVariable String receiver) {
		log.info("In Subscribe"+receiver);
		String message="";
		List<String> messageKeyInOrder = new ArrayList<>();
		//kafkaTemplate.send(TOPIC, "This is message to kafka from Spring boot Service");
		Map<String, LinkedHashMap<String, Message>> map = kafkaManager.getMessages();
		LinkedHashMap<String, Message> messagesInOrder = map.get(receiver);
		if(messagesInOrder==null){
			message = "No Message from Anyone.";
			return message;
		}
		else{
			Set<Map.Entry<String,Message>> set = messagesInOrder.entrySet();
			Iterator<Map.Entry<String,Message>> itr= set.iterator();
			while (itr.hasNext())
			{
				Map.Entry<String,Message> entry = itr.next();
				messageKeyInOrder.add(entry.getKey());
			}

			log.info("Total Messages..."+messageKeyInOrder.size());

			Collections.reverse(messageKeyInOrder);
			if(messageKeyInOrder.size()<15) {
				for (int i = 0; i < messageKeyInOrder.size(); i++) {
					message = message + messagesInOrder.get(messageKeyInOrder.get(i)).getSenderName() + " Sent " + messagesInOrder.get(messageKeyInOrder.get(i)).getReceiverName() + " " + messagesInOrder.get(messageKeyInOrder.get(i)).getMessageContent();
					message = message + "\n";

				}
			}else{
				for (int i = 0; i < 15; i++) {
					message = message + messagesInOrder.get(messageKeyInOrder.get(i)).getSenderName() + " Sent " + messagesInOrder.get(messageKeyInOrder.get(i)).getReceiverName() + " " + messagesInOrder.get(messageKeyInOrder.get(i)).getMessageContent();
					message = message + "\n";

				}
			}
		}

		return message;
	}


}
