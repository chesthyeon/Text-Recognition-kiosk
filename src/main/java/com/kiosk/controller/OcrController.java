package com.kiosk.controller;

import com.kiosk.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ocr")
public class OcrController {

    private final OcrService ocrService;
    @GetMapping("/")
    ResponseEntity<?> addPost(@RequestParam("url") String url) {
        System.out.println(url);
        return ResponseEntity.ok(ocrService.getOcr(url));
    }


    @RequestMapping("/camera")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("camera");
        return modelAndView;
    }
}
