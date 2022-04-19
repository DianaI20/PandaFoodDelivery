package ro.utcluj.pandafooddelivery.controller.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.controller.dto.AdministratorDTO;
import ro.utcluj.pandafooddelivery.service.AdministratorRegistrationService;
import ro.utcluj.pandafooddelivery.service.exception.InvalidEmailException;

@RestController
@AllArgsConstructor
public class AdministratorRegistrationController {


    private AdministratorRegistrationService registrationService;

    @PostMapping(path = "/admin/register")
    public ResponseEntity registerRestaurant(@RequestBody AdministratorDTO administratorDTO) {
        return registrationService.register(administratorDTO);
    }
}
