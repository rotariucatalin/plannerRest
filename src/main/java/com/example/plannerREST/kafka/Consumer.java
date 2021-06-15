package com.example.plannerREST.kafka;

import com.example.plannerREST.dto.PermissionDTO;
import com.example.plannerREST.entities.AuthRequest;
import com.example.plannerREST.entities.Permission;
import com.example.plannerREST.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private PermissionService permissionService;

    @KafkaListener(topics="Kafka_Example", groupId = "mygroup", containerFactory = "kafkaListenerAuthRequestFactory")
    public void consumeFromTopic(@Payload Permission permission) {

        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setName(permission.getName());
        //permissionService.savePermission(permissionDTO);

    }

}
