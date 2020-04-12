package com.nemytow.recycleCo.RecycleCo.endpoints;


import com.nemytow.recycleCo.RecycleCo.api.messaging.MessagingApi;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileData;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationInput;
import com.nemytow.recycleCo.RecycleCo.messaging.TrashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/trash")
@Valid
public class TrashController {

    @Autowired
    MessagingApi messagingApi;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = {"application/json"})
    public void sendMessage(@RequestBody TrashMessage message, HttpServletRequest req) {
        messagingApi.sendMessage(message);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = {"application/json"})
    protected void getUserTrash(){

    }

}
