package ro.utcluj.pandafooddelivery.controller.request;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.utcluj.pandafooddelivery.model.FoodCategory;

@Getter
@Setter
@AllArgsConstructor
public class FoodItemDTO {

    private Long foodId;
    private Long restaurantId;
    private String name;
    private FoodCategory category;
    private float price;

}
