package org.mystore.controller;

import org.mystore.model.Product;
import org.mystore.service.ImageService;
import org.mystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
                                @RequestParam(name = "subcategory", required = false) String subcategory,
                                @RequestParam("description") String description,
                                @RequestParam("price") int price,
                                @RequestParam("quantity") int quantity,
                                @RequestParam("size") String size,
                                @RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3) throws IOException {
        Product product=new Product();

        product.setCategory(category);
        product.setSubcategory(subcategory);
        product.setPrice(price);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setSize(size);

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
        System.err.println("Uploaded");
        return product.getPrimary_image();
    }

    @GetMapping("/id/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Product> findAll() {
        return productService.findAllProduct();
    }

    @GetMapping("/find-similar")
    public List<Product> findByCategorySubcategory(@RequestParam("category") String category,
                                                   @RequestParam(name = "subcategory", required = false) String subcategory) {

        if(subcategory==null || subcategory.toLowerCase().trim().equals("null") || subcategory.trim().equals("")){
            return productService.findByCategory(category);
        }
        return productService.findProductsByCategoryAndSubcategory(category,subcategory);
    }


    //this will be ued in index page to show the Latest product for all category
    @GetMapping("find-latest")
    public List<Product> findAllLatestByCategory(){
        List<Product> list=new ArrayList<>();
        list.add(productService.getLatestProductByCategory("saree"));
        list.add(productService.getLatestProductByCategory("jeans"));
        list.add(productService.getLatestProductByCategory("shirt"));
        list.removeIf(element -> element == null);
        return list;

      }

}
