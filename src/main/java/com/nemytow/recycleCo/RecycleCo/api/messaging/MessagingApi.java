package com.nemytow.recycleCo.RecycleCo.api.messaging;

import com.nemytow.recycleCo.RecycleCo.messaging.TrashMessage;

public interface MessagingApi {

    void sendMessage(TrashMessage message);

    Long saveTrashFromTheQueue(TrashMessage message);
}
