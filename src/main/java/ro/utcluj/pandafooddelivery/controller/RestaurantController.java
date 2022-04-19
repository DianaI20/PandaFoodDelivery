package ro.utcluj.pandafooddelivery.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.controller.dto.FoodItemDTO;
import ro.utcluj.pandafooddelivery.service.FoodItemService;
import ro.utcluj.pandafooddelivery.service.RestaurantService;

import javax.management.InstanceNotFoundException;

@Slf4j
@RestController
@RequestMapping(path = "/restaurant")
@AllArgsConstructor
public class RestaurantController {


    private RestaurantService restaurantService;
    private FoodItemService foodItemService;

    @GetMapping(path = "/menu")
    public ResponseEntity findFoodItems(@RequestParam(value = "id") Long id) {
        log.info("It works fine");
        return restaurantService.findAllFoodItems(id);
    }

    @GetMapping(path = "/all")
    public ResponseEntity findAll() {

        try {
            return restaurantService.findAll();
        } catch (InstanceNotFoundException e) {
            log.error("Instance not found.");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity findById(@RequestParam Long id) {
        return restaurantService.findById(id);
    }

    @GetMapping("/admin")
    public ResponseEntity findByAdministratorEmail(@RequestParam String email) {
        return restaurantService.findByAdministrator(email);
    }

    @PostMapping("/add/food")
    public ResponseEntity saveFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        return foodItemService.save(foodItemDTO);
    }

    @PostMapping("/remove/food")
    public ResponseEntity deleteFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        return foodItemService.deleteById(foodItemDTO.getFoodId());
    }


    @PostMapping("/export")
    public ResponseEntity exportPDF(@RequestParam(value = "id") Long id ) {
        return restaurantService.exportPDF(id);
    }
}
