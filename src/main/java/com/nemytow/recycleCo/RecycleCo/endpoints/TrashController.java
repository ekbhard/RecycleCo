package com.nemytow.recycleCo.RecycleCo.endpoints;


import com.nemytow.recycleCo.RecycleCo.api.messaging.MessagingApi;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileData;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationInput;
import com.nemytow.recycleCo.RecycleCo.messaging.PythonMessage;
import com.nemytow.recycleCo.RecycleCo.messaging.TrashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/trash")
@Valid
public class TrashController {

    @Autowired
    MessagingApi messagingApi;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = {"application/json"})
    public void sendMessage(@RequestParam Long beanId, HttpServletRequest req) {
        messagingApi.sendMessage(beanId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = {"application/json"})
    protected void getUserTrash(){

    }

}
