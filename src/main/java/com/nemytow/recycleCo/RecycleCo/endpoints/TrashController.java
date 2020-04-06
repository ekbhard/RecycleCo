package com.nemytow.recycleCo.RecycleCo.endpoints;


import com.nemytow.recycleCo.RecycleCo.api.messaging.MessagingApi;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Valid
public class TrashController {

    @Autowired
    MessagingApi messagingApi;

    @PreAuthorize("hasAuthority('USER, ADMIN')")
    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = {"application/json"})
    public void sendMessage() {
        messagingApi.sendMessage();
    }

}
