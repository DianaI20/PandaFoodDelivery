package ro.utcluj.pandafooddelivery.controller.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ro.utcluj.pandafooddelivery.model.Restaurant;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AdministatorRegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String password;
    private final String restaurantName;
    private final String restaurantLocation;
    private final String deliveryZone;
}
