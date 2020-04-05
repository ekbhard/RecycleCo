package com.nemytow.recycleCo.RecycleCo.endpoints;

import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.domain.Account;
import com.nemytow.recycleCo.RecycleCo.dto.OperationResponse;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationData;
import com.nemytow.recycleCo.RecycleCo.repository.AccountRepository;
import com.nemytow.recycleCo.RecycleCo.service.Role;
import com.nemytow.recycleCo.RecycleCo.service.SecurityService;
import com.nemytow.recycleCo.RecycleCo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@Valid
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse addNewUser(@RequestBody RegistrationData account, HttpServletRequest req) {
        Long idCreated = userService.addNewAccount(account);

        OperationResponse resp = new OperationResponse();
        if (idCreated != null){
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("User Added");
        }
        else{
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.ERROR);
            resp.setOperationMessage("Unable to add user");
        }
        return resp;
    }
}
