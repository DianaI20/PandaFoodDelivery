package ro.utcluj.pandafooddelivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String location;
    private String deliveryZones;

    @OneToOne
    private Administrator administrator;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FoodItem> foodItems;

    public Restaurant(String name, String location, String deliveryZones) {
        this.name = name;
        this.location = location;
        this.deliveryZones = deliveryZones;
    }


}
