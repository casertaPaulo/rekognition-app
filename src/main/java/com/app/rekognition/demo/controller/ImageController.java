package com.app.rekognition.demo.controller;

import com.app.rekognition.demo.DTO.ImageResponseDTO;
import com.app.rekognition.demo.service.ImageAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageAnalyzerService service;

    @PostMapping
    public ResponseEntity<ImageResponseDTO> analyzeImage(@RequestParam("file") MultipartFile file) {
       return ResponseEntity.ok(service.analyzeImage(file));
    }

}
