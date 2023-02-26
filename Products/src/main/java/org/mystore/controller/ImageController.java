package org.mystore.controller;

import org.mystore.exception.ImageNotFoundException;
import org.mystore.model.Image;
import org.mystore.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private  ImageService imageService;


    @PostMapping("/upload")
    public Image uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        System.err.println("Uploading");
        return imageService.saveImage(file);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) throws IOException, ImageNotFoundException {
        byte[] imageBytes = imageService.retrieveImage(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        //headers.setContentDispositionFormData("attachment", "image.jpeg");   // this used to download

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}