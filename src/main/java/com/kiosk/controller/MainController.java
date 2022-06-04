package com.kiosk.controller;

import com.kiosk.dto.CartDetailDto;
import com.kiosk.dto.ItemSearchDto;
import com.kiosk.dto.MainItemDto;
import com.kiosk.service.CartService;
import com.kiosk.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;
    private final CartService cartService;

//    @GetMapping(value = "/")
//    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model){
//
//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
//        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
//
//        model.addAttribute("items", items);
//        model.addAttribute("itemSearchDto", itemSearchDto);
//        model.addAttribute("maxPage", 5);
//        List<CartDetailDto> cartDetailList = cartService.getCartList();
//        model.addAttribute("cartItems", cartDetailList);
//
//        return "main";
//    }
    @GetMapping(value = {"/{param}","/"})
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model, @PathVariable(value = "param",required = false) String param){
        if(param == null)
            return "main2";

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable, param);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        List<CartDetailDto> cartDetailList = cartService.getCartList();
        model.addAttribute("cartItems", cartDetailList);
        return "main";
    }

}