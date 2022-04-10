package ro.utcluj.pandafooddelivery.controller.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.controller.request.CustomerRegistrationRequest;
import ro.utcluj.pandafooddelivery.service.CustomerRegistrationService;

@RestController
@RequestMapping(path = "/customer/register")
@AllArgsConstructor

public class CustomerRegistrationController {

    private CustomerRegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody CustomerRegistrationRequest request){
        return registrationService.registerCustomer(request);
    }
}
