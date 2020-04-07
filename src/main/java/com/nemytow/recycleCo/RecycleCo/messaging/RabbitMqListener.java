package com.nemytow.recycleCo.RecycleCo.messaging;

import com.google.gson.Gson;
import com.nemytow.recycleCo.RecycleCo.api.messaging.MessagingApi;
import com.nemytow.recycleCo.RecycleCo.dto.OperationResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    @Autowired
    MessagingApi api;

    @RabbitListener(queues="${rabbitmq.queueName}")
    public void listen(byte[] message) {
        String msg = new String(message);
        TrashMessage not = new Gson().fromJson(msg, TrashMessage.class);
        System.out.println("Received a new message..." + not.toString());
        Long idCreated = api.saveTrashFromTheQueue(not);
        OperationResponse resp = new OperationResponse();
        if (idCreated != null){
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("New trash saved : " + idCreated);
        }
        else{
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.ERROR);
            resp.setOperationMessage("Unable to add trash");
        }
    }
}