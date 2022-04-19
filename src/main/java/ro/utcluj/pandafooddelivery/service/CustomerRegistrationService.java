package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.Customer;
import ro.utcluj.pandafooddelivery.controller.request.CustomerDTO;
import ro.utcluj.pandafooddelivery.service.validators.EmailValidator;

@Service
@AllArgsConstructor
public class CustomerRegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public ResponseEntity registerCustomer(CustomerDTO request) {

        boolean isEmailValid = emailValidator.test(request.getEmail());
        if(!isEmailValid){
            throw new IllegalStateException("Email not valid");
        }
        return userService.signUpUser(new Customer(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword() , request.getPhoneNumber(), request.getAddress()));
    }

}
