package org.mystore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.mystore.model.Orders;
import org.mystore.model.Product;
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
                                    @RequestParam("products") String products){

        Orders orders =new Orders(customerName,address,mobileNumber,"inProgress",products);

        Orders savedOrdersToDB =orderService.saveOrder(orders);
        System.err.println(savedOrdersToDB);


        return new RedirectView("http://"+request.getServerName()+"/cart");
    }


    @GetMapping("mobile/{mobileNumber}")
    public List<Orders> findByMobileNumber(@PathVariable String mobileNumber) {
        return orderService.findByMobileNumber(mobileNumber);
    }

}
