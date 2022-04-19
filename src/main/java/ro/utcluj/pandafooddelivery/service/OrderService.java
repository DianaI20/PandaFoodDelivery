package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.request.FoodItemDTO;
import ro.utcluj.pandafooddelivery.controller.request.OrderDTO;
import ro.utcluj.pandafooddelivery.model.Customer;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Order;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.repository.OrderRepository;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;
import ro.utcluj.pandafooddelivery.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {


    private OrderRepository orderRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    public ResponseEntity placeOrder(OrderDTO orderDTO){
        Customer u =(Customer) userRepository.findById(orderDTO.getCustomerId()).get();
        Restaurant r = restaurantRepository.getById(orderDTO.getRestaurantId());
        List<FoodItem> foodItems = orderDTO.getOrderItems().stream().map(f -> new FoodItem(f.getFoodId(), f.getName(), r, f.getCategory(), f.getPrice())).collect(Collectors.toList());
        orderRepository.save(new Order(orderDTO.getId(),u, orderDTO.getOrderStatus(),r,foodItems,orderDTO.getTotal()));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getAllOrders(Long id){

        Optional<Restaurant > restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return ResponseEntity.ok().body(restaurant.get().getOrders());
        }else{
               return ResponseEntity.status(500 ).build();
        }
    }

    public ResponseEntity getOrdersByCustomerId(Long id){
        List<Order> order = orderRepository.findByCustomerId(id);
        List<OrderDTO> orderDTOS = order.stream().map(o -> {
            List <FoodItemDTO> food = o.getFoodItems().stream().map(f -> new FoodItemDTO(f.getId(), o.getRestaurant().getId(),f.getFoodName(), f.getCategory(), f.getPrice())).collect(Collectors.toList());
            return new OrderDTO(o.getId(), o.getCustomer().getId(), o.getRestaurant().getId(),food,o.getOrderStatus(),o.getTotal());
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(orderDTOS);
    }
}
