package ro.utcluj.pandafooddelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.controller.request.OrderDTO;
import ro.utcluj.pandafooddelivery.model.Order;
import ro.utcluj.pandafooddelivery.service.OrderService;
import ro.utcluj.pandafooddelivery.service.RestaurantService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("orders/all")
    public ResponseEntity getAllOrders(@RequestParam(name = "restaurantId") Long restaurantId){
        return orderService.getAllOrders(restaurantId);
    }

    @PostMapping("order/new")
    public ResponseEntity placeOrder(@RequestBody OrderDTO order){
        System.out.println(order);
        return orderService.placeOrder(order);
    }

    @GetMapping("orders/customer/")
    public ResponseEntity getOrdersByCustomerId( @RequestParam Long customerId){
        return orderService.getOrdersByCustomerId(customerId);
    }
}
