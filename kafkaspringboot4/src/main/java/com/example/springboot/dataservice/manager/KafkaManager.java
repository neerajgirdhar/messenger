package com.example.springboot.dataservice.manager;

import com.example.springboot.dataservice.entity.Authentication;
import com.example.springboot.dataservice.entity.Employee;
import com.example.springboot.dataservice.entity.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class KafkaManager {

    Map<String, LinkedHashMap<String, Authentication>> map = new HashMap<>();

    @KafkaListener(topics = "authenticationTopic", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Authentication authentication) {
        System.out.println("Consumed JSON Message: " + authentication);

        if (map.size() == 0) {
            LinkedHashMap<String, Authentication> messagesInorder = new LinkedHashMap<>();
            messagesInorder.put("" + authentication.getTimeinMilliSeconds(), authentication);
            this.map.put(authentication.getName(), messagesInorder);
        } else {

            if (null == map.get(authentication.getName())) {
                LinkedHashMap<String, Authentication> messagesInorder = new LinkedHashMap<>();
                messagesInorder.put("" + authentication.getTimeinMilliSeconds(), authentication);
                this.map.put(authentication.getName(), messagesInorder);
            } else {
                map.get(authentication.getName()).put("" + authentication.getTimeinMilliSeconds(), authentication);
            }

        }


    }



    public Map<String, LinkedHashMap<String, Authentication>> getMessages()
    {

        return this.map;
    }
}
