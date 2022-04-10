package ro.utcluj.pandafooddelivery.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.service.RestaurantService;

import javax.management.InstanceNotFoundException;

@RestController
@RequestMapping(path = "/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;

    @GetMapping(path ="/{restaurantId}")
    public ResponseEntity getAllFoodItems(@PathVariable("restaurantId") Long id) {
        try {
            return ResponseEntity.ok().body(restaurantService.findById(id).getFoodItems());
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
