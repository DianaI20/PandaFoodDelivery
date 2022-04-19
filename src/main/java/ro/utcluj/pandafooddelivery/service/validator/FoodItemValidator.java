package ro.utcluj.pandafooddelivery.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.FoodItemDTO;
import ro.utcluj.pandafooddelivery.service.exception.InvalidInputException;

@Slf4j
@Service
public class FoodItemValidator {

    private String FOOD_ITEM_ERROR = "Trying to insert a new food item: invalid %s";


    public void validate(FoodItemDTO foodItemDTO) throws InvalidInputException {

        if (foodItemDTO.getName().isEmpty()) {
            log.error(String.format(FOOD_ITEM_ERROR, "name"));
            throw new InvalidInputException(String.format(FOOD_ITEM_ERROR, "name"));
        }
        if (foodItemDTO.getCategory() == null) {
            log.error(String.format(FOOD_ITEM_ERROR, "category"));
            throw new InvalidInputException(String.format(FOOD_ITEM_ERROR, "category"));
        }
        if (foodItemDTO.getPrice() < 0 || Float.isNaN(foodItemDTO.getPrice())) {
            log.error(String.format(FOOD_ITEM_ERROR, "price"));
            throw new InvalidInputException(String.format(FOOD_ITEM_ERROR, "price"));
        }
    }
}
