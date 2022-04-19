package ro.utcluj.pandafooddelivery.service.mappper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.FoodItemDTO;
import ro.utcluj.pandafooddelivery.controller.dto.OrderDTO;
import ro.utcluj.pandafooddelivery.controller.dto.RestaurantDTO;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantMapper implements ObjectMapper<Restaurant, RestaurantDTO> {

    @Autowired
    private FoodItemMapper foodItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Restaurant convertFromDTO(RestaurantDTO restaurantDTO) {

        return new Restaurant(restaurantDTO.getRestaurantName(),
                              restaurantDTO.getRestaurantLocation(),
                              restaurantDTO.getDeliveryZone());
    }

    @Override
    public RestaurantDTO convertToDTO(Restaurant restaurant){

        List<FoodItemDTO> foodItemDTOS = restaurant.getFoodItems()
                                                    .stream()
                                                    .map(f -> foodItemMapper.convertToDTO((f)))
                                                    .collect(Collectors.toList());

        List<OrderDTO> orders = restaurant.getOrders()
                                            .stream()
                                            .map(order -> orderMapper.convertToDTO(order))
                                            .collect(Collectors.toList());

        return new RestaurantDTO(restaurant.getId(),
                                 restaurant.getAdministrator().getId(),
                                 restaurant.getAdministrator().getEmail(),
                                 restaurant.getAdministrator().getPhoneNumber(),
                                 restaurant.getName(),
                                 restaurant.getLocation(),
                                 restaurant.getDeliveryZones(),
                                 foodItemDTOS,
                                 orders);
    }

    public List<RestaurantDTO> convertToDTO(List<Restaurant> restaurantDTOS) {
        return restaurantDTOS.stream().map(f -> this.convertToDTO(f)).collect(Collectors.toList());
    }
}
