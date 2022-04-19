package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.FoodItemDTO;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.repository.FoodItemRepository;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;
import ro.utcluj.pandafooddelivery.service.exception.InvalidInputException;
import ro.utcluj.pandafooddelivery.service.mappper.FoodItemMapper;
import ro.utcluj.pandafooddelivery.service.validator.FoodItemValidator;

@Slf4j
@Service
@AllArgsConstructor
public class FoodItemService {

    private FoodItemRepository foodItemRepository;
    private FoodItemMapper foodItemMapper;
    private FoodItemValidator foodItemValidator;
    private final static String FOOD_ITEM_SAVED = "A new food item was added for restaurant %d";
    private final static String FOOD_ITEM_DELETED = "Food item %d deleted successfully";

    /**
     * Insert a new food item
     *
     * @param foodItemDTO holds the information about the food in the menu
     * @return Ok if the object was inserted successfully, 500 otherwise;
     */
    public ResponseEntity save(FoodItemDTO foodItemDTO) {

        try {
            foodItemValidator.validate(foodItemDTO);
        } catch (InvalidInputException e) {
            return ResponseEntity.status(500).build();
        }
        FoodItem foodItem = foodItemMapper.convertFromDTO(foodItemDTO);
        log.info(String.format(FOOD_ITEM_SAVED, foodItem.getId(), foodItemDTO.getRestaurantId()));
        foodItemRepository.save(foodItem);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete a food item by id.
     *
     * @param id If of the entity to be deleted
     * @return ResponseEntity ok if there was a successful operation
     */
    public ResponseEntity deleteById(Long id) {
        foodItemRepository.deleteById(id);
        log.info(String.format(FOOD_ITEM_DELETED,id));
        return ResponseEntity.ok().build();
    }
}
