package ro.utcluj.pandafooddelivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String location;
    private String deliveryZones;

    @OneToOne(cascade = CascadeType.ALL)
    private Administrator administrator;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<FoodItem> foodItems;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Order> orders;


    public Restaurant(String name, String location, String deliveryZones, Administrator administrator) {
        this.name = name;
        this.location = location;
        this.deliveryZones = deliveryZones;
        this.administrator = administrator;
    }

    public Restaurant(String name, String location, String deliveryZones) {
        this.name = name;
        this.location = location;
        this.deliveryZones = deliveryZones;
        this.administrator = administrator;
    }
}
