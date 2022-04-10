package ro.utcluj.pandafooddelivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.service.RestaurantService;
import ro.utcluj.pandafooddelivery.service.UserService;

import javax.management.InstanceNotFoundException;

@RestController
@RequestMapping(path = "/customer/{user}")
@AllArgsConstructor
public class CustomerController {


    private UserService userService;
    private RestaurantService restaurantService;

    @GetMapping(path = "/restaurants")
    public ResponseEntity findAllRestaurants() throws InstanceNotFoundException {
        return ResponseEntity.ok().body(restaurantService.getAllRestaurants());
    }

}
