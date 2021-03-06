package com.nemytow.recycleCo.RecycleCo.api.messaging;

import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.domain.Trash;
import com.nemytow.recycleCo.RecycleCo.domain.TrashType;
import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.messaging.AMQPProducer;
import com.nemytow.recycleCo.RecycleCo.messaging.PythonMessage;
import com.nemytow.recycleCo.RecycleCo.messaging.TrashMessage;
import com.nemytow.recycleCo.RecycleCo.repository.TrashRepository;
import com.nemytow.recycleCo.RecycleCo.repository.TrashTypeRepository;
import com.nemytow.recycleCo.RecycleCo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@Log4j2
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class MessagingApiImpl implements MessagingApi {

    @Autowired
    AMQPProducer producer;

    @Autowired
    TrashRepository trashRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountApi accountApi;

    @Autowired
    TrashTypeRepository trashTypeRepository;

    public void sendMessage(Long beanId){
        User currentUser = accountApi.getCurrentUser();
        PythonMessage message = PythonMessage.builder()
                .beanId(beanId)
                .userId(currentUser.getId())
                .build();
        producer.sendMessage(message);
    }

    @Override
    public Long saveTrashFromTheQueue(TrashMessage message){
        Optional<User> userFromMessage = userRepository.findById(message.getUserId());
        Optional<TrashType> type = trashTypeRepository.findById(message.getTypeId());
        if (!type.isPresent()) return null;
        if (!userFromMessage.isPresent()) return null;
        Trash trashFromMessage = Trash.from(userFromMessage.get(),type.get(),message);
        trashRepository.save(trashFromMessage);
        return trashFromMessage.getId();
    }
}
