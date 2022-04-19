package ro.utcluj.pandafooddelivery.controller.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.controller.request.AdministratorDTO;
import ro.utcluj.pandafooddelivery.service.AdministratorRegistrationService;

@RestController
@AllArgsConstructor
public class AdministratorRegistrationController {

    private AdministratorRegistrationService registrationService;

    @PostMapping(path = "/admin/register")
    public ResponseEntity registerRestaurant(@RequestBody AdministratorDTO request){
        return registrationService.registerAdministrator(request);
    }
}
