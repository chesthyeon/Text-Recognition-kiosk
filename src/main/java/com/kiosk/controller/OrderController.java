package com.kiosk.controller;

import com.kiosk.dto.OrderDto;
import com.kiosk.service.CartService;
import com.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final CartService cartService;
    @GetMapping("/order")
    public String oder(Model model){
        cartService.deleteAllCartItem();
        return "redirect:/";
    }

}