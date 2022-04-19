package ro.utcluj.pandafooddelivery.service.validators;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.controller.request.FoodItemDTO;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.repository.FoodItemRepository;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class FoodItemController {

    private FoodItemRepository foodItemRepository;
    private RestaurantRepository restaurantRepository;

    public ResponseEntity addNewFoodItem(FoodItemDTO foodItemDTO) {

        Restaurant restaurant = restaurantRepository.findById(foodItemDTO.getRestaurantId()).get();
        FoodItem foodItem = new FoodItem(foodItemDTO.getFoodId(), foodItemDTO.getName(), restaurant, foodItemDTO.getCategory(), foodItemDTO.getPrice());
        if (foodItemRepository.save(foodItem) != null) {
            return ResponseEntity.ok().body("Item added.");
        }
        
        return ResponseEntity.status(500).build();
    }

    public ResponseEntity removeFoodItem(Long id) {
        foodItemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
