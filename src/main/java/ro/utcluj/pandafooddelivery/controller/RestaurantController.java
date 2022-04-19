package ro.utcluj.pandafooddelivery.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.controller.request.FoodItemDTO;
import ro.utcluj.pandafooddelivery.model.*;
import ro.utcluj.pandafooddelivery.service.RestaurantService;
import ro.utcluj.pandafooddelivery.service.validators.FoodItemController;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;
    private UserController userController;
    private FoodItemController foodItemController;

    @GetMapping(path = "/menu")
    public ResponseEntity getRestaurantMenu(@RequestParam Long id)  {
        return restaurantService.getFoodItems(id);
    }

    @GetMapping(path = "/all")
    public ResponseEntity getAllRestaurants()  {
        try {
            return restaurantService.getAllRestaurants();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    public List<Restaurant> getRestaurantByName(String name) throws InstanceNotFoundException {
        return restaurantService.getRestaurantByName(name);
    }

    @GetMapping("/id")
    public ResponseEntity getRestaurantById(@RequestParam Long id){
        return restaurantService.findById(id);
    }

    @GetMapping("/admin")
    public ResponseEntity getRestaurantByAdministrator(@RequestParam String email){
        User admin = userController.getUserByEmail(email);
        System.out.println(admin);
        ResponseEntity r = restaurantService.findByAdministrator(admin);
        return r;
    }
    @PostMapping("/add/food")
        public ResponseEntity addNewFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        return  foodItemController.addNewFoodItem(foodItemDTO);
    }

    @DeleteMapping("/remove/food")
    public ResponseEntity removeFoodItem(@RequestParam Long id){
        return foodItemController.removeFoodItem(id);
    }





}
