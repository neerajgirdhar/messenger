package com.example.springboot.dataservice.manager;

import com.example.springboot.dataservice.entity.Employee;
import com.example.springboot.dataservice.entity.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class KafkaManager {

    Map<String, LinkedHashMap<String, Message>> map = new HashMap<>();

    @KafkaListener(topics = "messangerTopic", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Message message) {
        System.out.println("Consumed JSON Message: " + message);

        if (map.size() == 0) {
            LinkedHashMap<String, Message> messagesInorder = new LinkedHashMap<>();
            messagesInorder.put("" + message.getTimeinMilliSeconds(), message);
            this.map.put(message.getReceiverName(), messagesInorder);
        } else {

            if (null == map.get(message.getReceiverName())) {
                LinkedHashMap<String, Message> messagesInorder = new LinkedHashMap<>();
                messagesInorder.put("" + message.getTimeinMilliSeconds(), message);
                this.map.put(message.getReceiverName(), messagesInorder);
            } else {
                map.get(message.getReceiverName()).put("" + message.getTimeinMilliSeconds(), message);
            }

        }


    }



    public Map<String, LinkedHashMap<String, Message>> getMessages()
    {

        return this.map;
    }
}
