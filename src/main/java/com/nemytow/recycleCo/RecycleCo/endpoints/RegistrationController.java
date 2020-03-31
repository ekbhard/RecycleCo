package com.nemytow.recycleCo.RecycleCo.endpoints;

import com.nemytow.recycleCo.RecycleCo.domain.Account;
import com.nemytow.recycleCo.RecycleCo.repository.AccountRepository;
import com.nemytow.recycleCo.RecycleCo.service.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
@Valid
@Transactional
public class RegistrationController {
    @Autowired
    private AccountRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam Account user, Map<String,Object> model){
        if (user.getPassword().equals("") || user.getUsername().equals("")){
            model.put("message","Null login and password");
            return "registration";
        }

        Account userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message","User founded");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
//        userRepository.save(user);
        return "redirect:/settings";
    }
}
