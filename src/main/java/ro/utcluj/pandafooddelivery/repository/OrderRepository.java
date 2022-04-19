package ro.utcluj.pandafooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.utcluj.pandafooddelivery.model.Order;
import ro.utcluj.pandafooddelivery.model.Restaurant;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByRestaurant(Restaurant restaurant);

    @Query("SELECT o from Order o where o.customer.id = :id")
    List<Order> findByCustomerId(@Param("id") Long id);
}
