package ro.utcluj.pandafooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcluj.pandafooddelivery.model.FoodItem;
import ro.utcluj.pandafooddelivery.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional < List<Restaurant>> findRestaurantByName(String name);
    List<Restaurant> findAll();
}
