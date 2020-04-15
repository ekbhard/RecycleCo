package com.nemytow.recycleCo.RecycleCo.messaging;

import com.google.gson.Gson;
import com.nemytow.recycleCo.RecycleCo.api.messaging.MessagingApi;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqListener {

    @Autowired
    MessagingApi api;

    @RabbitListener(queues="${rabbitmq.pythonQueue}")
    public void listen(byte[] message) {
        String msg = new String(message);
        TrashMessage not = new Gson().fromJson(msg, TrashMessage.class);
        System.out.println("Received a new message..." + not.toString());
        Long idCreated = api.saveTrashFromTheQueue(not);
        if (idCreated==null){
            System.out.println("Message does not saved");
        }
    }
}