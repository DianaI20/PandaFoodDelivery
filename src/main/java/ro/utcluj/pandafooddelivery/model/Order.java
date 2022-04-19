package ro.utcluj.pandafooddelivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id") // name that will appear in the table
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "order_items",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<FoodItem> foodItems;

    private float total = -1;

    @Override
    public String toString() {
        return "Customer details:"  + "\n" + customer.toString()  + "\n" +
             //   "Food items:"       + foodItems.stream().map(f -> f.toString() + " ") + "\n" +
                "Total to pay: " + total + "\n";
    }
}
