package com.nemytow.recycleCo.RecycleCo.endpoints;

import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.dto.OperationResponse;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Valid
public class RegistrationController {
    @Autowired
    AccountApi accountApi;

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse addNewUser(@RequestBody RegistrationInput account, HttpServletRequest req) {
        Long idCreated = accountApi.addNewUser(account);

        OperationResponse resp = new OperationResponse();
        if (idCreated != null){
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("User Added id : " + idCreated);
        }
        else{
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.ERROR);
            resp.setOperationMessage("Unable to add user");
        }
        return resp;
    }
}
