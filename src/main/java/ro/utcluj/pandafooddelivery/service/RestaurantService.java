package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.request.FoodItemDTO;
import ro.utcluj.pandafooddelivery.controller.request.AdministratorDTO;
import ro.utcluj.pandafooddelivery.controller.request.OrderDTO;
import ro.utcluj.pandafooddelivery.controller.request.RestaurantDTO;
import ro.utcluj.pandafooddelivery.model.*;
import ro.utcluj.pandafooddelivery.repository.OrderRepository;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;
import ro.utcluj.pandafooddelivery.repository.UserRepository;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public List<Restaurant> getRestaurantByName(String name) throws InstanceNotFoundException {
        Optional <List<Restaurant>> restaurants = restaurantRepository.findRestaurantByName(name);
        if(restaurants.isPresent()){
            return restaurants.get();
        }else {
            throw new InstanceNotFoundException("No restaurant");
        }
    }

    public ResponseEntity getAllRestaurants() throws InstanceNotFoundException {

        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> rst = restaurants.stream().map(
                                            rest -> {
                    List<FoodItemDTO> foodItemDTOS = rest.getFoodItems().stream().map(f -> new FoodItemDTO(f.getId(),f.getRestaurant().getId(), f.getFoodName(),f.getCategory(),f.getPrice())).collect(Collectors.toList());
                    List<OrderDTO> orders = rest.getOrders().stream().map(f -> new OrderDTO(f.getId(),f.getCustomer().getId(), f.getRestaurant().getId(),
                                                                                                    f.getFoodItems().stream().map(g -> new FoodItemDTO(g.getId(),g.getRestaurant().getId(), g.getFoodName(),g.getCategory(), g.getPrice())).collect(Collectors.toList()),
                            f.getOrderStatus(), f.getTotal())).collect(Collectors.toList());
                    RestaurantDTO updatedRestaurant = new RestaurantDTO(rest.getId(), rest.getAdministrator().getId(),rest.getAdministrator().getEmail(),
                            rest.getAdministrator().getPhoneNumber(), rest.getName(), rest.getLocation(), rest.getDeliveryZones(),foodItemDTOS, orders);
                                                return updatedRestaurant;
                                            }).collect(Collectors.toList());
        return ResponseEntity.ok().body(rst);
    }

    public ResponseEntity findById(Long id) {
        Optional<Restaurant> restaurant =  restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            Restaurant rest = restaurant.get();
            List<FoodItemDTO> foodItemDTOS = rest.getFoodItems().stream().map(f -> new FoodItemDTO(f.getId(),f.getRestaurant().getId(), f.getFoodName(),f.getCategory(),f.getPrice())).collect(Collectors.toList());
            List<OrderDTO> orders = rest.getOrders().stream().map(f -> new OrderDTO(f.getId(),f.getCustomer().getId(), f.getRestaurant().getId(),
                    f.getFoodItems().stream().map(g -> new FoodItemDTO(g.getId(),g.getRestaurant().getId(), g.getFoodName(),g.getCategory(), g.getPrice())).collect(Collectors.toList()),f.getOrderStatus(), f.getTotal())).collect(Collectors.toList());

            return ResponseEntity.ok().body(
                                        new RestaurantDTO(rest.getId(), rest.getAdministrator().getId(),rest.getAdministrator().getEmail(),
                                        rest.getAdministrator().getPhoneNumber(), rest.getName(), rest.getLocation(), rest.getDeliveryZones(),foodItemDTOS, orders));
        }else {
            try {
                throw new InstanceNotFoundException("Restaurant does not exits");
            } catch (InstanceNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    public ResponseEntity getFoodItems(Long id) {
        Optional<Restaurant> restaurant =  restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            List<FoodItemDTO> foodItemDTOS = restaurant.get().getFoodItems().stream().map(f -> new FoodItemDTO(f.getId(),f.getRestaurant().getId(), f.getFoodName(),f.getCategory(),f.getPrice())).collect(Collectors.toList());
            return ResponseEntity.ok().body(foodItemDTOS);
        }else {
            try {
                throw new InstanceNotFoundException("Restaurant does not exits");
            } catch (InstanceNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    public ResponseEntity findByAdministrator(User admin) {
        Optional<Restaurant> restaurant =  restaurantRepository.findRestaurantByAdministrator(admin);
        if(restaurant.isPresent()){
            Restaurant rest = restaurant.get();
            List<FoodItemDTO> foodItemDTOS = rest.getFoodItems().stream().map(f -> new FoodItemDTO(f.getId(),f.getRestaurant().getId(), f.getFoodName(),f.getCategory(),f.getPrice())).collect(Collectors.toList());
            List<OrderDTO> orders = rest.getOrders().stream().map(f -> new OrderDTO(f.getId(),f.getCustomer().getId(), f.getRestaurant().getId(),
                    f.getFoodItems().stream().map(g -> new FoodItemDTO(g.getId(),g.getRestaurant().getId(), g.getFoodName(),g.getCategory(),g.getPrice())).collect(Collectors.toList()),f.getOrderStatus(), f.getTotal())).collect(Collectors.toList());

            return ResponseEntity.ok().body(
                    new RestaurantDTO(rest.getId(), rest.getAdministrator().getId(),rest.getAdministrator().getEmail(),
                            rest.getAdministrator().getPhoneNumber(), rest.getName(), rest.getLocation(), rest.getDeliveryZones(),foodItemDTOS, orders));
        }else {
            try {
                throw new InstanceNotFoundException("Restaurant does not exits");
            } catch (InstanceNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }


    public ResponseEntity addNewRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
        return (ResponseEntity.ok().build());
    }


}
