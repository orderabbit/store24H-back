package com.example.back.controller;

import com.example.back.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://3.35.30.191:3000")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(
            @RequestParam("file") MultipartFile file
    ) {
        String url = fileService.upload(file);
        return url;
    }

    @GetMapping(value="{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(
            @PathVariable("fileName") String filename
    ){
        Resource resource = fileService.getImage(filename);
        return  resource;
    }
}
