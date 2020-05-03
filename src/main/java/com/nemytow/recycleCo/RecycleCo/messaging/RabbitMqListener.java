package com.nemytow.recycleCo.RecycleCo.messaging;

import com.google.gson.Gson;
import com.nemytow.recycleCo.RecycleCo.api.messaging.MessagingApi;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RabbitMqListener implements MessageListener {

    @Autowired
    MessagingApi api;

    @SneakyThrows
    @Override
    @RabbitListener(queues="${rabbitmq.pythonQueue}")
    public void onMessage(Message message) {
        String json = new String(message.getBody());
        TrashMessage not = new Gson().fromJson(json, TrashMessage.class);
        log.info("Received a new message..." + json);
        Long idCreated = api.saveTrashFromTheQueue(not);
        if (idCreated==null){
            log.error("Message does not saved");
            return;
        }
        log.info("Trash saved : " + idCreated);
    }
}