package ro.utcluj.pandafooddelivery.service.mappper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.FoodItemDTO;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodItemMapper implements ObjectMapper<FoodItem, FoodItemDTO> {


    private RestaurantRepository restaurantRepository;

    public FoodItem convertFromDTO(FoodItemDTO foodItemDTO) {
        Restaurant restaurant = restaurantRepository.getById(foodItemDTO.getRestaurantId());
        return new FoodItem(foodItemDTO.getFoodId(),
                            foodItemDTO.getName(),
                            restaurant,
                            foodItemDTO.getCategory(),
                            foodItemDTO.getPrice());
    }

    public List<FoodItem> convertFromDTO(List<FoodItemDTO> foodItemDTOS) {
        return foodItemDTOS.stream().map(foodItemDTO -> this.convertFromDTO(foodItemDTO)).collect(Collectors.toList());
    }

    public FoodItemDTO convertToDTO(FoodItem foodItem) {
        return new FoodItemDTO( foodItem.getId(),
                                foodItem.getRestaurant().getId(),
                                foodItem.getName(),
                                foodItem.getCategory(),
                                foodItem.getPrice());
    }

    public List<FoodItemDTO> convertToDTO(List<FoodItem> foodItems) {
        return foodItems.stream().map(f -> this.convertToDTO(f)).collect(Collectors.toList());
    }
}
