package com.nemytow.recycleCo.RecycleCo.endpoints;

import com.nemytow.recycleCo.RecycleCo.dto.OperationResponse;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationData;
import com.nemytow.recycleCo.RecycleCo.service.SecurityService;
import com.nemytow.recycleCo.RecycleCo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@Valid
public class MainController {

    @GetMapping("/main")
    public String main(Map<String,Object> model){
        return "main";
    }
}
