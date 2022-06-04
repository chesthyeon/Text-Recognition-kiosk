package com.kiosk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    @GetMapping(value = "/admin")
    public String main(Model model){
        return "admin";
    }
}
