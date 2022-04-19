package ro.utcluj.pandafooddelivery.controller.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RestaurantDTO {

    private Long restaurantId;
    private Long administratorId;
    private String email;
    private String phoneNumber;
    private String restaurantName;
    private String restaurantLocation;
    private String deliveryZone;
    private List<FoodItemDTO> foodItems;
    private List<OrderDTO> orders;

}
