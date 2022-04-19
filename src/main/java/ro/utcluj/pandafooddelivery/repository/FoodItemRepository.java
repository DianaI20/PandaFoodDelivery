package ro.utcluj.pandafooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcluj.pandafooddelivery.model.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}

