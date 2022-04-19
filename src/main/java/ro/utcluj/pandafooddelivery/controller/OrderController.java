package ro.utcluj.pandafooddelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.controller.dto.OrderDTO;
import ro.utcluj.pandafooddelivery.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("orders/all")
    public ResponseEntity findByRestaurantId(@RequestParam(name = "restaurantId") Long restaurantId) {
        return orderService.findAllByRestaurantId(restaurantId);
    }

    @PostMapping("order/new")
    public ResponseEntity save(@RequestBody OrderDTO order) {
        return orderService.save(order);
    }

    @GetMapping("orders/customer/")
    public ResponseEntity findByCustomerId
            (@RequestParam Long customerId) {
        return orderService.findAllByCustomerId(customerId);
    }

    @PostMapping("update/status")
    public ResponseEntity updateStatus(@RequestBody OrderDTO orderDTO) {
        return orderService.updateStatus(orderDTO.getId(), orderDTO.getOrderStatus());
    }
}
