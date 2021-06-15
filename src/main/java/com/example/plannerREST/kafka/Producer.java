package com.example.plannerREST.kafka;

import com.example.plannerREST.entities.AuthRequest;
import com.example.plannerREST.entities.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    public static final String TOPIC = "Kafka_Example";

    @Autowired
    private KafkaTemplate<String, Permission> kafkaTemplate;

    public void publishMessage(Permission permission) {

        System.out.println("Publishing to topic " + TOPIC);
        kafkaTemplate.send(TOPIC, permission);
    }
}
