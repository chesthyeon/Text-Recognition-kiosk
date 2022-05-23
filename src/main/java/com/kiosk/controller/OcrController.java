package com.kiosk.controller;

import com.kiosk.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})//프론트서버랑 연결
public class OcrController {

    private final OcrService ocrService;
    @GetMapping("/requestOcr")
    ResponseEntity<?> addPost(@RequestParam String url) throws URISyntaxException {
        return ResponseEntity.ok(ocrService.getOcr(url));
    }
}
