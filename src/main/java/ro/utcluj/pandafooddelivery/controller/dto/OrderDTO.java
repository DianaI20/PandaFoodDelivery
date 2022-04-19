package ro.utcluj.pandafooddelivery.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.utcluj.pandafooddelivery.model.OrderStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private Long customerId;
    private Long restaurantId;
    private List<FoodItemDTO> orderItems;
    private OrderStatus orderStatus;
    private float total;

}
