package com.zhengguan.crush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/accounts/login")
    public String showLoginPage() {
        return "accounts/login";
    }
}