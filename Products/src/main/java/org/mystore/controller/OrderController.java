package org.mystore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.mystore.model.Orders;
import org.mystore.model.Product;
import org.mystore.service.OrderService;
import org.mystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @PostMapping("/submit")
    public RedirectView submitOrder(@RequestParam("customer_name") String customerName,
                                    @RequestParam("address") String address,
                                    @RequestParam("mobile_number") String mobileNumber,
                                    @RequestParam("total_price") double totalPrice,
                                    @RequestParam("products") String products) throws JsonProcessingException {




        List<String> productList=new ArrayList<>(); //we made a new list where we will add the product which is valid.

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode productsNode = objectMapper.readTree(products);
        for (JsonNode productNode : productsNode) {

            long productId=productNode.get("id").asLong();

            Product updatedProduct=new Product();
            updatedProduct.setId(productId); //id done
            updatedProduct.setCategory(productNode.get("category").asText());
            updatedProduct.setDescription(productNode.get("description").asText());
            updatedProduct.setOther_images(productNode.get("other_images")==null?"":productNode.get("other_images").asText());
            updatedProduct.setPrice(productNode.get("price").asInt());
            updatedProduct.setPrimary_image(productNode.get("primary_image").asText());
            updatedProduct.setSubcategory(productNode.get("subcategory")==null?"":productNode.get("subcategory").asText());
            //got the things which will not be updated

            Optional<Product> tempProduct=productService.findById(productId);
            //here we will update only quantity(int) and size(string)
            if(tempProduct.get().getQuantity() >= productNode.get("quantity").asInt()){
                updatedProduct.setQuantity(tempProduct.get().getQuantity() - productNode.get("quantity").asInt()); //quantity done
                if(tempProduct.get().getSize() != null){
                    String size=tempProduct.get().getSize();
                    size=size.replaceFirst(productNode.get("size").asText(),"").trim();
                    updatedProduct.setSize(size); //size done
                }

                productService.updateProduct(updatedProduct); //product Table updated
                productList.add(productNode.toString());
            }
        }
        // System.err.println(productList.toString());


        Orders orders =new Orders(customerName,address,mobileNumber,"inProgress",totalPrice,productList.toString());

        Orders savedOrdersToDB =orderService.saveOrder(orders);
       // System.err.println(savedOrdersToDB);

        //return new RedirectView("http://"+request.getServerName()+"/track-order");
        return trackOrder(mobileNumber);
    }


    @GetMapping("track/{mobileNumber}")
    public RedirectView trackOrder(@PathVariable String mobileNumber) {

        RedirectView rv= new RedirectView("http://"+request.getServerName()+"/track-order");
        String orderDetailString=orderService.findByMobileNumber(mobileNumber).toString();
       // System.out.println(orderDetailString);

        rv.addStaticAttribute("orderDetailString",orderDetailString);
        return rv;
    }

    //...............for ADMIN.......................
    @GetMapping("mobile/{mobileNumber}")
    public List<Orders> findByMobileNumber(@PathVariable String mobileNumber) {

        return orderService.findByMobileNumber(mobileNumber);
    }




}
