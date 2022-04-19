package ro.utcluj.pandafooddelivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "food_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String foodName;
    @ManyToOne
    @JoinColumn(name = "restaurant_id") // name that will appear in the table
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private FoodCategory category;

    private float price;

    @ManyToMany(mappedBy = "foodItems", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public FoodItem(Long id, String foodName, Restaurant restaurant, FoodCategory category, float price) {
        this.id = id;
        this.foodName = foodName;
        this.restaurant = restaurant;
        this.category = category;
        this.price = price;
    }
}
