package org.mystore.service;

import org.mystore.model.Orders;
import org.mystore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private  OrderRepository orderRepository;

    public Orders saveOrder(Orders orders){
        return orderRepository.save(orders);
    }

    public List<Orders> findByMobileNumber(String mobileNumber){
        return orderRepository.findByMobileNumber(mobileNumber);
    }


}
