package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.Administrator;
import ro.utcluj.pandafooddelivery.controller.request.AdministatorRegistrationRequest;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.service.validators.EmailValidator;

@Service
@AllArgsConstructor
public class AdministratorRegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String registerAdministrator(AdministatorRegistrationRequest request) {

        boolean isEmailValid = emailValidator.test(request.getEmail());
        if(!isEmailValid){
            throw new IllegalStateException("Email not valid");
        }
        Restaurant restaurant = new Restaurant(request.getRestaurantName(),request.getRestaurantLocation(), request.getDeliveryZone());
        Administrator admin = new Administrator(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword() , request.getPhoneNumber(), restaurant);
        return userService.signUpUser(admin);
    }

}
