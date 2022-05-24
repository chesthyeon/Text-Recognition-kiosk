package com.kiosk.controller;

import com.kiosk.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;
    @GetMapping("/ocr/{url}")
    ResponseEntity<?> addPost(@PathVariable("url") String url) {
        return ResponseEntity.ok(ocrService.getOcr(url));
    }

}