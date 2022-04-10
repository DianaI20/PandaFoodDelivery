package ro.utcluj.pandafooddelivery.controller.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.controller.request.AdministatorRegistrationRequest;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.controller.request.CustomerRegistrationRequest;
import ro.utcluj.pandafooddelivery.service.AdministratorRegistrationService;
import ro.utcluj.pandafooddelivery.service.CustomerRegistrationService;

@RestController
@RequestMapping(path = "/admin/register")
@AllArgsConstructor
public class AdministratorRegistrationController extends User {

    private AdministratorRegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody AdministatorRegistrationRequest request){
        return registrationService.registerAdministrator(request);
    }
}
