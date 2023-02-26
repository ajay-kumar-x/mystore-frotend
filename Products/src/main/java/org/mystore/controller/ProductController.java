package org.mystore.controller;

import org.mystore.model.Product;
import org.mystore.service.ImageService;
import org.mystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public String uploadProduct(@RequestParam("file") MultipartFile file,
                                @RequestParam("category") String category,
                                @RequestParam("description") String description,
                                @RequestParam("price") int price,
                                @RequestParam("quantity") int quantity,
                                @RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3) throws IOException {
        Product product=new Product();

        product.setCategory(category);
        product.setPrice(price);
        product.setDescription(description);
        product.setQuantity(quantity);

        product.setPrimary_image(String.valueOf(imageService.saveImage(file).getId()));

        String otherImages="";
        if(! file1.isEmpty())
            otherImages += String.valueOf(imageService.saveImage(file1).getId())+" ";

        if(! file2.isEmpty())
            otherImages += String.valueOf(imageService.saveImage(file2).getId())+" ";

        if(! file3.isEmpty())
            otherImages += String.valueOf(imageService.saveImage(file3).getId())+" ";

        product.setOther_images(otherImages.trim());

        productService.saveProduct(product);
        System.err.println("Uploading");
        return product.getPrimary_image();
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("")
    public List<Product> findAll() {
        return productService.findAllProduct();
    }

    @GetMapping("category/{category}")
    public List<Product> findById(@PathVariable String category) {
        return productService.findByCategory(category);
    }


}
