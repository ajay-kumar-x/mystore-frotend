package org.mystore.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.mystore.exception.ImageNotFoundException;
import org.mystore.model.Image;
import org.mystore.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private  ImageRepository imageRepository;

    private Cloudinary cloudinary;
    public ImageService(@Value("${cloudinary.cloud-name}") String cloudName,
                        @Value("${cloudinary.api-key}") String apiKey,
                        @Value("${cloudinary.api-secret}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    public Image saveImage(MultipartFile file) throws IOException {
        String filePath = saveFileToDisk(file);
        Image image = new Image();
        image.setFilePath(filePath);
        return imageRepository.save(image);
    }

    public byte[] retrieveImage(Long id) throws IOException, ImageNotFoundException {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Image not found with id: " + id));
        return readFileFromWeb(image.getFilePath());
    }





    private String saveFileToDisk(MultipartFile file) throws IOException {

        Transformation transformation = new Transformation().width(500)
                .height(500)
                .quality("auto")
                .flags("progressive");

        Map<String, String> options = ObjectUtils.asMap(
                "folder", "my-folder",
                "use_filename", "false",
                "unique_filename", "true",
                "transformation", transformation);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        String url = (String) uploadResult.get("url");
        System.out.println(url);
        return url;
    }

    private byte[] readFileFromWeb(String filePath) throws IOException {

            // Open a connection to the image URL
            URL url = new URL(filePath);
            InputStream is = url.openStream();

            // Read the image data into a byte array
           byte[] imageData = is.readAllBytes();

        return imageData;

    }
}
