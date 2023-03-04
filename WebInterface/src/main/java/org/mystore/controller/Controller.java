package org.mystore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mystore.model.OrderDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Value("${spring.application.name}")
    String appName;

    static final String PRODUCT_SERVICE_URL="http://192.168.1.2:8080";



    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("productSearched","Home");
        return "index";
    }

    @GetMapping("/explore")
    public String exploreProduct(@RequestParam("category") String category,
                                 @RequestParam(name = "subcategory", required = false) String subcategory, Model model) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = PRODUCT_SERVICE_URL+"/product/category/"+category;
        String response = restTemplate.getForObject(url, String.class);
//        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode productArray = objectMapper.readTree(response);

        model.addAttribute("appName", appName);
        model.addAttribute("product_service_url",PRODUCT_SERVICE_URL);

        model.addAttribute("productSearched",category);
        model.addAttribute("noOfproductFound",productArray.size());
        model.addAttribute("productList",productArray);
//        for(JsonNode x:productArray)
//            System.out.println(x.get("price"));
        return "explore-product";
    }


    //this will return the particular product and also similar product(same category)
    @GetMapping("/product")
    public String getProduct(@RequestParam("id") int id,Model model) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = PRODUCT_SERVICE_URL+"/product/"+id;
        String response = restTemplate.getForObject(url, String.class); //product detail in string format

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode product = objectMapper.readTree(response); //product detail in json format
        //System.out.println(jsonNode.get("id").asInt());



        String urlForAllProductInSameCategory = PRODUCT_SERVICE_URL+"/product/category/"+product.get("category").asText();
        String allProductInString = restTemplate.getForObject(urlForAllProductInSameCategory, String.class); //All product detail with that category in string format
        JsonNode allProduct=objectMapper.readTree(allProductInString);

        List<JsonNode> similarProduct=new ArrayList<>();
        for(JsonNode j:allProduct)
        {
            if(j.get("category").asText().trim().equals(product.get("category").asText().trim()) && j.get("id").asInt() !=id )
              similarProduct.add(j);
        }


        model.addAttribute("appName", appName);
        model.addAttribute("product_service_url",PRODUCT_SERVICE_URL);

        model.addAttribute("productSearched",product.get("category").asText()); //this will be used as showing on top of product
        model.addAttribute("product",product);
        model.addAttribute("similarProduct",similarProduct);

        return "product";
    }


    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("product_service_url",PRODUCT_SERVICE_URL);
        model.addAttribute("productSearched","Cart"); //this will be used as showing on top of product
        return "cart";
    }


    //we will not use this directly in our microservice
    //we will call OrderController's /track/{mobile-number} method which will get the order details and put in model attribute and redirect here
    //also be used by /submit order
    @GetMapping("/track-order")
    public String trackOrder(@ModelAttribute("orderDetailString")  String orderDetailString,
                             Model model) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderDetail> orderDetailJson = objectMapper.readValue(orderDetailString, new TypeReference<List<OrderDetail>>(){});

        model.addAttribute("appName", appName);
        model.addAttribute("product_service_url",PRODUCT_SERVICE_URL);

        model.addAttribute("orderDetailJson", orderDetailJson);

        return "track-order";
    }



    //.................................for  ADMIN.........................................
    @GetMapping("/upload")
    public String productUpload(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("product_service_url",PRODUCT_SERVICE_URL);
        return "upload";
    }

}
