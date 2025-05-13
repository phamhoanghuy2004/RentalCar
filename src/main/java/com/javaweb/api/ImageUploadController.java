package com.javaweb.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.service.CloudinaryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/multiple")
    public ResponseEntity<List<String>> uploadMultipleImages(@RequestParam("images") List<MultipartFile> images) {
        List<String> urls = new ArrayList<>();
        try {
            for (MultipartFile image : images) {
                String url = cloudinaryService.uploadFile(image);
                urls.add(url);
            }
            return ResponseEntity.ok(urls);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}