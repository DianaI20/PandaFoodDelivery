package ro.utcluj.pandafooddelivery.controller.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.controller.request.CustomerDTO;
import ro.utcluj.pandafooddelivery.service.CustomerRegistrationService;

@RestController
@RequestMapping(path = "/customer/register")
@AllArgsConstructor

public class CustomerRegistrationController {

    private CustomerRegistrationService registrationService;

    @PostMapping
    public ResponseEntity register(@RequestBody CustomerDTO request){
        return registrationService.registerCustomer(request);
    }
}
