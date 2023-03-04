package org.mystore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.mystore.model.Orders;
import org.mystore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderService orderService;
    @PostMapping("/submit")
    public RedirectView submitOrder(@RequestParam("customer_name") String customerName,
                                    @RequestParam("address") String address,
                                    @RequestParam("mobile_number") String mobileNumber,
                                    @RequestParam("total_price") double totalPrice,
                                    @RequestParam("products") String products) throws JsonProcessingException {

        Orders orders =new Orders(customerName,address,mobileNumber,"inProgress",totalPrice,products);

        Orders savedOrdersToDB =orderService.saveOrder(orders);
        System.err.println(savedOrdersToDB);

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
