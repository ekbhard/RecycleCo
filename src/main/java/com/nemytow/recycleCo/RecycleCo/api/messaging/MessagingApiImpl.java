package com.nemytow.recycleCo.RecycleCo.api.messaging;

import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.domain.TrashType;
import com.nemytow.recycleCo.RecycleCo.messaging.AMQPProducer;
import com.nemytow.recycleCo.RecycleCo.messaging.TrashMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Log4j2
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class MessagingApiImpl implements MessagingApi {

    @Autowired
    AMQPProducer producer;

    @Autowired
    AccountApi accountApi;

    @Override
    public void sendMessage(){
        producer.sendMessage(TrashMessage.builder()
                .beanId(1L)
                .trashId(1L)
                .type("battery")
                .userId(accountApi.getCurrentUser().getId())
                .right(true)
                .build());
    }

}
