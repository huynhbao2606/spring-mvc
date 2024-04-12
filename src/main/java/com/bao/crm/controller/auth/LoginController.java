package com.bao.crm.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "auth/access-denied";
    }
}
