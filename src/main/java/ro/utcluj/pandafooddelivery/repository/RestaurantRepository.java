package ro.utcluj.pandafooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.model.User;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<List<Restaurant>> findRestaurantByName(String name);

    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    Optional<Restaurant> findByAdministrator(User administrator);


}
