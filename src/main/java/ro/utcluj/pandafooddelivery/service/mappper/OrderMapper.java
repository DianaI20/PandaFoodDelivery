package ro.utcluj.pandafooddelivery.service.mappper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.FoodItemDTO;
import ro.utcluj.pandafooddelivery.controller.dto.OrderDTO;
import ro.utcluj.pandafooddelivery.model.Customer;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Order;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;
import ro.utcluj.pandafooddelivery.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderMapper implements ObjectMapper<Order, OrderDTO> {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private FoodItemMapper foodItemMapper;

    @Override
    public Order convertFromDTO(OrderDTO orderDTO) {

        Customer customer = (Customer) userRepository.findById(orderDTO.getCustomerId()).get();
        Restaurant restaurant = restaurantRepository.getById(orderDTO.getRestaurantId());
        List<FoodItem> foodItems = orderDTO.getOrderItems()
                                            .stream()
                                            .map(f -> foodItemMapper.convertFromDTO(f)).collect(Collectors.toList());

        return new Order(orderDTO.getId(),
                         customer,
                         orderDTO.getOrderStatus(),
                         restaurant,
                         foodItems,
                         orderDTO.getTotal());
    }

    public OrderDTO convertToDTO(Order order) {

        List<FoodItemDTO> foodItemsDTOS = order.getFoodItems()
                                                .stream()
                                                .map(f -> foodItemMapper.convertToDTO(f)).collect(Collectors.toList());

        return new OrderDTO(order.getId(),
                            order.getCustomer().getId(),
                            order.getRestaurant().getId(),
                            foodItemsDTOS,
                            order.getStatus(),
                            order.getTotal());
    }

    public List<OrderDTO> convertToDTO(List<Order> orders) {
        return orders.stream().map(o -> this.convertToDTO(o)).collect(Collectors.toList());
    }

}
