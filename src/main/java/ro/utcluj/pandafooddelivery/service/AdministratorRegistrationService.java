package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.Administrator;
import ro.utcluj.pandafooddelivery.controller.request.AdministratorDTO;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.service.validators.EmailValidator;

@Service
@AllArgsConstructor
public class AdministratorRegistrationService {

    private final UserService userService;
    private RestaurantService restaurantService;
    private final EmailValidator emailValidator;

    public ResponseEntity registerAdministrator(AdministratorDTO request) {

        boolean isEmailValid = emailValidator.test(request.getEmail());
        if(!isEmailValid){
            throw new IllegalStateException("Email not valid");
        }

        Administrator admin = new Administrator(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword() , request.getPhoneNumber());
        Restaurant restaurant = new Restaurant(request.getRestaurantName(),request.getRestaurantLocation(),request.getDeliveryZone(),admin);
        userService.signUpUser(admin);
        restaurantService.addNewRestaurant(restaurant);
        return ResponseEntity.ok().build();
    }

}
