package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurantByName(String name) throws InstanceNotFoundException {
        Optional <List<Restaurant>> restaurants = restaurantRepository.findRestaurantByName(name);
        if(restaurants.isPresent()){
            return restaurants.get();
        }else {
            throw new InstanceNotFoundException("No restaurant");
        }
    }

    public List<Restaurant> getAllRestaurants() throws InstanceNotFoundException {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) throws InstanceNotFoundException {
        Optional<Restaurant> restaurant =  restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get();
        }else {
           throw new InstanceNotFoundException("Restaurant does not exits");
        }
    }

}
