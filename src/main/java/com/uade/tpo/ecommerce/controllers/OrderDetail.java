package com.uade.tpo.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.service.inter.OrderDetailService;

@RestController
@RequestMapping("orderDetail")
public class OrderDetail {

    @Autowired OrderDetailService orderDetailService;
    
}
